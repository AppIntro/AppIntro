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


        addSlide(AppIntroFragment.createInstance("Welcome!",
                "This is a demo example in java of AppIntro library, with a custom background on each slide!",
                R.drawable.ic_slide1,
                R.color.appintro_example_orange
        ));

        addSlide(AppIntroFragment.createInstance(
                "Clean App Intros",
                "This library offers developers the ability to add clean app intros at the start of their apps.",
                R.drawable.ic_slide2,
                R.color.appintro_example_lime
        ));

        addSlide(AppIntroFragment.createInstance(
                "Simple, yet Customizable",
                "The library offers a lot of customization, while keeping it simple for those that like simple.",
                R.drawable.ic_slide3,
                R.color.appintro_example_blue
        ));

        addSlide(AppIntroFragment.createInstance(
                "Explore",
                "Feel free to explore the rest of the library demo!",
                R.drawable.ic_slide4,
                R.color.appintro_example_blue_gray
        ));

        // Fade Transition
        setTransformer(AppIntroPageTransformerType.Fade.INSTANCE);
        
        // Show/hide status bar
        showStatusBar(true);
        //Enable the color "fade" animation between two slides (make sure the slide implements SlideBackgroundColorHolder)
        setColorTransitionsEnabled(true);

        //Prevent the back button from exiting the slides
        setSystemBackButtonLocked(true);
        
        //Activate wizard mode (Some aesthetic changes)
        setWizardMode(true);
        
        //Enable immersive mode (no status and nav bar)
        setImmersiveMode();
        
        //Enable/disable page indicators
        setIndicatorEnabled(true);
        
        //Dhow/hide ALL buttons
        setButtonsEnabled(true);

        // Enable Vibration
        setVibrate(true);
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
