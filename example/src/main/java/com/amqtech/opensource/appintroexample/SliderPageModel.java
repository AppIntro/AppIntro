package com.amqtech.opensource.appintroexample;

import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;

/**
 * Created by tatianasolonets on 3/19/17.
 */

public class SliderPageModel {

    CharSequence title;
    CharSequence description;
    @DrawableRes
    int imageDrawable;
    @ColorInt
    int bgColor;
    @ColorInt
    int titleColor;
    @ColorInt
    int descColor;
    String titleTypeface;
    String descTypeface;

    public void setBgColor( final int bgColor ) {
        this.bgColor = bgColor;
    }

    public void setDescColor( final int descColor ) {
        this.descColor = descColor;
    }

    public void setDescription( final CharSequence description ) {
        this.description = description;
    }

    public void setDescTypeface( final String descTypeface ) {
        this.descTypeface = descTypeface;
    }

    public void setImageDrawable( final int imageDrawable ) {
        this.imageDrawable = imageDrawable;
    }

    public void setTitle( final CharSequence title ) {
        this.title = title;
    }

    public void setTitleColor( final int titleColor ) {
        this.titleColor = titleColor;
    }

    public void setTitleTypeface( final String titleTypeface ) {
        this.titleTypeface = titleTypeface;
    }

    public CharSequence getTitle() {
        return title;
    }

    public String getTitleString() {
        return title != null ? title.toString() : null;
    }

    public CharSequence getDescription() {
        return description;
    }

    public String getDescriptionString() {
        return description != null ? description.toString() : null;
    }

    public int getBgColor() {
        return bgColor;
    }

    public int getDescColor() {
        return descColor;
    }

    public int getImageDrawable() {
        return imageDrawable;
    }

    public int getTitleColor() {
        return titleColor;
    }

    public String getDescTypeface() {
        return descTypeface;
    }

    public String getTitleTypeface() {
        return titleTypeface;
    }
}
