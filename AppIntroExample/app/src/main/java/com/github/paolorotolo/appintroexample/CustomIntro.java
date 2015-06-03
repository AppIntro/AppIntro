package com.github.paolorotolo.appintroexample;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintroexample.slides.FirstSlide;
import com.github.paolorotolo.appintroexample.slides.FourthSlide;
import com.github.paolorotolo.appintroexample.slides.SecondSlide;
import com.github.paolorotolo.appintroexample.slides.ThirdSlide;

public class CustomIntro extends AppIntro {
    @Override
    public void init(Bundle savedInstanceState) {
        addSlide(new FirstSlide(), getApplicationContext());
        addSlide(new SecondSlide(), getApplicationContext());
        addSlide(new ThirdSlide(), getApplicationContext());
        addSlide(new FourthSlide(), getApplicationContext());

        setBarColor(Color.parseColor("#3F51B5"));
        setSeparatorColor(Color.parseColor("#2196F3"));
        showSkipButton(false);
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
    public void onDonePressed() {
        loadMainActivity();
    }

    public void getStarted(View v){
        loadMainActivity();
    }
}