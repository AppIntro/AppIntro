package com.github.paolorotolo.appintroexample.animations;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintroexample.MainActivity;
import com.github.paolorotolo.appintroexample.R;
import com.github.paolorotolo.appintroexample.slides.FirstSlide;
import com.github.paolorotolo.appintroexample.slides.FourthSlide;
import com.github.paolorotolo.appintroexample.slides.SecondSlide;
import com.github.paolorotolo.appintroexample.slides.ThirdSlide;

public class FadeAnimation extends AppIntro {
    @Override
    public void init(Bundle savedInstanceState) {
        addSlide(new FirstSlide(), getApplicationContext());
        addSlide(new SecondSlide(), getApplicationContext());
        addSlide(new ThirdSlide(), getApplicationContext());
        addSlide(new FourthSlide(), getApplicationContext());

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