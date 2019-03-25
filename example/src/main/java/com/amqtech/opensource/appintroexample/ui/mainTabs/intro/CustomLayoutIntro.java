package com.amqtech.opensource.appintroexample.ui.mainTabs.intro;

import android.os.Bundle;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.CustomSlide;
import com.github.paolorotolo.appintroexample.R;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * Created by andrew on 11/17/16.
 */

public class CustomLayoutIntro extends AppIntro {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addSlide(CustomSlide.newInstance(R.layout.intro_custom_layout1));
        addSlide(CustomSlide.newInstance(R.layout.intro_custom_layout2));
        addSlide(CustomSlide.newInstance(R.layout.intro_custom_layout3));
        addSlide(CustomSlide.newInstance(R.layout.intro_custom_layout4));

        setProgressIndicator();
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
