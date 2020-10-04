package com.github.appintro.example.ui.java;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.github.appintro.AppIntro;
import com.github.appintro.AppIntroFragment;
import com.github.appintro.AppIntroPageTransformerType;
import com.github.appintro.example.R;


public class JavaIntro extends AppIntro {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        addSlide(AppIntroFragment.newInstance("Welcome!",
                "This is a demo example in java of AppIntro library, with a custom background on each slide!",
                R.drawable.ic_slide1));

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

        // Fade Transition
        setTransformer(AppIntroPageTransformerType.Fade.INSTANCE);
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
