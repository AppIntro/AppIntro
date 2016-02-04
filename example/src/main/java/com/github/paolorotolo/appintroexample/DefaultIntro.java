package com.github.paolorotolo.appintroexample;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;

public class DefaultIntro extends AppIntro {
    @Override
    public void init(Bundle savedInstanceState) {
        // color must be resource ID
        addSlide(AppIntroFragment.newInstance("Page 1", getResources().getString(R.string.app_about),
				R.drawable.ic_launcher, R.color.primary_app), R.color.primary_app);
		addSlide(AppIntroFragment.newInstance("Page 2", getResources().getString(R.string.app_about),
				R.drawable.ic_launcher, R.color.primary_app_dark), R.color.primary_app_dark);
		addSlide(AppIntroFragment.newInstance("Page 3", getResources().getString(R.string.about),
				R.drawable.ic_launcher, R.color.red_error), R.color.red_error);
		addSlide(AppIntroFragment.newInstance("Page 4", getResources().getString(R.string.about),
				R.drawable.ic_launcher, R.color.accent2), R.color.accent2);
		addSlide(AppIntroFragment.newInstance("Page 5", getResources().getString(R.string.about),
				R.drawable.ic_launcher, R.color.accent), R.color.accent);
				
		int color = R.color.primary_app;
		setNavBarColor(color);
    }

    private void loadMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onNextPressed() {
    }

    @Override
    public void onSkipPressed() {
        loadMainActivity();
        Toast.makeText(getApplicationContext(),
                getString(R.string.skip), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDonePressed() {
        loadMainActivity();
    }

    @Override
    public void onSlideChanged() {
    }

    public void getStarted(View v){
        loadMainActivity();
    }
}
