package com.github.app.javaexample.ui.custom;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.github.app.javaexample.ui.custom.fragments.CustomSlidePolicyFragment;
import com.github.appintro.AppIntro;
import com.github.appintro.AppIntroFragment;


public class SlidePolicyIntro extends AppIntro {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addSlide(AppIntroFragment.newInstance(
                "Welcome",
                "This is a demo of the AppIntro library, using the SlidePolicy feature."
        ));

        addSlide(CustomSlidePolicyFragment.newIntance());

        addSlide(AppIntroFragment.newInstance(
                "Policy Respected!",
                "If the user arrived here, the SlidePolicy was respected."
        ));

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
