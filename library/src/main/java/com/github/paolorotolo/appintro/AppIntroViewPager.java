package com.github.paolorotolo.appintro;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.Interpolator;

import java.lang.reflect.Field;

public class AppIntroViewPager extends ViewPager {

    private boolean pagingEnabled;
    private boolean nextPagingEnabled;
    private float initialXValue;
    private int lockPage;
    protected OnPageChangeListener listener;

    public AppIntroViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        pagingEnabled = true;
        nextPagingEnabled = true;
        lockPage = 0;
        initViewPagerScroller();
    }

    @Override
    public void addOnPageChangeListener(OnPageChangeListener listener) {
        super.addOnPageChangeListener(listener);
        this.listener = listener;
    }

    /**
     * Override is required to trigger {@link OnPageChangeListener#onPageSelected} for the first page.
     * This is needed to correctly handle progress button display after rotation on a locked first page.
     */
    @Override
    public void setCurrentItem(int item) {
        // when you pass set current item to 0,
        // the listener won't be called so we call it on our own
        boolean invokeMeLater = false;

        if (super.getCurrentItem() == 0 && item == 0)
            invokeMeLater = true;

        super.setCurrentItem(item);

        if (invokeMeLater && listener != null)
            listener.onPageSelected(0);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        if (checkPagingState(event)) {
            return false;
        }

        return super.onInterceptTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (checkPagingState(event)) {
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
                initialXValue = event.getX();
            }
            if (event.getAction() == MotionEvent.ACTION_MOVE) {
                if (detectSwipeToRight(event)) {
                    return true;
                }
            }
        }
        return false;
    }

    // To enable/disable swipe
    public void setNextPagingEnabled(boolean nextPagingEnabled) {
        this.nextPagingEnabled = nextPagingEnabled;
        if (!nextPagingEnabled) {
            lockPage = getCurrentItem();
        }
    }
    private ScrollerCustomDuration mScroller = null;

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

    /**
     * Set the factor by which the duration will change
     */
    public void setScrollDurationFactor(double scrollFactor) {
        mScroller.setScrollDurationFactor(scrollFactor);
    }

    public boolean isNextPagingEnabled() {
        return nextPagingEnabled;
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

    // Detects the direction of swipe. Right or left.
    // Returns true if swipe is in right direction
    private boolean detectSwipeToRight(MotionEvent event) {
        final int SWIPE_THRESHOLD = 0; // detect swipe
        boolean result = false;

        try {
            float diffX = event.getX() - initialXValue;
            if (Math.abs(diffX) > SWIPE_THRESHOLD) {
                if (diffX < 0) {
                    // swipe from right to left detected ie.SwipeLeft
                    result = true;
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return result;
    }
}