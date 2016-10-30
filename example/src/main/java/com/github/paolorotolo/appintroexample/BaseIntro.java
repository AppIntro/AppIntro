package com.github.paolorotolo.appintroexample;

import android.annotation.SuppressLint;
import android.content.Intent;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintroexample.ui.MainActivity;

/**
 * Created by avluis on 08/08/2016.
 * Shared methods between classes
 */
@SuppressLint("Registered")
public class BaseIntro extends AppIntro {

    protected void loadMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
