package com.github.paolorotolo.appintroexample.animations;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintroexample.MainActivity;
import com.github.paolorotolo.appintroexample.R;
import com.github.paolorotolo.appintroexample.SampleSlide;
import com.github.paolorotolo.appintroexample.slides.FirstSlide;
import com.github.paolorotolo.appintroexample.slides.FourthSlide;
import com.github.paolorotolo.appintroexample.slides.SecondSlide;
import com.github.paolorotolo.appintroexample.slides.ThirdSlide;

public class FadeAnimation extends BaseAppIntro {
    @Override
    public void init(Bundle savedInstanceState) {
        addSlide(SampleSlide.newInstance(R.layout.intro));
        addSlide(SampleSlide.newInstance(R.layout.intro2));
        addSlide(SampleSlide.newInstance(R.layout.intro3));
        addSlide(SampleSlide.newInstance(R.layout.intro4));

        setFadeAnimation();
    }

    private void loadMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onSkipPressed() {
        loadMainActivity();
        Toast.makeText(getApplicationContext(), getString(R.string.skip), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDonePressed() {
        loadMainActivity();
    }

    public void getStarted(View v){
        loadMainActivity();
    }
}