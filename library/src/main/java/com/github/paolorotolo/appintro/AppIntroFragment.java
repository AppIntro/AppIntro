package com.github.paolorotolo.appintro;

import android.os.Bundle;
import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;

import com.github.paolorotolo.appintro.model.SliderPage;

public final class AppIntroFragment extends AppIntroBaseFragment {

    /**
     * @deprecated Obsolete, use {@link #newInstance(SliderPage)} instead
     */
    public static AppIntroFragment newInstance(CharSequence title, CharSequence description,
                                               @DrawableRes int imageDrawable,
                                               @ColorInt int bgColor) {
        return newInstance(title, null, description, null, imageDrawable, bgColor, 0, 0);
    }

    /**
     * @deprecated Obsolete, use {@link #newInstance(SliderPage)} instead
     */
    public static AppIntroFragment newInstance(CharSequence title, String titleTypeface,
                                               CharSequence description, String descTypeface,
                                               @DrawableRes int imageDrawable,
                                               @ColorInt int bgColor) {
        return newInstance(title, titleTypeface, description, descTypeface, imageDrawable, bgColor,
                0, 0);
    }

    public static AppIntroFragment newInstance(CharSequence title, String titleTypeface,
                                               CharSequence description, String descTypeface,
                                               @DrawableRes int imageDrawable, @ColorInt int bgColor,
                                               @ColorInt int titleColor, @ColorInt int descColor) {
        SliderPage sliderPage = new SliderPage();
        sliderPage.setTitle(title);
        sliderPage.setTitleTypeface(titleTypeface);
        sliderPage.setDescription(description);
        sliderPage.setDescTypeface(descTypeface);
        sliderPage.setImageDrawable(imageDrawable);
        sliderPage.setBgColor(bgColor);
        sliderPage.setTitleColor(titleColor);
        sliderPage.setDescColor(descColor);

        return newInstance(sliderPage);
    }

    public static AppIntroFragment newInstance(SliderPage sliderPage) {
        AppIntroFragment slide = new AppIntroFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TITLE, sliderPage.getTitleString());
        args.putString(ARG_TITLE_TYPEFACE, sliderPage.getTitleTypeface());
        args.putString(ARG_DESC, sliderPage.getDescriptionString());
        args.putString(ARG_DESC_TYPEFACE, sliderPage.getDescTypeface());
        args.putInt(ARG_DRAWABLE, sliderPage.getImageDrawable());
        args.putInt(ARG_BG_COLOR, sliderPage.getBgColor());
        args.putInt(ARG_TITLE_COLOR, sliderPage.getTitleColor());
        args.putInt(ARG_DESC_COLOR, sliderPage.getDescColor());
        slide.setArguments(args);

        return slide;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_intro;
    }
}
