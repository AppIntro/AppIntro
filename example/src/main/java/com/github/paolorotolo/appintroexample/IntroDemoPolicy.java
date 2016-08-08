package com.github.paolorotolo.appintroexample;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.github.paolorotolo.appintroexample.slides.policyDemo.PolicyDemoSlide1;
import com.github.paolorotolo.appintroexample.slides.policyDemo.PolicyDemoSlide2;
import com.github.paolorotolo.appintroexample.slides.policyDemo.PolicyDemoSlide3;

public final class IntroDemoPolicy extends BaseIntro {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setImmersiveMode(true);
        setGoBackLock(true);
        setColorTransitionsEnabled(true);
        showSkipButton(false);

        addSlide(new PolicyDemoSlide1());
        addSlide(new PolicyDemoSlide2());
        addSlide(new PolicyDemoSlide3());
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);

        loadMainActivity();
    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);

        loadMainActivity();
    }
}
