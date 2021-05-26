package com.github.appintro

import androidx.annotation.ColorInt
import androidx.annotation.ColorRes

interface SlideBackgroundColorHolder {

    /**
     * Returns the default background color of the slide
     *
     * @return The default background color of the slide
     */
    @get:ColorInt
    @Deprecated(
        "`defaultBackgroundColor` has been deprecated to support configuration changes",
        ReplaceWith("defaultBackgroundColorRes")
    )
    val defaultBackgroundColor: Int

    /**
     * Returns the default background color of the slide
     *
     * @return The default background color of the slide
     */
    @get:ColorRes
    val defaultBackgroundColorRes: Int

    /**
     * Sets the actual background color of the slide. This does not affect the default background color.
     * This method should change the background color of the slide's root layout element (e.g. LinearLayout).
     *
     * @param backgroundColor New actual background color.
     */
    fun setBackgroundColor(@ColorInt backgroundColor: Int)
}
