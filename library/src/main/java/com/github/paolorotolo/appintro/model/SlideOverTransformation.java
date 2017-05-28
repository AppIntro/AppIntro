package com.github.paolorotolo.appintro.model;

import android.view.View;

/**
 * Created by tatianasolonets on 3/19/17.
 */

public class SlideOverTransformation extends  Transformation {

    private static final float SCALE_FACTOR_SLIDE = 0.85f;
    private static final float MIN_ALPHA_SLIDE = 0.35f;

    @Override
    public void transformPage( View page, float position) {
        if (position < 0 && position > -1) {
            // this is the page to the left
            scale = Math.abs(Math.abs(position) - 1) * (1.0f - SCALE_FACTOR_SLIDE) +
                    SCALE_FACTOR_SLIDE;
            alpha = Math.max(MIN_ALPHA_SLIDE, 1 - Math.abs(position));
            int pageWidth = page.getWidth();
            float translateValue = position * -pageWidth;
            if (translateValue > -pageWidth) {
                translationX = translateValue;
            } else {
                translationX = 0;
            }
        } else {
            alpha = 1;
            scale = 1;
            translationX = 0;
        }

        setTransformParameters(page);
    }
}
