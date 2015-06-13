package com.github.paolorotolo.appintroexample;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startDefaultIntro(View v){
        Intent intent = new Intent(this, DefaultIntro.class);
        startActivity(intent);
    }

    public void startCustomIntro(View v){
        Intent intent = new Intent(this, CustomIntro.class);
        startActivity(intent);
    }

    public void startSecondLayoutIntro(View v){
        Intent intent = new Intent(this, SecondLayoutIntro.class);
        startActivity(intent);
    }
}