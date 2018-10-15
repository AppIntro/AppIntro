package com.github.paolorotolo.appintro;

import android.os.Bundle;

import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.FontRes;

public final class AppIntro2Fragment extends AppIntroBaseFragment {
    /**
     * @deprecated Obsolete, use
     * {@link #newInstance(CharSequence, int, CharSequence, int, int, int)}
     * or {@link #newInstance(CharSequence, String, CharSequence, String, int, int, int, int)} instead
     */
    @Deprecated
    public static AppIntroFragment newInstance(CharSequence title, CharSequence description,
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
    public static AppIntroFragment newInstance(CharSequence title, CharSequence description,
                                               @DrawableRes int imageDrawable, @ColorInt int bgColor,
                                               @ColorInt int titleColor, @ColorInt int descColor) {
        return newInstance(title, null, description, null, imageDrawable, bgColor, titleColor,
                descColor);
    }

    /**
     * @deprecated Obsolete, use
     * {@link #newInstance(CharSequence, int, CharSequence, int, int, int)}
     * or {@link #newInstance(CharSequence, String, CharSequence, String, int, int, int, int)} instead
     */
    @Deprecated
    public static AppIntroFragment newInstance(CharSequence title, String titleTypeface,
                                               CharSequence description, String descTypeface,
                                               @DrawableRes int imageDrawable,
                                               @ColorInt int bgColor) {
        return newInstance(title, titleTypeface, description, descTypeface, imageDrawable, bgColor,
                0, 0);
    }

    /**
     * Generates new instance for AppIntro2Fragment
     *
     * @param title         CharSequence which will be the slide title
     * @param titleTypeface @FontRes (Integer) custom title typeface obtained
     *                      from Resources
     * @param description   CharSequence which will be the slide description
     * @param descTypeface  @FontRes (Integer) custom description typeface obtained
     *                      from Resources
     *                      the description found at assets folder
     * @param imageDrawable @DrawableRes (Integer) the image that will be
     *                      displayed, obtained from Resources
     * @param bgColor       @ColorInt (Integer) custom background color
     * @return AppIntro2Fragment created instance
     */
    public static AppIntroFragment newInstance(CharSequence title, @FontRes int titleTypeface,
                                               CharSequence description, @FontRes int descTypeface,
                                               @DrawableRes int imageDrawable,
                                               @ColorInt int bgColor) {
        return newInstance(title, titleTypeface, description, descTypeface, imageDrawable, bgColor,
                0, 0);
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
    public static AppIntroFragment newInstance(CharSequence title, String titleTypeface,
                                               CharSequence description, String descTypeface,
                                               @DrawableRes int imageDrawable, @ColorInt int bgColor,
                                               @ColorInt int titleColor, @ColorInt int descColor) {
        return createInstance(title, titleTypeface, description, descTypeface, imageDrawable, bgColor, titleColor,
                descColor);
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
