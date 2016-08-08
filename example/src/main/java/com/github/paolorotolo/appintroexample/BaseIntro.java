package com.github.paolorotolo.appintroexample;

import android.content.Intent;

import com.github.paolorotolo.appintro.AppIntro;

/**
 * Created by avluis on 08/08/2016.
 * Shared methods between classes
 */
public class BaseIntro extends AppIntro {

    void loadMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
