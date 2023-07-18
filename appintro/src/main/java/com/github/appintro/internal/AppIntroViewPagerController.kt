package com.github.appintro.internal

import android.gesture.GestureOverlayView
import android.gesture.GestureOverlayView.OnGestureListener
import android.view.MotionEvent
import androidx.viewpager2.widget.ViewPager2
import com.github.appintro.AppIntroBase
import com.github.appintro.AppIntroPageTransformerType
import com.github.appintro.AppIntroViewPagerListener
import com.github.appintro.internal.viewpager.PagerAdapter
import com.github.appintro.internal.viewpager.ViewPagerTransformer
import kotlin.math.max

/**
 * Class that controls the [ViewPager2] of AppIntro.
 * This is responsible of handling of paging, managing touch and dispatching events.
 *
 * @property isFullPagingEnabled Enable or disable swiping at all.
 * @property isPermissionSlide If the current slide has permissions.
 * @property onNextPageRequestedListener Listener for Next Page events.
 */
internal class AppIntroViewPagerController(
    private val viewPager: ViewPager2,
    private val viewPagerGestureOverlay: GestureOverlayView
) {

    var isFullPagingEnabled = true
    var isPermissionSlide = false
    var onNextPageRequestedListener: AppIntroViewPagerListener? = null

    private var currentTouchDownX: Float = 0.toFloat()
    private var currentTouchDownY: Float = 0.toFloat()
    private var illegallyRequestedNextPageLastCalled: Long = 0
    private var pageChangeCallback: AppIntroBase.OnPageChangeCallback? = null

    init {
        addPagerTouchInterceptor()
    }

    fun goToNextSlide() {
        with(viewPager) {
            // avoid IllegalStateException when changing item while fake dragging
            endFakeDrag()
            setCurrentViewPagerItem(
                position = if (!LayoutUtil.isRtl(context)) currentItem + 1 else currentItem - 1,
                smoothScrool = true
            )
        }
    }

    fun goToPreviousSlide() {
        with(viewPager) {
            // avoid IllegalStateException when changing item while fake dragging
            endFakeDrag()
            setCurrentViewPagerItem(
                position = if (!LayoutUtil.isRtl(context)) currentItem - 1 else currentItem + 1,
                smoothScrool = true
            )
        }
    }

    fun isFirstSlide(size: Int): Boolean {
        with(viewPager) {
            return if (LayoutUtil.isRtl(context)) (currentItem - size + 1 == 0) else (currentItem == 0)
        }
    }

    fun isLastSlide(size: Int): Boolean {
        with(viewPager) {
            return if (LayoutUtil.isRtl(context)) (currentItem == 0) else (currentItem - size + 1 == 0)
        }
    }

    fun getCurrentSlideNumber(size: Int): Int {
        with(viewPager) {
            return if (LayoutUtil.isRtl(context)) (size - currentItem) else (currentItem + 1)
        }
    }

    /**
     * Override is required to trigger [AppIntroBase.OnPageChangeCallback.onPageSelected] for the first page.
     * This is needed to correctly handle progress button display after rotation on a locked first page.
     */
    fun setCurrentViewPagerItem(position: Int, smoothScrool: Boolean = false) {
        with(viewPager) {
            endFakeDrag()

            val oldItem = currentItem
            viewPager.setCurrentItem(position, smoothScrool)

            // When you pass set current item to 0,
            // The pageChangeListener won't be called so we call it on our own
            if (oldItem == 0 && position == 0) {
                pageChangeCallback?.onPageSelected(0)
            }
        }
    }

    /**
     * Checks for illegal sliding attempts.
     * Every time the user presses the screen, the respective coordinates are stored.
     * Once the user swipes/stops pressing, the new coordinates are checked against the stored ones.
     * Therefore [userIllegallyRequestNextPage] is called. If this call detects an illegal swipe,
     * the respective listener [onNextPageRequestedListener] gets called.
     */
    private fun canPerformTouchEvent(event: MotionEvent?): Boolean {
        // If paging is disabled we should ignore any viewpager touch
        // (also, not display any error message)
        if (!isFullPagingEnabled || event == null) {
            return false
        }

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                currentTouchDownX = event.x
                currentTouchDownY = event.y
            }
            else -> {
                if (event.action == MotionEvent.ACTION_UP) {
                    viewPager.performClick()
                }
                val canRequestNextPage = onNextPageRequestedListener?.onCanRequestNextPage() ?: true

                // If user can't request the page, we shortcircuit the ACTION_MOVE logic here.
                // We need to return false if we detect that the user swipes forward,
                // and also call onIllegallyRequestedNextPage if the threshold was too high
                // (so the user can be informed).
                if (!canRequestNextPage && isSwipeForward(currentTouchDownX, event.x)) {
                    if (userIllegallyRequestNextPage()) {
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

    private var lastTouchValue: Float = 0f

    /**
     * Simulate touch events on the ViewPager2 using fakeDrag, since isUserInputEnabled = false
     * We need this to eventually block user touches in forward if policy is not respected
     */
    private fun handleOnTouchEvent(event: MotionEvent?): Boolean {
        if (!canPerformTouchEvent(event)) {
            return false
        }

        // allow the slider to "work" left and right.
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                lastTouchValue = event.x
                if (!viewPager.isFakeDragging) {
                    viewPager.beginFakeDrag()
                }
            }

            MotionEvent.ACTION_MOVE -> {
                val value = event.x
                val delta = value - lastTouchValue

                viewPager.fakeDragBy(delta)
                lastTouchValue = value
                return true
            }

            MotionEvent.ACTION_CANCEL, MotionEvent.ACTION_UP -> {
                viewPager.endFakeDrag()
            }
        }
        return true
    }

    /**
     * Util function to check if the user swiped forward.
     * The direction of forward is different in RTL mode.
     */
    private fun isSwipeForward(oldX: Float, newX: Float): Boolean {
        with(viewPager) {
            return (if (LayoutUtil.isRtl(context)) (newX > oldX) else (oldX > newX))
        }
    }

    /**
     * Util function to throttle illegallyRequestedNext to max one request per second.
     */
    private fun userIllegallyRequestNextPage(): Boolean {
        if (System.currentTimeMillis() - illegallyRequestedNextPageLastCalled >=
            ON_ILLEGALLY_REQUESTED_NEXT_PAGE_MAX_INTERVAL
        ) {
            illegallyRequestedNextPageLastCalled = System.currentTimeMillis()
            return true
        }

        return false
    }

    /**
     * Disables ViewPager swipes and handles manually touch events.
     * This is because we may want to discard some swipes in a particular direction
     * if policy doesn't allow that.
     *
     * Touch events are then forwarded (if policy allows that) to pager
     * using fakeDrag feature of ViewPager2.
     */
    private fun addPagerTouchInterceptor() {
        // disable ViewPager swipe
        // touch events will be forwarded to gesture overlay to check policies
        viewPager.isUserInputEnabled = false

        viewPagerGestureOverlay.addOnGestureListener(object : OnGestureListener {
            override fun onGestureStarted(overlayView: GestureOverlayView?, event: MotionEvent?) {
                handleOnTouchEvent(event)
            }

            override fun onGesture(overlayView: GestureOverlayView?, event: MotionEvent?) {
                handleOnTouchEvent(event)
            }

            override fun onGestureEnded(overlayView: GestureOverlayView?, event: MotionEvent?) {
                handleOnTouchEvent(event)
            }

            override fun onGestureCancelled(overlayView: GestureOverlayView?, event: MotionEvent?) {
                handleOnTouchEvent(event)
            }
        })
    }

    internal fun reCenterCurrentSlide() {
        // Hacky way to force a recenter of the ViewPager to the current slide.
        // We perform a page back and forward to recenter the ViewPager at the current position.
        // This is needed as we're interrupting the user Swipe due to Permissions.
        // If the user denies a permission, we want to recenter the slide.
        with(viewPager) {
            val item = currentItem
            setCurrentViewPagerItem(max(item - 1, 0), false)
            setCurrentViewPagerItem(item, false)
        }
    }

    internal fun registerOnPageChangeCallback(callback: AppIntroBase.OnPageChangeCallback) {
        viewPager.registerOnPageChangeCallback(callback)
        this.pageChangeCallback = callback
    }

    fun setAppIntroPageTransformer(appIntroTransformer: AppIntroPageTransformerType) {
        with(viewPager) {
            setPageTransformer(ViewPagerTransformer(appIntroTransformer))
        }
    }

    fun setPageTransformer(pageTransformer: ViewPager2.PageTransformer?) {
        with(viewPager) {
            setPageTransformer(pageTransformer)
        }
    }

    fun setOffscreenPageLimit(offscreenPageLimit: Int) {
        viewPager.offscreenPageLimit = offscreenPageLimit
    }

    fun setAdapter(pagerAdapter: PagerAdapter) {
        viewPager.adapter = pagerAdapter
    }

    fun post(function: () -> Unit) {
        viewPager.post(function)
    }

    fun getCurrentItem() = viewPager.currentItem

    private companion object {
        private const val ON_ILLEGALLY_REQUESTED_NEXT_PAGE_MAX_INTERVAL = 1000
    }
}
