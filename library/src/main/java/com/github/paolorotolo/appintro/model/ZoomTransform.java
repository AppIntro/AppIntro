package com.github.paolorotolo.appintro.model;

import android.view.View;

public class ZoomTransform extends Transformation {

    private static final float MIN_SCALE_ZOOM = 0.85f;
    private static final float MIN_ALPHA_ZOOM = 0.5f;

    @Override
    public void transformPage(View page, float position) {

        if (position >= -1 && position <= 1) {
            scale = Math.max(MIN_SCALE_ZOOM, 1 - Math.abs(position));
            alpha = MIN_ALPHA_ZOOM +
                    (scale - MIN_SCALE_ZOOM) / (1 - MIN_SCALE_ZOOM) * (1 - MIN_ALPHA_ZOOM);
            float vMargin = page.getHeight() * (1 - scale) / 2;
            float hMargin = page.getWidth() * (1 - scale) / 2;
            if (position < 0) {
                translationX = (hMargin - vMargin / 2);
            } else {
                translationX = (-hMargin + vMargin / 2);
            }
        } else {
            alpha = 1;
            scale = 1;
            translationX = 0;
        }

        setTransformParameters(page);
    }
}
