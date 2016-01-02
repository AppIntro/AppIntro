package com.github.paolorotolo.appintroexample;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Toast;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;

public class CustomIntro extends AppIntro {
    @Override
    public void init(Bundle savedInstanceState) {
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

    private void loadMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onSkipPressed() {
        loadMainActivity();
        Toast.makeText(getApplicationContext(),getString(R.string.skip),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNextPressed() {

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