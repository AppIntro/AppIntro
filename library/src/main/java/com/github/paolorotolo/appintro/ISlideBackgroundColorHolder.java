package com.github.paolorotolo.appintro;

import android.support.annotation.ColorInt;

public interface ISlideBackgroundColorHolder {
    /**
     * Returns the default background color of the slide
     *
     * @return The default background color of the slide
     */
    @ColorInt
    int getDefaultBackgroundColor();

    /**
     * Sets the actual background color of the slide. This does not affect the default background color.
     * This method should change the background color of the slide's root layout element (e.g. LinearLayout).
     *
     * @param backgroundColor New actual background color.
     */
    void setBackgroundColor(@ColorInt int backgroundColor);
}
