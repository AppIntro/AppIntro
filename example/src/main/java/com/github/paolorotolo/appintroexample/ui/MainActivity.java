package com.github.paolorotolo.appintroexample.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.github.paolorotolo.appintroexample.CustomIntro;
import com.github.paolorotolo.appintroexample.CustomTypefaceActivity;
import com.github.paolorotolo.appintroexample.DefaultIntro;
import com.github.paolorotolo.appintroexample.DefaultIntro2;
import com.github.paolorotolo.appintroexample.DisableSwipeIntro1;
import com.github.paolorotolo.appintroexample.DisableSwipeIntro2;
import com.github.paolorotolo.appintroexample.IntroDemoPolicy;
import com.github.paolorotolo.appintroexample.IntroWithBackground;
import com.github.paolorotolo.appintroexample.PermissionsIntro;
import com.github.paolorotolo.appintroexample.PermissionsIntro2;
import com.github.paolorotolo.appintroexample.R;
import com.github.paolorotolo.appintroexample.WizardActivity;
import com.github.paolorotolo.appintroexample.animations.ColorAnimation;
import com.github.paolorotolo.appintroexample.animations.CustomAnimation;
import com.github.paolorotolo.appintroexample.animations.DepthAnimation;
import com.github.paolorotolo.appintroexample.animations.FadeAnimation;
import com.github.paolorotolo.appintroexample.animations.FlowAnimation;
import com.github.paolorotolo.appintroexample.animations.SlideOverAnimation;
import com.github.paolorotolo.appintroexample.animations.ZoomAnimation;
import com.github.paolorotolo.appintroexample.indicators.CustomColorIndicator;
import com.github.paolorotolo.appintroexample.indicators.CustomIndicator;
import com.github.paolorotolo.appintroexample.indicators.ProgressIndicator;

/**
 * Created by avluis on 10/04/2016.
 * Main Activity as a View Pager~
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new CustomPagerAdapter(this));
    }

    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            // Main
            case R.id.app_intro_default_intro:
                intent = new Intent(this, DefaultIntro.class);
                break;
            case R.id.app_intro_layout_2:
                intent = new Intent(this, DefaultIntro2.class);
                break;
            case R.id.app_intro_custom_intro:
                intent = new Intent(this, CustomIntro.class);
                break;
            case R.id.app_intro_custom_background:
                intent = new Intent(this, IntroWithBackground.class);
                break;
            // Permissions
            case R.id.app_intro_permissions:
                intent = new Intent(this, PermissionsIntro.class);
                break;
            case R.id.app_intro_permissions_2:
                intent = new Intent(this, PermissionsIntro2.class);
                break;
            // Animations
            case R.id.app_intro_color_animation:
                intent = new Intent(this, ColorAnimation.class);
                break;
            case R.id.app_intro_fade_animation:
                intent = new Intent(this, FadeAnimation.class);
                break;
            case R.id.app_intro_zoom_animation:
                intent = new Intent(this, ZoomAnimation.class);
                break;
            case R.id.app_intro_flow_animation:
                intent = new Intent(this, FlowAnimation.class);
                break;
            case R.id.app_intro_depth_animation:
                intent = new Intent(this, DepthAnimation.class);
                break;
            case R.id.app_intro_slide_over_animation:
                intent = new Intent(this, SlideOverAnimation.class);
                break;
            case R.id.app_intro_custom_transformer:
                intent = new Intent(this, CustomAnimation.class);
                break;
            // Indicators
            case R.id.app_intro_progress_indicator:
                intent = new Intent(this, ProgressIndicator.class);
                break;
            case R.id.app_intro_custom_indicator:
                intent = new Intent(this, CustomIndicator.class);
                break;
            case R.id.app_intro_custom_color_indicator:
                intent = new Intent(this, CustomColorIndicator.class);
                break;
            // Custom
            case R.id.app_intro_disable_swipe:
                intent = new Intent(this, DisableSwipeIntro1.class);
                break;
            case R.id.app_intro_disable_swipe_2:
                intent = new Intent(this, DisableSwipeIntro2.class);
                break;
            case R.id.app_intro_slide_policy:
                intent = new Intent(this, IntroDemoPolicy.class);
                break;
            case R.id.app_intro_custom_typeface:
                intent = new Intent(this, CustomTypefaceActivity.class);
                break;
            case R.id.app_intro_wizard_mode:
                intent = new Intent(this, WizardActivity.class);
                break;
            default:
                break;
        }

        if (intent != null) {
            startActivity(intent);
        }
    }
}
