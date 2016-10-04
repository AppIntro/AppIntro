package com.github.paolorotolo.appintroexample;

import android.annotation.SuppressLint;
import android.content.Intent;

import com.github.paolorotolo.appintro.AppIntro2;
import com.github.paolorotolo.appintroexample.ui.MainActivity;

/**
 * Created by avluis on 08/08/2016.
 * Shared methods between classes
 */
@SuppressLint("Registered")
public class BaseIntro2 extends AppIntro2 {

    protected void loadMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
