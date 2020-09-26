package com.github.app.javaexample.ui.permissions;

import android.Manifest;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.github.app.javaexample.R;
import com.github.appintro.AppIntro;
import com.github.appintro.AppIntroFragment;


public class PermissionsIntro2 extends AppIntro {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addSlide(AppIntroFragment.newInstance(
                "Welcome!",
                "This is a demo of the AppIntro library, with permissions being requested on a slide!",
                 R.drawable.ic_slide1));

        addSlide(AppIntroFragment.newInstance(
                "Welcome!",
                "In order to access your camera, you must give permissions.",
                 R.drawable.ic_slide2));

        addSlide(AppIntroFragment.newInstance(
                "Simple, yet Customizable",
                "The library offers a lot of customization, while keeping it simple for those that like simple.",
                 R.drawable.ic_slide3));

        addSlide(AppIntroFragment.newInstance(
                "Explore",
                "Feel free to explore the rest of the library demo!",
                 R.drawable.ic_slide4));

        // Request mandatory camera and location permission
        askForPermissions(
                new String[]{Manifest.permission.CAMERA, Manifest.permission.ACCESS_FINE_LOCATION},
                2, true);

        // Request optional storage permission
        askForPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                3, false);
    }

    @Override
    protected void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        finish();
    }

    @Override
    protected void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        finish();
    }
}
