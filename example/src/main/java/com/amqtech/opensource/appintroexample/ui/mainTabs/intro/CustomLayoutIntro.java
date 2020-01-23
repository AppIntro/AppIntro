package com.amqtech.opensource.appintroexample.ui.mainTabs.intro;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.github.paolorotolo.appintro.AppIntroCustomLayoutSlide;
import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintroexample.R;

public class CustomLayoutIntro extends AppIntro {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addSlide(AppIntroCustomLayoutSlide.newInstance(R.layout.intro_custom_layout1));
        addSlide(AppIntroCustomLayoutSlide.newInstance(R.layout.intro_custom_layout2));
        addSlide(AppIntroCustomLayoutSlide.newInstance(R.layout.intro_custom_layout3));
        addSlide(AppIntroCustomLayoutSlide.newInstance(R.layout.intro_custom_layout4));

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
