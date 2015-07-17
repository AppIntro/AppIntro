package com.github.paolorotolo.appintro;

import android.support.v4.view.ViewPager;
import android.view.View;

import com.nineoldandroids.view.ViewHelper;

class FadePageTransformer implements ViewPager.PageTransformer {
    @Override
    public void transformPage(View view, float position) {
        ViewHelper.setTranslationX(view, view.getWidth() * -position);

        if (position <= -1.0F || position >= 1.0F) {
            ViewHelper.setAlpha(view, 0.0F);
            view.setClickable(false);
        } else if (position == 0.0F) {
            ViewHelper.setAlpha(view, 1.0F);
            view.setClickable(true);
        } else {
            // position is between -1.0F & 0.0F OR 0.0F & 1.0F
            ViewHelper.setAlpha(view, 1.0F - Math.abs(position));
        }
    }
}
