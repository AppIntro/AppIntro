package com.github.paolorotolo.appintro.model;

import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.FontRes;

/**
 * Created by Mike on 21/02/2018.
 */

public class SliderPagerBuilder {

    private CharSequence title;

    private CharSequence description;

    @DrawableRes
    private int imageDrawable;

    @ColorInt
    private int bgColor;

    @ColorInt
    private int titleColor;

    @ColorInt
    private int descColor;

    private Object titleTypeface;

    private Object descTypeface;

    public SliderPagerBuilder(){

    }

    public SliderPagerBuilder title(CharSequence title){

        this.title = title;
        return this;
    }

    public SliderPagerBuilder description(CharSequence description){

        this.description = description;
        return this;
    }

    public SliderPagerBuilder imageDrawable(int imageDrawable){

        this.imageDrawable = imageDrawable;
        return this;
    }

    public SliderPagerBuilder bgColor(int bgColor){

        this.bgColor = bgColor;
        return this;
    }

    public SliderPagerBuilder titleColor(int titleColor){

        this.titleColor = titleColor;
        return  this;
    }

    public SliderPagerBuilder descColor(int descColor){

        this.descColor = descColor;
        return this;
    }

    public SliderPagerBuilder titleTypeface(String titleTypeface){
        this.titleTypeface = titleTypeface;
        return this;
    }

    public SliderPagerBuilder titleTypeface(@FontRes int titleTypeface) {
        this.titleTypeface = titleTypeface;
        return this;
    }

    public SliderPagerBuilder descTypeface(String descTypeface){

        this.descTypeface = descTypeface;
        return this;
    }

    public SliderPagerBuilder descTypeface(@FontRes int descTypeface) {
        this.descTypeface = descTypeface;
        return this;
    }

    public SliderPage build(){

        SliderPage sliderPage = new SliderPage();
        sliderPage.setTitle(this.title);
        sliderPage.setDescription(this.description);
        sliderPage.setImageDrawable(this.imageDrawable);
        sliderPage.setBgColor(this.bgColor);
        sliderPage.setTitleColor(this.titleColor);
        sliderPage.setDescColor(this.descColor);
        sliderPage.setTitleTypeface(this.titleTypeface);
        sliderPage.setDescTypeface(this.descTypeface);

        return sliderPage;
    }
}
