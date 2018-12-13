package com.github.paolorotolo.appintro;

import android.os.Bundle;

import com.github.paolorotolo.appintro.model.SliderPage;

import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.FontRes;
import androidx.annotation.NonNull;

public final class AppIntroFragment extends AppIntroBaseFragment {

    /**
     * @deprecated Obsolete, use {@link #newInstance(SliderPage)} instead
     */
    @Deprecated
    public static AppIntroFragment newInstance(CharSequence title,
                                               CharSequence description,
                                               @DrawableRes int imageDrawable,
                                               @ColorInt int bgColor) {
        return newInstance(title, null, description, null,
                imageDrawable, bgColor, 0, 0);
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
     * @param title         CharSequence which will be the slide title
     * @param titleTypeface @FontRes (Integer) custom title typeface obtained
     *                      from Resources
     * @param description   CharSequence which will be the slide description
     * @param descTypeface  @FontRes (Integer) custom description typeface
     *                      obtained from Resources
     * @param imageDrawable @DrawableRes (Integer) the image that will be
     *                      displayed, obtained from Resources
     * @param bgColor       @ColorInt (Integer) custom background color
     * @return AppIntroFragment created instance
     * @deprecated Obsolete, use {@link #newInstance(SliderPage)} instead
     * Generates new instance for AppIntroFragment
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
     * Generates new instance for AppIntroFragment
     *
     * @param title            CharSequence which will be the slide title
     * @param titleTypefaceUrl CharSequence the URL of the custom typeface for
     *                         the title found at assets folder
     * @param description      CharSequence which will be the slide description
     * @param descTypefaceUrl  CharSequence the URL of the custom typeface for
     *                         the description found at assets folder
     * @param imageDrawable    @DrawableRes (Integer) the image that will be
     *                         displayed, obtained from Resources
     * @param bgColor          @ColorInt (Integer) custom background color
     * @return AppIntroFragment created instance
     */
    public static AppIntroFragment newInstance(CharSequence title,
                                               String titleTypefaceUrl,
                                               CharSequence description,
                                               String descTypefaceUrl,
                                               @DrawableRes int imageDrawable,
                                               @ColorInt int bgColor,
                                               @ColorInt int titleColor,
                                               @ColorInt int descColor) {
        SliderPage sliderPage = new SliderPage();
        sliderPage.setTitle(title);
        sliderPage.setTitleTypeface(titleTypefaceUrl);
        sliderPage.setDescription(description);
        sliderPage.setDescTypeface(descTypefaceUrl);
        sliderPage.setImageDrawable(imageDrawable);
        sliderPage.setBgColor(bgColor);
        sliderPage.setTitleColor(titleColor);
        sliderPage.setDescColor(descColor);
        return newInstance(sliderPage);
    }

    /**
     * Generates new instance for AppIntroFragment
     *
     * @param title            CharSequence which will be the slide title
     * @param titleTypefaceRes @FontRes (Integer) custom title typeface obtained
     *                         from Resources
     * @param description      CharSequence which will be the slide description
     * @param descTypefaceRes  @FontRes (Integer) custom description typeface
     *                         obtained from Resources
     * @param imageDrawable    @DrawableRes (Integer) the image that will be
     *                         displayed, obtained from Resources
     * @param bgColor          @ColorInt (Integer) custom background color
     * @param titleColor       @ColorInt (Integer) custom title color
     * @param descColor        @ColorInt (Integer) custom description color
     * @return AppIntroFragment created instance
     */
    public static AppIntroFragment newInstance(CharSequence title,
                                               @FontRes int titleTypefaceRes,
                                               CharSequence description,
                                               @FontRes int descTypefaceRes,
                                               @DrawableRes int imageDrawable,
                                               @ColorInt int bgColor,
                                               @ColorInt int titleColor,
                                               @ColorInt int descColor) {
        SliderPage sliderPage = new SliderPage();
        sliderPage.setTitle(title);
        sliderPage.setTitleTypefaceFontRes(titleTypefaceRes);
        sliderPage.setDescription(description);
        sliderPage.setDescTypefaceFontRes(descTypefaceRes);
        sliderPage.setImageDrawable(imageDrawable);
        sliderPage.setBgColor(bgColor);
        sliderPage.setTitleColor(titleColor);
        sliderPage.setDescColor(descColor);
        return newInstance(sliderPage);
    }

    /**
     * Generates an AppIntroFragment by a given SliderPage
     *
     * @param sliderPage SliderPage which contains all attributes for the
     *                   current slide
     * @return AppIntroFragment created instance
     */
    public static AppIntroFragment newInstance(@NonNull SliderPage sliderPage) {
        AppIntroFragment slide = new AppIntroFragment();
        Bundle args = new Bundle();

        args.putString(ARG_TITLE, sliderPage.getTitleString());
        args.putString(ARG_TITLE_TYPEFACE, sliderPage.getTitleTypeface());
        args.putInt(ARG_TITLE_TYPEFACE_RES, sliderPage.getTitleTypefaceFontRes());
        args.putInt(ARG_TITLE_COLOR, sliderPage.getTitleColor());

        args.putString(ARG_DESC, sliderPage.getDescriptionString());
        args.putString(ARG_DESC_TYPEFACE, sliderPage.getDescTypeface());
        args.putInt(ARG_DESC_TYPEFACE_RES, sliderPage.getDescTypefaceFontRes());
        args.putInt(ARG_DESC_COLOR, sliderPage.getDescColor());

        args.putInt(ARG_DRAWABLE, sliderPage.getImageDrawable());
        args.putInt(ARG_BG_COLOR, sliderPage.getBgColor());

        slide.setArguments(args);
        return slide;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.appintro_fragment_intro;
    }
}
