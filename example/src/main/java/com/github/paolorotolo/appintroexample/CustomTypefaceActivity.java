package com.github.paolorotolo.appintroexample;

import android.graphics.Color;
<<<<<<< HEAD
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;

public class CustomTypefaceActivity extends AppIntro {
=======
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.github.paolorotolo.appintro.AppIntroFragment;

public class CustomTypefaceActivity extends BaseIntro {
>>>>>>> PaoloRotolo/master

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
<<<<<<< HEAD
        //setContentView(R.layout.activity_custom_typeface);
        addSlide(AppIntroFragment.newInstance("Welcome",getResources().getString(R.string.title_font),"Using custom typeface",getResources().getString(R.string.desc_font),R.drawable.planet_earth, Color.parseColor("#27ae60")));
        addSlide(AppIntroFragment.newInstance("No typeface",null,"Using custom typeface",getResources().getString(R.string.desc_font),R.drawable.cloudy, Color.parseColor("#34495e")));
        addSlide(AppIntroFragment.newInstance("Using typeface",getResources().getString(R.string.title_font),"No typeface",null,R.drawable.full_moon, Color.parseColor("#c0392b")));
        addSlide(AppIntroFragment.newInstance("Ending",getResources().getString(R.string.title_font),"Applying typeface to Done button",getResources().getString(R.string.desc_font),R.drawable.planet_earth, Color.parseColor("#27ae60")));
        setSkipTextTypeface(getResources().getString(R.string.title_font));

=======

        addSlide(AppIntroFragment.newInstance("Welcome",
                getResources().getString(R.string.title_font), "Using custom typeface",
                getResources().getString(R.string.desc_font), R.drawable.ic_slide1,
                Color.parseColor("#27ae60")));
        addSlide(AppIntroFragment.newInstance("No typeface", null, "Using custom typeface",
                getResources().getString(R.string.desc_font), R.drawable.ic_slide2,
                Color.parseColor("#34495e")));
        addSlide(AppIntroFragment.newInstance("Using typeface",
                getResources().getString(R.string.title_font), "No typeface", null,
                R.drawable.ic_slide3, Color.parseColor("#c0392b")));
        addSlide(AppIntroFragment.newInstance("Ending",
                getResources().getString(R.string.title_font), "Applying typeface to Done button",
                getResources().getString(R.string.desc_font), R.drawable.ic_slide4,
                Color.parseColor("#27ae60")));

        setSkipTextTypeface(getResources().getString(R.string.title_font));
>>>>>>> PaoloRotolo/master
        setDoneTextTypeface(getResources().getString(R.string.title_font));
    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
<<<<<<< HEAD
        loadMainActivity();
    }

    @Override
    public void onSlideChanged(@Nullable Fragment oldFragment, @Nullable Fragment newFragment) {

=======

        loadMainActivity();
>>>>>>> PaoloRotolo/master
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
<<<<<<< HEAD
        loadMainActivity();
    }

    private void loadMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
=======

        loadMainActivity();
>>>>>>> PaoloRotolo/master
    }
}
