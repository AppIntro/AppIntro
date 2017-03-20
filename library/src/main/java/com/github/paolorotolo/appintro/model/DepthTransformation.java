package com.github.paolorotolo.appintro.model;

import android.view.View;

/**
 * Created by tatianasolonets on 3/19/17.
 */

public class DepthTransformation extends Transformation {

    private static final float MIN_SCALE_DEPTH = 0.75f;

    @Override
    public void transformPage( View page, float position) {

        if (position > 0 && position < 1) {
            // moving to the right
            alpha = (1 - position);
            scale = MIN_SCALE_DEPTH + (1 - MIN_SCALE_DEPTH) * (1 - Math.abs(position));
            translationX = (page.getWidth() * -position);
        } else {
            // use default for all other cases
            alpha = 1;
            scale = 1;
            translationX = 0;
        }

        setTransformParameters(page);
    }
}
