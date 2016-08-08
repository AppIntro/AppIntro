package com.github.paolorotolo.appintroexample;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.github.paolorotolo.appintro.AppIntroFragment;

public class CustomTypefaceTwoActivity extends BaseIntro2 {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_custom_typeface);
        addSlide(AppIntroFragment.newInstance("Welcome", getResources().getString(R.string.title_font), "Using custom typeface", getResources().getString(R.string.desc_font), R.drawable.planet_earth, Color.parseColor("#27ae60")));
        addSlide(AppIntroFragment.newInstance("No typeface", null, "Using custom typeface", getResources().getString(R.string.desc_font), R.drawable.cloudy, Color.parseColor("#34495e")));
        addSlide(AppIntroFragment.newInstance("Using typeface", getResources().getString(R.string.title_font), "No typeface", null, R.drawable.full_moon, Color.parseColor("#c0392b")));

        addSlide(AppIntroFragment.newInstance("Ending", getResources().getString(R.string.title_font), "Applying typeface to Done button", getResources().getString(R.string.desc_font), R.drawable.planet_earth, Color.parseColor("#27ae60")));
    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        loadMainActivity();
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);

        loadMainActivity();
    }
}
