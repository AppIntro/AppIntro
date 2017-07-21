package com.amqtech.opensource.appintroexample.ui.mainTabs.intro;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.amqtech.opensource.appintroexample.SliderPageModel;
import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;
import com.github.paolorotolo.appintroexample.R;

/**
 * Created by andrew on 11/17/16.
 */

public class DefaultIntro extends AppIntro {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SliderPageModel sliderPage1 = new SliderPageModel();
        sliderPage1.setTitle( "Welcome!" );
        sliderPage1.setDescription( "This is a demo of the AppIntro library, with a custom background on each slide!" );
        sliderPage1.setImageDrawable( R.drawable.ic_slide1 );
        sliderPage1.setBgColor( Color.TRANSPARENT );
        add(sliderPage1);

        SliderPageModel sliderPage2 = new SliderPageModel();
        sliderPage2.setTitle( "Clean App Intros" );
        sliderPage2.setDescription( "This library offers developers the ability to add clean app intros at the start of their apps." );
        sliderPage2.setImageDrawable(  R.drawable.ic_slide2 );
        sliderPage2.setBgColor( Color.TRANSPARENT );
        add(sliderPage2);

        SliderPageModel sliderPage3 = new SliderPageModel();
        sliderPage3.setTitle( "Simple, yet Customizable" );
        sliderPage3.setDescription( "The library offers a lot of customization, while keeping it simple for those that like simple." );
        sliderPage3.setImageDrawable(  R.drawable.ic_slide3 );
        sliderPage3.setBgColor( Color.TRANSPARENT );
        add(sliderPage3);

        SliderPageModel sliderPage4 = new SliderPageModel();
        sliderPage4.setTitle( "Explore" );
        sliderPage4.setDescription( "Feel free to explore the rest of the library demo!" );
        sliderPage4.setImageDrawable(  R.drawable.ic_slide4 );
        sliderPage4.setBgColor( Color.TRANSPARENT );
        add(sliderPage4);
    }

    public void add(SliderPageModel slide){
        addSlide(AppIntroFragment.newInstance(slide.getTitle(),slide.getDescription(),slide.getImageDrawable(),slide.getBgColor()));
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
