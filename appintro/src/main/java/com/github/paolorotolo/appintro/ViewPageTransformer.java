package com.github.paolorotolo.appintro;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;

class ViewPageTransformer implements ViewPager.PageTransformer {
    private static final float MIN_SCALE_DEPTH = 0.75f;
    private static final float MIN_SCALE_ZOOM = 0.85f;
    private static final float MIN_ALPHA_ZOOM = 0.5f;
    private static final float SCALE_FACTOR_SLIDE = 0.85f;
    private static final float MIN_ALPHA_SLIDE = 0.35f;
    private final TransformType mTransformType;

    ViewPageTransformer(TransformType transformType) {
        mTransformType = transformType;
    }

    public void transformPage(@NonNull View page, float position) {
        final float alpha;
        final float scale;
        final float translationX;

        switch (mTransformType) {
            case FLOW:
                page.setRotationY(position * -30f);
                return;

            case SLIDE_OVER:
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
                break;

            case DEPTH:
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
                break;

            case ZOOM:
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
                break;
            case FADE:
                if (position <= -1.0F || position >= 1.0F) {
                    page.setAlpha(0.0F);
                    page.setClickable(false);
                } else if (position == 0.0F) {
                    page.setAlpha(1.0F);
                    page.setClickable(true);
                } else {
                    // position is between -1.0F & 0.0F OR 0.0F & 1.0F
                    page.setAlpha(1.0F - Math.abs(position));
                }

            default:
                return;
        }

        page.setAlpha(alpha);
        page.setTranslationX(translationX);
        page.setScaleX(scale);
        page.setScaleY(scale);
    }

    enum TransformType {
        FLOW,
        DEPTH,
        ZOOM,
        SLIDE_OVER,
        FADE
    }
}
