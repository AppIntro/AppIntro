package com.github.paolorotolo.appintroexample;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Toast;

import com.github.paolorotolo.appintro.AppIntroViewPager;

public final class DisableSwipeIntro1 extends BaseIntro {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addSlide(SampleSlide.newInstance(R.layout.intro_disable));
        addSlide(SampleSlide.newInstance(R.layout.intro2_disable));
        addSlide(SampleSlide.newInstance(R.layout.intro3_disable));
    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);

        loadMainActivity();
        Toast.makeText(getApplicationContext(), getString(R.string.skip), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);

        loadMainActivity();
    }

    public void getStarted(View v) {
        loadMainActivity();
    }

    public void toggleNextPageSwipeLock(View v) {
        AppIntroViewPager pager = getPager();
        boolean pagingState = pager.isNextPagingEnabled();
        setNextPageSwipeLock(pagingState);
    }

    public void toggleSwipeLock(View v) {
        AppIntroViewPager pager = getPager();
        boolean pagingState = pager.isPagingEnabled();
        setSwipeLock(pagingState);
    }

    public void toggleProgressButton(View v) {
        boolean progressButtonState = isProgressButtonEnabled();
        progressButtonState = !progressButtonState;
        setProgressButtonEnabled(progressButtonState);
    }

    public void toggleSkipButton(View v) {
        boolean skipButtonState = isSkipButtonEnabled();
        skipButtonState = !skipButtonState;
        showSkipButton(skipButtonState);
    }
}
