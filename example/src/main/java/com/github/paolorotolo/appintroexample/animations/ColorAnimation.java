package com.github.paolorotolo.appintroexample.animations;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.view.View;

import com.github.paolorotolo.appintro.AppIntro2;
import com.github.paolorotolo.appintro.AppIntro2Fragment;
import com.github.paolorotolo.appintro.AppIntroFragment;
import com.github.paolorotolo.appintroexample.MainActivity;
import com.github.paolorotolo.appintroexample.R;

import java.util.ArrayList;

/**
 * Created by Girish on 18/01/16.
 */
public class ColorAnimation extends AppIntro2 {

    @Override
    public void init(@Nullable Bundle savedInstanceState) {

        addSlide(AppIntro2Fragment.newInstance("Title 1", "Description here...\nYeah, I've added this fragment programmatically",
                R.drawable.ic_slide1, Color.TRANSPARENT));

        addSlide(AppIntro2Fragment.newInstance("HTML Description", Html.fromHtml("<b>Description bold...</b><br><i>Description italic...</i>"),
                R.drawable.ic_slide1, Color.TRANSPARENT));

        addSlide(AppIntro2Fragment.newInstance("HTML Description", Html.fromHtml("<b>Description bold...</b><br><i>Description italic...</i>"),
                R.drawable.ic_slide1, Color.TRANSPARENT));

        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.parseColor("#FF566A"));
        colors.add(Color.parseColor("#50EB8F"));
        colors.add(Color.parseColor("#3A8BBB"));
        setAnimationColors(colors);
    }

    private void loadMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onDonePressed() {
        loadMainActivity();
    }

    @Override
    public void onNextPressed() {

    }

    @Override
    public void onSlideChanged() {

    }

    public void getStarted(View v){
        loadMainActivity();
    }
}
