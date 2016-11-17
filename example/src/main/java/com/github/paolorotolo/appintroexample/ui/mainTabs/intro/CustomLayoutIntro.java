package com.github.paolorotolo.appintroexample.ui.mainTabs.intro;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintroexample.R;
import com.github.paolorotolo.appintroexample.util.SampleSlide;

/**
 * Created by andrew on 11/17/16.
 */

public class CustomLayoutIntro extends AppIntro {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addSlide(SampleSlide.newInstance(R.layout.intro_custom_layout1));
        addSlide(SampleSlide.newInstance(R.layout.intro_custom_layout2));
        addSlide(SampleSlide.newInstance(R.layout.intro_custom_layout3));
        addSlide(SampleSlide.newInstance(R.layout.intro_custom_layout4));
    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        finish();
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        finish();
    }
}
