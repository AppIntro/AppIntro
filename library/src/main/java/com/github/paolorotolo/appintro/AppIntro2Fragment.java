package com.github.paolorotolo.appintro;

import android.os.Bundle;
import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.FontRes;
import com.github.paolorotolo.appintro.model.SliderPage;

public final class AppIntro2Fragment extends AppIntroBaseFragment {
    public static AppIntroFragment newInstance(CharSequence title, CharSequence description,
                                               @DrawableRes int imageDrawable,
                                               @ColorInt int bgColor) {
        return newInstance(title, description, imageDrawable, bgColor, 0, 0);
    }

    public static AppIntroFragment newInstance(CharSequence title, CharSequence description,
                                               @DrawableRes int imageDrawable, @ColorInt int bgColor,
                                               @ColorInt int titleColor, @ColorInt int descColor) {
        return newInstance(title, null, description, null, imageDrawable, bgColor, titleColor,
                descColor);
    }

    public static AppIntroFragment newInstance(CharSequence title, String titleTypeface,
                                               CharSequence description, String descTypeface,
                                               @DrawableRes int imageDrawable,
                                               @ColorInt int bgColor) {
        return newInstance(title, titleTypeface, description, descTypeface, imageDrawable, bgColor,
                0, 0);
    }

    public static AppIntroFragment newInstance(CharSequence title, @FontRes int titleTypeface,
                                               CharSequence description, @FontRes int descTypeface,
                                               @DrawableRes int imageDrawable,
                                               @ColorInt int bgColor) {
        return newInstance(title, titleTypeface, description, descTypeface, imageDrawable, bgColor,
                0, 0);
    }

    public static AppIntroFragment newInstance(CharSequence title, String titleTypeface,
                                               CharSequence description, String descTypeface,
                                               @DrawableRes int imageDrawable, @ColorInt int bgColor,
                                               @ColorInt int titleColor, @ColorInt int descColor) {
        return createInstance(title, titleTypeface, description, descTypeface, imageDrawable, bgColor, titleColor,
                descColor);
    }

    public static AppIntroFragment newInstance(CharSequence title, @FontRes int titleTypeface,
                                               CharSequence description, @FontRes int descTypeface,
                                               @DrawableRes int imageDrawable, @ColorInt int bgColor,
                                               @ColorInt int titleColor, @ColorInt int descColor) {
        return createInstance(title, titleTypeface, description, descTypeface, imageDrawable, bgColor, titleColor,
                descColor);
    }

    private static AppIntroFragment createInstance(CharSequence title, Object titleTypeface,
                                                   CharSequence description, Object descTypeface,
                                                   @DrawableRes int imageDrawable, @ColorInt int bgColor,
                                                   @ColorInt int titleColor, @ColorInt int descColor) {
        AppIntroFragment slide = new AppIntroFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TITLE, title.toString());
        if (titleTypeface instanceof Integer)
            args.putInt(ARG_TITLE_TYPEFACE, (int) titleTypeface);
        else
            args.putString(ARG_TITLE_TYPEFACE, (String) titleTypeface);
        args.putString(ARG_DESC, description.toString());
        if (descTypeface instanceof Integer)
            args.putInt(ARG_DESC_TYPEFACE, (int) descTypeface);
        else
            args.putString(ARG_DESC_TYPEFACE, (String) descTypeface);
        args.putInt(ARG_DRAWABLE, imageDrawable);
        args.putInt(ARG_BG_COLOR, bgColor);
        args.putInt(ARG_TITLE_COLOR, titleColor);
        args.putInt(ARG_DESC_COLOR, descColor);
        slide.setArguments(args);

        return slide;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.appintro_fragment_intro2;
    }
}
