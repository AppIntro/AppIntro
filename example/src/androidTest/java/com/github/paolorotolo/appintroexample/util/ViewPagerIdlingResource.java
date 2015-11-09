package com.github.paolorotolo.appintroexample.util;

import android.support.test.espresso.IdlingResource;
import android.support.v4.view.ViewPager;

public class ViewPagerIdlingResource implements IdlingResource {

    private final String mName;

    private boolean mIdle = true; // Default to idle since we can't query the scroll state.

    private ResourceCallback mResourceCallback;

    public ViewPagerIdlingResource(ViewPager viewPager, String name) {
        viewPager.addOnPageChangeListener(new ViewPagerListener());
        mName = name;
    }

    @Override
    public String getName() {
        return mName;
    }

    @Override
    public boolean isIdleNow() {
        return mIdle;
    }

    @Override
    public void registerIdleTransitionCallback(ResourceCallback resourceCallback) {
        mResourceCallback = resourceCallback;
    }

    private class ViewPagerListener extends ViewPager.SimpleOnPageChangeListener {

        @Override
        public void onPageScrollStateChanged(int state) {
            mIdle = (state == ViewPager.SCROLL_STATE_IDLE
                    // Treat dragging as idle, or Espresso will block itself when swiping.
                    || state == ViewPager.SCROLL_STATE_DRAGGING);
            if (mIdle && mResourceCallback != null) {
                mResourceCallback.onTransitionToIdle();
            }
        }
    }
}
