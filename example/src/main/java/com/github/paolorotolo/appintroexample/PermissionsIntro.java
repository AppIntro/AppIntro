package com.github.paolorotolo.appintroexample;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;

public class PermissionsIntro extends AppIntro {
    @Override
    public void init(Bundle savedInstanceState) {
        addSlide(AppIntroFragment.newInstance("Welcome!", "We just need some permissions to start\n", R.drawable.ic_slide1, Color.parseColor("#2196F3")));
        addSlide(AppIntroFragment.newInstance("Camera permission", "We need camera permission for... \n", R.drawable.ic_slide2, Color.parseColor("#2196F3")));
        addSlide(AppIntroFragment.newInstance("Storage permission", "We need storage permission for... \n", R.drawable.ic_slide3, Color.parseColor("#2196F3")));
        addSlide(AppIntroFragment.newInstance("That's all", "Thanks you! :) \n", R.drawable.ic_slide4, Color.parseColor("#2196F3")));

        String[] myFirstPerm = new String[]{
                Manifest.permission.CAMERA
        };

        String[] mySecondPerm = new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };

        // Ask Camera permission in the first slide
        askForPermissions(myFirstPerm, 2);

        // Ask Storage permission in the second slide
        askForPermissions(mySecondPerm, 3);
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