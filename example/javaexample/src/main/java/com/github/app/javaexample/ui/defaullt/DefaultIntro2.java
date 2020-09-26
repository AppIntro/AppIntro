package com.github.app.javaexample.ui.defaullt;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.github.app.javaexample.R;
import com.github.appintro.AppIntro2;
import com.github.appintro.AppIntroFragment;
import com.github.appintro.AppIntroPageTransformerType;
import com.github.appintro.model.SliderPage;


public class DefaultIntro2 extends AppIntro2 {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        addSlide(AppIntroFragment.newInstance(
                "Welcome!",
                "This is a demo of the AppIntro library, using the second layout.",
                R.drawable.ic_slide1,
                0,
                0,
                0,
                R.font.lato,
                R.font.lato,
                R.drawable.back_slide1
        ));

        addSlide(AppIntroFragment.newInstance(new SliderPage(
                "Gradients!",
                "This text is written on a gradient background",
                R.drawable.ic_slide2,
                0,
                0,
                0,
                R.font.opensans_regular,
                R.font.opensans_regular,
                null,
                null,
                R.drawable.back_slide2
        )));

        addSlide(AppIntroFragment.newInstance(
                "Simple, yet Customizable",
                "The library offers a lot of customization, while keeping it simple for those that like simple.",
                R.drawable.ic_slide3,
                0,
                0,
                0,
                R.font.opensans_regular,
                R.font.opensans_regular,
                R.drawable.back_slide3
        ));

        addSlide(AppIntroFragment.newInstance(
                "Explore",
                "Feel free to explore the rest of the library demo!",
                R.drawable.ic_slide4,
                0,
                0,
                0,
                0,
                0,
                R.drawable.back_slide4
        ));

        addSlide(AppIntroFragment.newInstance(
                ":)",
                "...gradients are awesome!",
                R.mipmap.ic_launcher,
                0,
                0,
                0,
                0,
                0,
                R.drawable.back_slide5
        ));

        setTransformer(new AppIntroPageTransformerType.Parallax());
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
