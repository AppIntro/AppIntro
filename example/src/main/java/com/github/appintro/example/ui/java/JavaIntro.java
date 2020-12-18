package com.github.appintro.example.ui.java;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.github.appintro.AppIntro;
import com.github.appintro.AppIntroBase;
import com.github.appintro.SlidePageFragment;
import com.github.appintro.AppIntroPageTransformerType;
import com.github.appintro.example.R;


public class JavaIntro extends AppCompatActivity implements AppIntroBase.OnAppIntroListener {

    private AppIntro appIntro;
    private final String appIntroTag = "AppIntro";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame);

        if (savedInstanceState == null) {
            appIntro = AppIntro.newInstance(false);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.app_intro_container, appIntro, appIntroTag)
                    .commitNow();
        } else {
            appIntro = (AppIntro) getSupportFragmentManager().findFragmentByTag(appIntroTag);
        }
        appIntro.setAppIntroListener(this);

    }

    @Override
    public void onCreateAppIntro() {
        appIntro.addSlide(SlidePageFragment.newInstance("Welcome!",
                "This is a demo example in java of AppIntro library, with a custom background on each slide!",
                R.drawable.ic_slide1));

        appIntro.addSlide(SlidePageFragment.newInstance(
                "Clean App Intros",
                "This library offers developers the ability to add clean app intros at the start of their apps.",
                R.drawable.ic_slide2
        ));

        appIntro.addSlide(SlidePageFragment.newInstance(
                "Simple, yet Customizable",
                "The library offers a lot of customization, while keeping it simple for those that like simple.",
                R.drawable.ic_slide3
        ));

        appIntro.addSlide(SlidePageFragment.newInstance(
                "Explore",
                "Feel free to explore the rest of the library demo!",
                R.drawable.ic_slide4
        ));

        // Fade Transition
        appIntro.setTransformer(AppIntroPageTransformerType.Fade.INSTANCE);

    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        finish();
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        finish();
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onNextPressed(@org.jetbrains.annotations.Nullable Fragment currentFragment) {

    }

    @Override
    public void onNextSlide() {

    }

    @Override
    public void onIntroFinished() {

    }

    @Override
    public void onSlideChanged(@org.jetbrains.annotations.Nullable Fragment oldFragment, @org.jetbrains.annotations.Nullable Fragment newFragment) {

    }

}
