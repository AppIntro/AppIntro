package com.github.paolorotolo.appintro;

import android.animation.ArgbEvaluator;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.view.View;
import android.widget.FrameLayout;

import java.util.ArrayList;
public abstract class AppIntro2 extends AppIntroBase {
    private static final String TAG = "AppIntro2";

    private boolean STATUS_BAR_VISIBLE = false;

    protected View customBackgroundView;
    protected FrameLayout backgroundFrame;
    private ArrayList<Integer> transitionColors;
    private ArgbEvaluator argbEvaluator = new ArgbEvaluator();

    public AppIntro2() {
        super(R.layout.intro_layout2);
    }

    @Override
    final protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        backgroundFrame = (FrameLayout) findViewById(R.id.background);
    }

    /**
     * Shows or hides Done button, replaced with setProgressButtonEnabled
     *
     * @deprecated use {@link #setProgressButtonEnabled(boolean)} instead.
     */
    @Deprecated
    public void showDoneButton(boolean showDone) {
        setProgressButtonEnabled(showDone);
    }

    public void setBackgroundView(View view){
        customBackgroundView = view;
        if (customBackgroundView!=null){
            backgroundFrame.addView(customBackgroundView);
        }
    }

    /**
     * For color transition, will be shown only if color values are properly set and
     * Size of the color array must be equal to the number of slides added
     * @param colors Set color values
     * */
    public void setAnimationColors(@ColorInt ArrayList<Integer> colors) {
        transitionColors = colors;
    }
}
