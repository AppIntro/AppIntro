package com.github.paolorotolo.appintroexample.ui.mainTabs.intro;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.github.paolorotolo.appintro.AppIntro2;
import com.github.paolorotolo.appintro.AppIntroFragment;
import com.github.paolorotolo.appintroexample.R;

/**
 * Created by andrew on 11/17/16.
 */

public class CustomBackgroundIntro extends AppIntro2 {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addSlide(AppIntroFragment.newInstance("Welcome!", "This is a demo of the AppIntro library, with a custom background on each slide!", R.drawable.ic_slide1, Color.TRANSPARENT));
        addSlide(AppIntroFragment.newInstance("Clean App Intros", "This library offers developers the ability to add clean app intros at the start of their apps.", R.drawable.ic_slide2, Color.TRANSPARENT));
        addSlide(AppIntroFragment.newInstance("Simple, yet Customizable", "The library offers a lot of customization, while keeping it simple for those that like simple.", R.drawable.ic_slide3, Color.TRANSPARENT));
        addSlide(AppIntroFragment.newInstance("Explore", "Feel free to explore the rest of the library demo!", R.drawable.ic_slide4, Color.TRANSPARENT));

        // Declare a new image view
        ImageView imageView = new ImageView(this);

        // Bind a drawable to the imageview
        imageView.setImageResource(R.drawable.ic_sample_bg);

        // Set background color
        imageView.setBackgroundColor(Color.BLACK);

        // Set layout params
        imageView.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        // Bind the background to the intro
        setBackgroundView(imageView);
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
