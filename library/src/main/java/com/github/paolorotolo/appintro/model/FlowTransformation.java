package com.github.paolorotolo.appintro.model;

import android.view.View;

/**
 * Created by tatianasolonets on 3/19/17.
 */

public class FlowTransformation extends  Transformation {

    @Override
    public void transformPage( View page, float position) {
        page.setRotationY(position * -30f);

        setTransformParameters(page);
    }
}
