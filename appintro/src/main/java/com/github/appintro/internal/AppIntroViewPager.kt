package com.github.appintro.internal

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.animation.Interpolator
import androidx.viewpager.widget.ViewPager
import com.github.appintro.AppIntroBase
import com.github.appintro.AppIntroPageTransformerType
import com.github.appintro.AppIntroViewPagerListener
import com.github.appintro.internal.viewpager.ViewPagerTransformer
import kotlin.math.max

/**
 * Class that controls the [AppIntro] of AppIntro.
 * This is responsible of handling of paging, managing touch and dispatching events.
 *
 * @property isFullPagingEnabled Enable or disable swiping at all.
 * @property isPermissionSlide If the current slide has permissions.
 * @property lockPage Set the page where the lock happened.
 * @property onNextPageRequestedListener Listener for Next Page events.
 * @property isNextPagingEnabled Enable or disable swiping to the next page.
 */
internal class AppIntroViewPager(context: Context, attrs: AttributeSet) : ViewPager(context, attrs) {

    var isFullPagingEnabled = true
    var isPermissionSlide = false
    var lockPage = 0
    var onNextPageRequestedListener: AppIntroViewPagerListener? = null
    var isNextPagingEnabled: Boolean = true
        set(value) {
            field = value
            if (!value) {
                lockPage = currentItem
            }
        }

    private var currentTouchDownX: Float = 0.toFloat()
    private var currentTouchDownY: Float = 0.toFloat()
    private var illegallyRequestedNextPageLastCalled: Long = 0
    private var customScroller: ScrollerCustomDuration? = null
    private var pageChangeListener: OnPageChangeListener? = null

    init {
        // Override the Scroller instance with our own class so we can change the duration
        try {
            val scroller = ViewPager::class.java.getDeclaredField("mScroller")
            scroller.isAccessible = true

            val interpolator = ViewPager::class.java.getDeclaredField("sInterpolator")
            interpolator.isAccessible = true

            customScroller = ScrollerCustomDuration(context, interpolator.get(null) as Interpolator)
            scroller.set(this, customScroller)
        } catch (e: NoSuchFieldException) {
            e.printStackTrace()
        }
    }

    internal fun addOnPageChangeListener(listener: AppIntroBase.OnPageChangeListener) {
        super.addOnPageChangeListener(listener)
        this.pageChangeListener = listener
    }

    fun goToNextSlide() {
        currentItem += if (!LayoutUtil.isRtl(context)) 1 else -1
    }

    fun goToPreviousSlide() {
        currentItem += if (!LayoutUtil.isRtl(context)) -1 else 1
    }

    internal fun reCenterCurrentSlide() {
        // Hacky way to force a recenter of the ViewPager to the current slide.
        // We perform a page back and forward to recenter the ViewPager at the current position.
        // This is needed as we're interrupting the user Swipe due to Permissions.
        // If the user denies a permission, we want to recenter the slide.
        val item = currentItem
        setCurrentItem(max(item - 1, 0), false)
        setCurrentItem(item, false)
    }

    fun isFirstSlide(size: Int): Boolean {
        return if (LayoutUtil.isRtl(context)) (currentItem - size + 1 == 0) else (currentItem == 0)
    }

    fun isLastSlide(size: Int): Boolean {
        return if (LayoutUtil.isRtl(context)) (currentItem == 0) else (currentItem - size + 1 == 0)
    }

    fun getCurrentSlideNumber(size: Int): Int {
        return if (LayoutUtil.isRtl(context)) (size - currentItem) else (currentItem + 1)
    }

    /**
     * Override is required to trigger [AppIntroBase.OnPageChangeListener.onPageSelected] for the first page.
     * This is needed to correctly handle progress button display after rotation on a locked first page.
     */
    override fun setCurrentItem(currentItem: Int) {
        val oldItem = super.getCurrentItem()
        super.setCurrentItem(currentItem)

        // When you pass set current item to 0,
        // The pageChangeListener won't be called so we call it on our own
        if (oldItem == 0 && currentItem == 0) {
            pageChangeListener?.onPageSelected(0)
        }
    }

    /**
     * Set the factor by which the Scrolling duration will change.
     */
    fun setScrollDurationFactor(factor: Double) {
        customScroller?.scrollDurationFactor = factor
    }

    override fun performClick() = super.performClick()

    override fun onInterceptTouchEvent(event: MotionEvent): Boolean {
        if (!handleTouchEvent(event)) {
            return false
        }

        // Calling super will allow the slider to "work" left and right.
        return super.onInterceptTouchEvent(event)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (!handleTouchEvent(event)) {
            return false
        }

        // Calling super will allow the slider to "work" left and right.
        return super.onTouchEvent(event)
    }

    /**
     * Checks for illegal sliding attempts.
     * Every time the user presses the screen, the respective coordinates are stored.
     * Once the user swipes/stops pressing, the new coordinates are checked against the stored ones.
     * Therefor [userIllegallyRequestNextPage] is called. If this call detects an illegal swipe,
     * the respective listener [onNextPageRequestedListener] gets called.
     */
    private fun handleTouchEvent(event: MotionEvent): Boolean {
        // If paging is disabled we should ignore any viewpager touch
        // (also, not display any error message)
        if (!isFullPagingEnabled) {
            return false
        }

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                currentTouchDownX = event.x
                currentTouchDownY = event.y
            }
            else -> {
                if (event.action == MotionEvent.ACTION_UP) {
                    performClick()
                }
                val canRequestNextPage = onNextPageRequestedListener?.onCanRequestNextPage() ?: true

                // If user can't request the page, we shortcircuit the ACTION_MOVE logic here.
                // We need to return false if we detect that the user swipes forward,
                // and also call onIllegallyRequestedNextPage if the threshold was too high
                // (so the user can be informed).
                if (!canRequestNextPage && isSwipeForward(currentTouchDownX, event.x)) {
                    if (userIllegallyRequestNextPage(event)) {
                        onNextPageRequestedListener?.onIllegallyRequestedNextPage()
                    }
                    return false
                }

                // If the slide contains permissions, check for forward swipe.
                if (isPermissionSlide && isSwipeForward(currentTouchDownX, event.x)) {
                    onNextPageRequestedListener?.onUserRequestedPermissionsDialog()
                }
            }
        }
        return isFullPagingEnabled
    }

    /**
     * Util function to check if the user swiped forward.
     * The direction of forward is different in RTL mode.
     */
    private fun isSwipeForward(oldX: Float, newX: Float): Boolean {
        return (if (LayoutUtil.isRtl(context)) (newX > oldX) else (oldX > newX))
    }

    /**
     * Util function to throttle illegallyRequestedNext to max one request per second.
     */
    private fun userIllegallyRequestNextPage(event: MotionEvent): Boolean {
        if (System.currentTimeMillis() - illegallyRequestedNextPageLastCalled >=
            ON_ILLEGALLY_REQUESTED_NEXT_PAGE_MAX_INTERVAL
        ) {
            illegallyRequestedNextPageLastCalled = System.currentTimeMillis()
            return true
        }

        return false
    }

    fun setAppIntroPageTransformer(appIntroTransformer: AppIntroPageTransformerType) {
        setPageTransformer(true, ViewPagerTransformer(appIntroTransformer))
    }

    private companion object {
        private const val ON_ILLEGALLY_REQUESTED_NEXT_PAGE_MAX_INTERVAL = 1000
    }
}
