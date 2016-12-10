package com.github.paolorotolo.appintro;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Interpolator;

import java.lang.reflect.Field;

public final class AppIntroViewPager extends ViewPager {
    private static final int ON_ILLEGALLY_REQUESTED_NEXT_PAGE_MAX_INTERVAL = 1000;
    private boolean pagingEnabled;
    private boolean nextPagingEnabled;
    private float currentTouchDownX;
    private long illegallyRequestedNextPageLastCalled;
    private int lockPage;
    private ScrollerCustomDuration mScroller = null;
    private OnNextPageRequestedListener nextPageRequestedListener;
    private OnPageChangeListener pageChangeListener;

    public AppIntroViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);

        pagingEnabled = true;
        nextPagingEnabled = true;
        lockPage = 0;

        initViewPagerScroller();
    }

    public void addOnPageChangeListener(AppIntroBase.PagerOnPageChangeListener listener) {
        super.addOnPageChangeListener(listener);

        this.pageChangeListener = listener;
    }

    public void goToNextSlide() {
        if (isRtl(getResources())) {
            setCurrentItem(getCurrentItem() - 1);
        } else {
            setCurrentItem(getCurrentItem() + 1);
        }
    }

    public void goToPreviousSlide() {
        try {
            if (isRtl(getResources())) {
                setCurrentItem(getCurrentItem() + 1);
            } else {
                setCurrentItem(getCurrentItem() - 1);
            }
        } catch (Exception e){
            Log.e("AppIntroViewPager", "goToPreviousSlide: An error occured while switching to the previous slide. Was isFirstSlide checked before the call?");
        }
    }

    public boolean isFirstSlide(int size) {
        if (isRtl(getResources())) {
            return getCurrentItem() - size + 1 == 0;
        } else {
            return getCurrentItem() == 0;
        }
    }

    /**
     * Override is required to trigger {@link OnPageChangeListener#onPageSelected} for the first page.
     * This is needed to correctly handle progress button display after rotation on a locked first page.
     */
    @Override
    public void setCurrentItem(int item) {
        // when you pass set current item to 0,
        // the pageChangeListener won't be called so we call it on our own
        boolean invokeMeLater = false;

        if (super.getCurrentItem() == 0 && item == 0)
            invokeMeLater = true;
        super.setCurrentItem(item);

        if (invokeMeLater && pageChangeListener != null)
            pageChangeListener.onPageSelected(0);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            currentTouchDownX = event.getX();
            return super.onInterceptTouchEvent(event);
        } else if (checkPagingState(event) || checkCanRequestNextPage(event)) {
            // Call callback method if threshold has been reached
            checkIllegallyRequestedNextPage(event);
            return false;
        }

        return super.onInterceptTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            currentTouchDownX = event.getX();
            return super.onTouchEvent(event);
        }
        // Check if we should handle the touch event
        else if (checkPagingState(event) || checkCanRequestNextPage(event)) {
            // Call callback method if threshold has been reached
            checkIllegallyRequestedNextPage(event);
            return false;
        }

        return super.onTouchEvent(event);
    }

    private boolean checkPagingState(MotionEvent event) {
        if (!pagingEnabled) {
            return true;
        }

        if (!nextPagingEnabled) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                currentTouchDownX = event.getX();
            }
            if (event.getAction() == MotionEvent.ACTION_MOVE) {
                if (detectSwipeToEnd(event)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean checkCanRequestNextPage(MotionEvent event) {
        return nextPageRequestedListener != null && !nextPageRequestedListener.onCanRequestNextPage();
    }

    private void checkIllegallyRequestedNextPage(MotionEvent event) {
        int swipeThreshold = 25;

        if (event.getAction() == MotionEvent.ACTION_MOVE &&
                Math.abs(event.getX() - currentTouchDownX) >= swipeThreshold) {
            if (System.currentTimeMillis() - illegallyRequestedNextPageLastCalled >=
                    ON_ILLEGALLY_REQUESTED_NEXT_PAGE_MAX_INTERVAL) {
                illegallyRequestedNextPageLastCalled = System.currentTimeMillis();

                if (nextPageRequestedListener != null) {
                    nextPageRequestedListener.onIllegallyRequestedNextPage();
                }
            }
        }
    }

    /**
     * Override the Scroller instance with our own class so we can change the
     * duration
     */
    private void initViewPagerScroller() {
        try {
            Field scroller = ViewPager.class.getDeclaredField("mScroller");
            scroller.setAccessible(true);
            Field interpolator = ViewPager.class.getDeclaredField("sInterpolator");
            interpolator.setAccessible(true);

            mScroller = new ScrollerCustomDuration(getContext(),
                    (Interpolator) interpolator.get(null));
            scroller.set(this, mScroller);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Detects the direction of swipe. Right or left.
    // Returns true if swipe is in right direction
    private boolean detectSwipeToEnd(MotionEvent event) {
        final int SWIPE_THRESHOLD = 0; // detect swipe
        boolean result = false;

        try {
            float diffX = event.getX() - currentTouchDownX;
            if (Math.abs(diffX) > SWIPE_THRESHOLD) {
                if (diffX < 0) {
                    // swipe from right to left detected ie.SwipeLeft
                    result = true;
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        if (isRtl(getResources())) {
            return !result;
        } else {
            return result;
        }
    }

    static boolean isRtl(Resources resources) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            return resources.getConfiguration().getLayoutDirection() == View.LAYOUT_DIRECTION_RTL;
        }
        return false;
    }

    /**
     * Register an instance of OnNextPageRequestedListener.
     * Before the user swipes to the next page, this listener will be called and can interrupt swiping by returning false.
     *
     * @param nextPageRequestedListener Instance of OnNextPageRequestedListener
     */
    public void setOnNextPageRequestedListener(OnNextPageRequestedListener nextPageRequestedListener) {
        this.nextPageRequestedListener = nextPageRequestedListener;
    }

    /**
     * Set the factor by which the duration will change
     */
    public void setScrollDurationFactor(double scrollFactor) {
        mScroller.setScrollDurationFactor(scrollFactor);
    }

    public boolean isNextPagingEnabled() {
        return nextPagingEnabled;
    }

    /**
     * Enable or disable swiping to the next page
     *
     * @param nextPagingEnabled Whether swiping to the next page should be enabled or not
     */
    public void setNextPagingEnabled(boolean nextPagingEnabled) {
        this.nextPagingEnabled = nextPagingEnabled;
        if (!nextPagingEnabled) {
            lockPage = getCurrentItem();
        }
    }

    public boolean isPagingEnabled() {
        return pagingEnabled;
    }

    public void setPagingEnabled(boolean pagingEnabled) {
        this.pagingEnabled = pagingEnabled;
    }

    public int getLockPage() {
        return lockPage;
    }

    public void setLockPage(int lockPage) {
        this.lockPage = lockPage;
    }

    public interface OnNextPageRequestedListener {
        boolean onCanRequestNextPage();

        void onIllegallyRequestedNextPage();
    }
}
