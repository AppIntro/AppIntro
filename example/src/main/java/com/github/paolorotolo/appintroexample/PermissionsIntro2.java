/*
 * Copyright (c) Andrew Quebe 2015 .
 */

package com.github.paolorotolo.appintroexample;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.github.paolorotolo.appintro.AppIntro2;
import com.github.paolorotolo.appintro.AppIntro2Fragment;
import com.github.paolorotolo.appintro.AppIntroFragment;

public final class PermissionsIntro2 extends AppIntro2 {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addSlide(AppIntro2Fragment.newInstance("Welcome!", "We just need some permissions to start. (This is only as an example...this app doesn't utilize any of the perms.)\n", R.drawable.ic_slide1, Color.parseColor("#2196F3")));
        addSlide(AppIntro2Fragment.newInstance("Camera", "We need to use the camera.\n", R.drawable.ic_slide2, Color.parseColor("#2196F3")));
        addSlide(AppIntro2Fragment.newInstance("Storage", "We need to save stuff on your device. \n", R.drawable.ic_slide3, Color.parseColor("#2196F3")));
        addSlide(AppIntro2Fragment.newInstance("All Set!", "Enjoy our app! \n", R.drawable.ic_slide4, Color.parseColor("#2196F3")));
        addSlide(AppIntro2Fragment.newInstance("Location", "One more permission! We need to locate your device. \n", R.drawable.ic_slide4, Color.parseColor("#2196F3")));
        addSlide(AppIntro2Fragment.newInstance("All set!", "All done! \n", R.drawable.ic_slide4, Color.parseColor("#2196F3")));

        // Ask Camera permission in the second slide
        askForPermissions(new String[]{Manifest.permission.CAMERA}, 2);

        // Ask Storage permission in the third slide
        askForPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 3);

        // Ask Location permission in the fifth slide
        askForPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 5);
    }

    private void loadMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);

        loadMainActivity();
    }

    public void getStarted(View v) {
        loadMainActivity();
    }
}