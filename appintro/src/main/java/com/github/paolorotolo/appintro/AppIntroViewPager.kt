package com.github.paolorotolo.appintro

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.animation.Interpolator
import androidx.viewpager.widget.ViewPager
import com.github.paolorotolo.appintro.internal.LayoutUtil
import com.github.paolorotolo.appintro.internal.LogHelper
import com.github.paolorotolo.appintro.internal.ScrollerCustomDuration

const val ON_ILLEGALLY_REQUESTED_NEXT_PAGE_MAX_INTERVAL = 1000

/**
 * Class that controls the [AppIntro] of AppIntro.
 * This is responsible of handling of paging, managing touch and dispatching events.
 *
 * @property isFullPagingEnabled Enable or disable swiping at all.
 * @property lockPage Set the page where the lock happened.
 * @property onNextPageRequestedListener Listener for Next Page events
 * @property isNextPagingEnabled Enable or disable swiping to the next page.
 */
class AppIntroViewPager(context: Context, attrs: AttributeSet) : ViewPager(context, attrs) {

    var isFullPagingEnabled = true
    var lockPage = 0
    var onNextPageRequestedListener: OnNextPageRequestedListener? = null
    var isNextPagingEnabled: Boolean = true
        set(value) {
            field = value
            if (!value) {
                lockPage = currentItem
            }
        }

    private var currentTouchDownX: Float = 0.toFloat()
    private var illegallyRequestedNextPageLastCalled: Long = 0
    private var customScroller: ScrollerCustomDuration? = null
    private var pageChangeListener: ViewPager.OnPageChangeListener? = null

    init {
        // Override the Scroller instance with our own class so we can change the duration
        try {
            val scroller = ViewPager::class.java.getDeclaredField("mScroller")
            scroller.isAccessible = true

            val interpolator = ViewPager::class.java.getDeclaredField("sInterpolator")
            interpolator.isAccessible = true

            customScroller = ScrollerCustomDuration(context, interpolator.get(null) as Interpolator)
            scroller.set(this, customScroller)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    internal fun addOnPageChangeListener(listener: AppIntroBase.OnPageChangerListener) {
        super.addOnPageChangeListener(listener)
        this.pageChangeListener = listener
    }

    fun goToNextSlide() {
        currentItem += if (!LayoutUtil.isRtl(context)) 1 else -1
    }

    fun goToPreviousSlide() {
        try {
            currentItem += if (!LayoutUtil.isRtl(context)) -1 else 1
        } catch (e: Exception) {
            LogHelper.e("AppIntro", "goToPreviousSlide: An error occurred while switching to the previous slide. Was isFirstSlide checked before the call?")
        }
    }

    fun isFirstSlide(size: Int): Boolean {
        return if (LayoutUtil.isRtl(context)) (currentItem - size + 1 == 0) else (currentItem == 0)
    }

    /**
     * Override is required to trigger [OnPageChangeListener.onPageSelected] for the first page.
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

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        // If paging is disabled we should ignore any viewpager touch
        // (also, not display any error message)
        if (!isFullPagingEnabled) {
            return false
        }

        val canRequestNextPage = onNextPageRequestedListener?.onCanRequestNextPage() ?: true
        when (event?.action) {
            MotionEvent.ACTION_UP -> performClick()
            MotionEvent.ACTION_DOWN -> currentTouchDownX = event.x
            MotionEvent.ACTION_MOVE -> {
                // If user can't request the page, we shortcircuit the ACTION_MOVE logic here.
                // We need to return false, and also call onIllegallyRequestedNextPage if the
                // threshold was too high (so the user can be informed).
                if (!canRequestNextPage) {
                    if (userIllegallyRequestNextPage(event)) {
                        onNextPageRequestedListener?.onIllegallyRequestedNextPage()
                    }
                    return false
                }
            }
        }

        // Calling super will allow the slider to "work" left and right.
        return super.onTouchEvent(event)
    }

    /**
     * Util function to check if the user Illegaly request a swipe.
     * Also checks if the event happened not earlier than every 1000ms
     */
    private fun userIllegallyRequestNextPage(event: MotionEvent): Boolean {
        val swipeThreshold = 25
        if (Math.abs(event.x - currentTouchDownX) >= swipeThreshold) {
            if (System.currentTimeMillis() - illegallyRequestedNextPageLastCalled >= ON_ILLEGALLY_REQUESTED_NEXT_PAGE_MAX_INTERVAL) {
                illegallyRequestedNextPageLastCalled = System.currentTimeMillis()
                return true
            }
        }
        return false
    }

    /**
     * Register an instance of OnNextPageRequestedListener.
     * Before the user swipes to the next page, this listener will be called and
     * can interrupt swiping by returning false to [onCanRequestNextPage]
     *
     * [onIllegallyRequestedNextPage] will be called if the user tries to swipe and was not allowed
     * to do so (useful for showing a toast or something similar).
     */
    interface OnNextPageRequestedListener {
        fun onCanRequestNextPage(): Boolean

        fun onIllegallyRequestedNextPage()
    }
}
