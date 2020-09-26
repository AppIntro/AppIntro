package com.github.app.javaexample.ui.custom;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.github.app.javaexample.R;
import com.github.appintro.AppIntro2;
import com.github.appintro.AppIntroFragment;


public class CustomBackgroundIntro extends AppIntro2 {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addSlide(AppIntroFragment.newInstance(
                "Welcome!",
                "This is a demo of the AppIntro library, with a custom background on each slide!",
                 R.drawable.ic_slide1
        ));

        addSlide(AppIntroFragment.newInstance(
                "Clean App Intros",
                "This library offers developers the ability to add clean app intros at the start of their apps.",
                 R.drawable.ic_slide2
        ));

        addSlide(AppIntroFragment.newInstance(
                "Simple, yet Customizable",
                "The library offers a lot of customization, while keeping it simple for those that like simple.",
                 R.drawable.ic_slide3
        ));

        addSlide(AppIntroFragment.newInstance(
                "Explore",
                "Feel free to explore the rest of the library demo!",
                 R.drawable.ic_slide4
        ));

        // Set intro custom background
        setBackgroundResource(R.drawable.ic_drawer_header);

        // Change the color of the dot indicator.
        setIndicatorColor(Color.RED, Color.BLACK);
    }

    @Override
    protected void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        finish();
    }

    @Override
    protected void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        finish();
    }
}
