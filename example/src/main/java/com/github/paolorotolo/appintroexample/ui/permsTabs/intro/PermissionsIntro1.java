package com.github.paolorotolo.appintroexample.ui.permsTabs.intro;

import android.Manifest;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;
import com.github.paolorotolo.appintroexample.R;

/**
 * Created by andrew on 11/17/16.
 */

public class PermissionsIntro1 extends AppIntro {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addSlide(AppIntroFragment.newInstance("Welcome!", "This is a demo of the AppIntro library, with permissions being requested on a slide..", R.drawable.ic_slide1, Color.parseColor("#1976D2")));
        addSlide(AppIntroFragment.newInstance("Permission Request", "In order to access your camera, you must give permissions.", R.drawable.ic_slide2, Color.parseColor("#1976D2")));
        addSlide(AppIntroFragment.newInstance("Simple, yet Customizable", "The library offers a lot of customization, while keeping it simple for those that like simple.", R.drawable.ic_slide3, Color.parseColor("#1976D2")));
        addSlide(AppIntroFragment.newInstance("Explore", "Feel free to explore the rest of the library demo!", R.drawable.ic_slide4, Color.parseColor("#1976D2")));

        // Here we load a string array with a camera permission, and tell the library to request permissions on slide 2
        askForPermissions(new String[]{Manifest.permission.CAMERA}, 2);
    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        finish();
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        finish();
    }
}
