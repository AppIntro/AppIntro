package com.github.paolorotolo.appintroexample;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.github.paolorotolo.appintro.AppIntro2;
import com.github.paolorotolo.appintroexample.slides2.FirstSlide2;
import com.github.paolorotolo.appintroexample.slides2.SecondSlide2;
import com.github.paolorotolo.appintroexample.slides2.ThirdSlide2;

public class SecondLayoutIntro extends AppIntro2 {
    @Override
    public void init(Bundle savedInstanceState) {
        addSlide(new FirstSlide2(), getApplicationContext());
        addSlide(new SecondSlide2(), getApplicationContext());
        addSlide(new ThirdSlide2(), getApplicationContext());
    }

    private void loadMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onDonePressed() {
        loadMainActivity();
    }

    public void getStarted(View v){
        loadMainActivity();
    }
}
