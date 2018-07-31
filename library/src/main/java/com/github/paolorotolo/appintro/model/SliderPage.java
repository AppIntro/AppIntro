package com.github.paolorotolo.appintro.model;

import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;

/**
 * Created by tatianasolonets on 3/19/17.
 * Slide Page Model
 */

public class SliderPage {
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
    private String titleTypeface;
    private String descTypeface;

    public CharSequence getTitle() {
        return title;
    }

    public void setTitle(final CharSequence title) {
        this.title = title;
    }

    public String getTitleString() {
        return title != null ? title.toString() : null;
    }

    public CharSequence getDescription() {
        return description;
    }

    public void setDescription(final CharSequence description) {
        this.description = description;
    }

    public String getDescriptionString() {
        return description != null ? description.toString() : null;
    }

    public int getBgColor() {
        return bgColor;
    }

    public void setBgColor(@ColorInt final int bgColor) {
        this.bgColor = bgColor;
    }

    public int getDescColor() {
        return descColor;
    }

    public void setDescColor(@ColorInt final int descColor) {
        this.descColor = descColor;
    }

    public int getImageDrawable() {
        return imageDrawable;
    }

    public void setImageDrawable(@DrawableRes final int imageDrawable) {
        this.imageDrawable = imageDrawable;
    }

    public int getTitleColor() {
        return titleColor;
    }

    public void setTitleColor(@ColorInt final int titleColor) {
        this.titleColor = titleColor;
    }

    public String getDescTypeface() {
        return descTypeface;
    }

    public void setDescTypeface(final String descTypeface) {
        this.descTypeface = descTypeface;
    }

    public String getTitleTypeface() {
        return titleTypeface;
    }

    public void setTitleTypeface(final String titleTypeface) {
        this.titleTypeface = titleTypeface;
    }
}
