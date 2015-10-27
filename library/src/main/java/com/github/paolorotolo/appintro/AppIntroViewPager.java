package com.github.paolorotolo.appintro;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by dluong on 10/27/2015.
 */
public class AppIntroViewPager extends ViewPager {

    private boolean pagingEnabled;
    private boolean nextPagingEnabled;
    private float initialXValue;
    private int lockPage;

    public AppIntroViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        pagingEnabled = true;
        nextPagingEnabled = true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(!pagingEnabled){
            return false;
        }
        // disable lock page logic if swiped to a previous page
        if (!nextPagingEnabled && (lockPage != getCurrentItem())) {
            nextPagingEnabled = true;
        }

        if (!nextPagingEnabled) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                initialXValue = event.getX();
            }
            if (event.getAction() == MotionEvent.ACTION_MOVE) {
                if (detectSwipeToRight(event)) {
                    return false;
                }
            }
        }

        return super.onTouchEvent(event);
    }

    // To enable/disable swipe
    public void setNextPagingEnabled(boolean nextPagingEnabled) {
        this.nextPagingEnabled = nextPagingEnabled;
        if (!nextPagingEnabled) {
            lockPage = getCurrentItem();
        }
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