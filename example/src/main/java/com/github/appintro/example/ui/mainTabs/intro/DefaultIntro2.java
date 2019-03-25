package com.github.appintro.example.ui.mainTabs.intro;

import android.graphics.Color;
import android.os.Bundle;

import com.github.appintro.example.R;
import com.github.paolorotolo.appintro.AppIntro2;
import com.github.paolorotolo.appintro.AppIntroFragment;
import com.github.paolorotolo.appintro.model.SliderPage;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * Created by andrew on 11/17/16.
 */

public class DefaultIntro2 extends AppIntro2 {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SliderPage sliderPage1 = new SliderPage();
        sliderPage1.setTitle("Welcome!");
        sliderPage1.setDescription("This is a demo of the AppIntro library, using the second layout.");
        sliderPage1.setImageDrawable(R.drawable.ic_slide1);
        sliderPage1.setBgDrawable(R.drawable.back_slide1);
        // A new instance of AppIntro2Fragment is created to get alternative layout.
        addSlide(AppIntroFragment.newInstance(sliderPage1));

        SliderPage sliderPage2 = new SliderPage();
        sliderPage2.setTitle("Dynamic Theme");
        sliderPage2.setDescription("The StatusBar, buttons and the indicator change colors depending on the background color");
        sliderPage2.setImageDrawable(R.drawable.ic_slide1);
        sliderPage2.setBgColor(Color.WHITE);
        sliderPage2.setTitleColor(Color.BLACK);
        sliderPage2.setDescColor(Color.DKGRAY);
        addSlide(AppIntroFragment.newInstance(sliderPage2));

        SliderPage sliderPage3 = new SliderPage();
        sliderPage3.setTitle("Clean App Intros");
        sliderPage3.setDescription("This library offers developers the ability to add clean app intros at the start of their apps.");
        sliderPage3.setImageDrawable(R.drawable.ic_slide2);
        sliderPage3.setBgDrawable(R.drawable.back_slide3);
        addSlide(AppIntroFragment.newInstance(sliderPage3));

        SliderPage sliderPage4 = new SliderPage();
        sliderPage4.setTitle("Simple, yet Customizable");
        sliderPage4.setDescription("The library offers a lot of customization, while keeping it simple for those that like simple.");
        sliderPage4.setTitleTypefaceFontRes(R.font.opensans_regular);
        sliderPage4.setDescTypefaceFontRes(R.font.opensans_regular);
        sliderPage4.setImageDrawable(R.drawable.ic_slide3);
        sliderPage4.setBgDrawable(R.drawable.back_slide4);
        addSlide(AppIntroFragment.newInstance(sliderPage4));

        SliderPage sliderPage5 = new SliderPage();
        sliderPage5.setTitle("Explore");
        sliderPage5.setDescription("Feel free to explore the rest of the library demo!");
        sliderPage5.setImageDrawable(R.drawable.ic_slide4);
        sliderPage5.setBgDrawable(R.drawable.back_slide5);
        addSlide(AppIntroFragment.newInstance(sliderPage5));
        // This call sets parallax effect with default values.
        setParallax(2.0, 3.0, 4.0);

        // This call sets parallax effect with default values.
        // setParallax();
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
