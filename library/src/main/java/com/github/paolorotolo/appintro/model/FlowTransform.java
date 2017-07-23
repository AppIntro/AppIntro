package com.github.paolorotolo.appintro.model;

import android.view.View;

public class FlowTransform extends Transformation {

    @Override
    public void transformPage(View page, float position) {
        page.setRotationY(position * -30f);

        setTransformParameters(page);
    }
}
