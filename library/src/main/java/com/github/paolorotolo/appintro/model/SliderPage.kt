package com.github.paolorotolo.appintro.model

import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.annotation.FontRes

/**
 * Slide Page Model
 */
data class SliderPage @JvmOverloads constructor(
        var title: CharSequence? = null,
        var description: CharSequence? = null,
        @DrawableRes var imageDrawable: Int = 0,
        @ColorInt var bgColor: Int = 0,
        @ColorInt var titleColor: Int = 0,
        @ColorInt var descColor: Int = 0,
        @FontRes var titleTypefaceFontRes: Int = 0,
        @FontRes var descTypefaceFontRes: Int = 0,
        var titleTypeface: String? = null,
        var descTypeface: String? = null
) {
    val titleString: String? get() = title?.toString()
    val descriptionString: String? get() = description?.toString()
}