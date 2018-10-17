package com.github.paolorotolo.appintro;

import android.os.Bundle;

import com.github.paolorotolo.appintro.model.SliderPage;

import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.FontRes;
import androidx.annotation.NonNull;

public final class AppIntro2Fragment extends AppIntroBaseFragment {

    /**
     * @deprecated Obsolete, use {@link #newInstance(SliderPage)} instead
     */
    @Deprecated
    public static AppIntroFragment newInstance(CharSequence title,
                                               CharSequence description,
                                               @DrawableRes int imageDrawable,
                                               @ColorInt int bgColor) {
        return newInstance(title, description, imageDrawable, bgColor, 0, 0);
    }

    /**
     * Generates new instance for AppIntro2Fragment
     *
     * @param title         CharSequence which will be the slide title
     * @param description   CharSequence which will be the slide description
     * @param imageDrawable @DrawableRes (Integer) the image that will be
     *                      displayed, obtained from Resources
     * @param bgColor       @ColorInt (Integer) custom background color
     * @param titleColor    @ColorInt (Integer) custom title color
     * @param descColor     @ColorInt (Integer) custom description color
     * @return AppIntro2Fragment created instance
     */
    public static AppIntroFragment newInstance(CharSequence title,
                                               CharSequence description,
                                               @DrawableRes int imageDrawable,
                                               @ColorInt int bgColor,
                                               @ColorInt int titleColor,
                                               @ColorInt int descColor) {
        return newInstance(title, null, description, null,
                imageDrawable, bgColor, titleColor, descColor);
    }

    /**
     * @deprecated Obsolete, use {@link #newInstance(SliderPage)} instead
     */
    @Deprecated
    public static AppIntroFragment newInstance(CharSequence title,
                                               String titleTypeface,
                                               CharSequence description,
                                               String descTypeface,
                                               @DrawableRes int imageDrawable,
                                               @ColorInt int bgColor) {
        return newInstance(title, titleTypeface, description, descTypeface,
                imageDrawable, bgColor, 0, 0);
    }

    /**
     * @deprecated Obsolete, use {@link #newInstance(SliderPage)} instead
     */
    @Deprecated
    public static AppIntroFragment newInstance(CharSequence title,
                                               @FontRes int titleTypeface,
                                               CharSequence description,
                                               @FontRes int descTypeface,
                                               @DrawableRes int imageDrawable,
                                               @ColorInt int bgColor) {
        return newInstance(title, titleTypeface, description, descTypeface,
                imageDrawable, bgColor, 0, 0);
    }

    /**
     * Generates new instance for AppIntro2Fragment
     *
     * @param title         CharSequence which will be the slide title
     * @param titleTypeface CharSequence the URL of the custom typeface for
     *                      the title found at assets folder
     * @param description   CharSequence which will be the slide description
     * @param descTypeface  CharSequence the URL of the custom typeface for
     *                      the description found at assets folder
     * @param imageDrawable @DrawableRes (Integer) the image that will be
     *                      displayed, obtained from Resources
     * @param bgColor       @ColorInt (Integer) custom background color
     * @return AppIntro2Fragment created instance
     */
    public static AppIntroFragment newInstance(CharSequence title,
                                               String titleTypeface,
                                               CharSequence description,
                                               String descTypeface,
                                               @DrawableRes int imageDrawable,
                                               @ColorInt int bgColor,
                                               @ColorInt int titleColor,
                                               @ColorInt int descColor) {
        return createInstance(title, titleTypeface, description, descTypeface,
                imageDrawable, bgColor, titleColor, descColor);
    }

    /**
     * Generates new instance for AppIntro2Fragment
     *
     * @param title         CharSequence which will be the slide title
     * @param titleTypeface CharSequence the URL of the custom typeface for
     *                      the title found at assets folder
     * @param description   CharSequence which will be the slide description
     * @param descTypeface  CharSequence the URL of the custom typeface for
     *                      the description found at assets folder
     * @param imageDrawable @DrawableRes (Integer) the image that will be
     *                      displayed, obtained from Resources
     * @param bgColor       @ColorInt (Integer) custom background color
     * @param titleColor    @ColorInt (Integer) custom title color
     * @param descColor     @ColorInt (Integer) custom description color
     * @return AppIntro2Fragment created instance
     */
    public static AppIntroFragment newInstance(CharSequence title,
                                               @FontRes int titleTypeface,
                                               CharSequence description,
                                               @FontRes int descTypeface,
                                               @DrawableRes int imageDrawable,
                                               @ColorInt int bgColor,
                                               @ColorInt int titleColor,
                                               @ColorInt int descColor) {
        return createInstance(title, titleTypeface, description, descTypeface,
                imageDrawable, bgColor, titleColor, descColor);
    }

    private static AppIntroFragment createInstance(CharSequence title,
                                                   Object titleTypeface,
                                                   CharSequence description,
                                                   Object descTypeface,
                                                   @DrawableRes int imageDrawable,
                                                   @ColorInt int bgColor,
                                                   @ColorInt int titleColor,
                                                   @ColorInt int descColor) {
        SliderPage sliderPage = new SliderPage();
        sliderPage.setTitle(title);
        if (titleTypeface instanceof Integer)
            sliderPage.setTitleTypefaceFontRes((int) titleTypeface);
        else
            sliderPage.setTitleTypeface((String) titleTypeface);
        sliderPage.setDescription(description);
        if (descTypeface instanceof Integer)
            sliderPage.setDescTypefaceFontRes((int) descTypeface);
        else
            sliderPage.setDescTypeface((String) descTypeface);
        sliderPage.setImageDrawable(imageDrawable);
        sliderPage.setBgColor(bgColor);
        sliderPage.setTitleColor(titleColor);
        sliderPage.setDescColor(descColor);
        return newInstance(sliderPage);
    }

    /**
     * Generates an AppIntro2Fragment by a given SliderPage
     *
     * @param sliderPage the SliderPage object which contains all attributes for
     *                   the current slide
     * @return AppIntro2Fragment created instance
     */
    public static AppIntroFragment newInstance(@NonNull SliderPage sliderPage) {
        AppIntroFragment slides = new AppIntroFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TITLE, sliderPage.getTitleString());
        if (sliderPage.getTitleTypefaceFontRes() != 0)
            args.putInt(ARG_TITLE_TYPEFACE, sliderPage.getTitleTypefaceFontRes());
        else
            args.putString(ARG_TITLE_TYPEFACE, sliderPage.getDescTypeface());
        args.putString(ARG_DESC, sliderPage.getDescriptionString());
        if (sliderPage.getTitleTypefaceFontRes() != 0)
            args.putInt(ARG_DESC_TYPEFACE, sliderPage.getDescTypefaceFontRes());
        else
            args.putString(ARG_DESC_TYPEFACE, sliderPage.getDescTypeface());
        args.putInt(ARG_DRAWABLE, sliderPage.getImageDrawable());
        args.putInt(ARG_BG_COLOR, sliderPage.getBgColor());
        args.putInt(ARG_TITLE_COLOR, sliderPage.getTitleColor());
        args.putInt(ARG_DESC_COLOR, sliderPage.getDescColor());
        slides.setArguments(args);

        return slides;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.appintro_fragment_intro2;
    }
}
