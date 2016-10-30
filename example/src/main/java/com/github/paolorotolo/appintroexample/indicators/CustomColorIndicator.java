package com.github.paolorotolo.appintroexample.indicators;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.github.paolorotolo.appintroexample.BaseIntro2;
import com.github.paolorotolo.appintroexample.R;
import com.github.paolorotolo.appintroexample.SampleSlide;

public class CustomColorIndicator extends BaseIntro2 {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addSlide(SampleSlide.newInstance(R.layout.intro));
        addSlide(SampleSlide.newInstance(R.layout.intro2));
        addSlide(SampleSlide.newInstance(R.layout.intro3));
        addSlide(SampleSlide.newInstance(R.layout.intro4));

        setIndicatorColor(Color.parseColor("#ff0000"), Color.parseColor("#00ff00"));
        showSkipButton(false);
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        loadMainActivity();
    }

    public void getStarted(View v) {
        loadMainActivity();
    }
}
