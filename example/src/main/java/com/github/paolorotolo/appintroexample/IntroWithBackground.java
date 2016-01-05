package com.github.paolorotolo.appintroexample;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.github.paolorotolo.appintro.AppIntro2;
import com.github.paolorotolo.appintro.AppIntroFragment;

public class IntroWithBackground extends AppIntro2 {
    @Override
    public void init(Bundle savedInstanceState) {

        addSlide(AppIntroFragment.newInstance("Title here", "Description here...\nYeah, I've added this fragment programmatically",
                R.drawable.ic_slide1, Color.TRANSPARENT));

        addSlide(AppIntroFragment.newInstance("HTML Description", Html.fromHtml("<b>Description bold...</b><br><i>Description italic...</i>"),
            R.drawable.ic_slide1, Color.TRANSPARENT));

        ImageView imageView = new ImageView(this);
        imageView.setImageResource(android.R.drawable.ic_dialog_email);
        imageView.setBackgroundColor(Color.BLACK);
        imageView.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        setBackgroundView(imageView);

        setVibrate(true);
        setVibrateIntensity(30);
    }

    private void loadMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
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