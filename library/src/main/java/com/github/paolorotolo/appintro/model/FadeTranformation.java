package com.github.paolorotolo.appintro.model;

import android.view.View;

/**
 * Created by tatianasolonets on 3/19/17.
 */

public class FadeTranformation extends  Transformation {

    @Override
    public void transformPage( View page, float position) {

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

        setTransformParameters(page);
    }
}
