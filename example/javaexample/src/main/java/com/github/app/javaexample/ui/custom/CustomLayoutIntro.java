package com.github.app.javaexample.ui.custom;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.github.app.javaexample.R;
import com.github.appintro.AppIntro;
import com.github.appintro.AppIntroCustomLayoutFragment;


public class CustomLayoutIntro extends AppIntro {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addSlide(AppIntroCustomLayoutFragment.newInstance(R.layout.intro_custom_layout1));
        addSlide(AppIntroCustomLayoutFragment.newInstance(R.layout.intro_custom_layout2));
        addSlide(AppIntroCustomLayoutFragment.newInstance(R.layout.intro_custom_layout3));
        addSlide(AppIntroCustomLayoutFragment.newInstance(R.layout.intro_custom_layout4));

        showStatusBar(true);
        setStatusBarColorRes(R.color.orange);
        setNavBarColorRes(R.color.orange);
        setProgressIndicator();
    }

    @Override
    protected void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        finish();
    }

    @Override
    protected void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        finish();
    }
}
