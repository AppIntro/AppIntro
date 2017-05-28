package com.github.paolorotolo.appintro;

import android.annotation.SuppressLint;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.github.paolorotolo.appintro.model.Transformation;

class ViewPageTransformer implements ViewPager.PageTransformer {

    private final Transformation mTransformation;

    ViewPageTransformer(Transformation transformation) {
        mTransformation = transformation;
    }

    @SuppressLint("NewApi")
    public void transformPage(View page, float position) {
        mTransformation.transformPage(page, position);
    }
}
