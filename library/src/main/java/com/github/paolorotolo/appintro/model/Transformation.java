package com.github.paolorotolo.appintro.model;

import android.view.View;

/**
 * Created by tatianasolonets on 3/19/17.
 * Transformation class for use with slide transform animations.
 */

public abstract class Transformation {

    protected float alpha;
    protected float scale;
    protected float translationX;

    public abstract void transformPage(View page, float position);

    protected void setTransformParameters(View page) {
        page.setAlpha(alpha);
        page.setTranslationX(translationX);
        page.setScaleX(scale);
        page.setScaleY(scale);
    }
}
