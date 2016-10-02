package com.github.paolorotolo.appintroexample;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.View;
import android.widget.Toast;

import com.github.paolorotolo.appintro.AppIntroFragment;

public final class CustomIntro extends BaseIntro {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addSlide(AppIntroFragment.newInstance("Title here", "Description here...\nYeah, I've added this fragment programmatically",
                R.drawable.ic_slide1, Color.parseColor("#2196F3")));

        addSlide(AppIntroFragment.newInstance("HTML Description", Html.fromHtml("<b>Description bold...</b><br><i>Description italic...</i>"),
                R.drawable.ic_slide1, Color.parseColor("#2196F3")));

        setBarColor(Color.parseColor("#3F51B5"));
        setSeparatorColor(Color.parseColor("#2196F3"));
        showSkipButton(false);

        setVibrate(true);
        setVibrateIntensity(30);
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
}
