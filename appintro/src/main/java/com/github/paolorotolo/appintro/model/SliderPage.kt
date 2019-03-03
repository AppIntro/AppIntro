package com.github.paolorotolo.appintro.model

import android.os.Bundle
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.annotation.FontRes
import com.github.paolorotolo.appintro.*

/**
 * Slide Page Model.
 *
 * This data class represent a single page that can be visualized with AppIntro.
 */
data class SliderPage @JvmOverloads constructor(
        var title: CharSequence? = null,
        var description: CharSequence? = null,
        @DrawableRes var bgDrawable: Int = 0,
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

    /**
     * Util method to convert a [SliderPage] into an Android [Bundle].
     * This method will be used to pass the [SliderPage] to [AppIntroBaseFragment] implementations.
     */
    fun toBundle(): Bundle {
        val newBundle = Bundle()
        newBundle.putString(ARG_TITLE, this.titleString)
        newBundle.putString(ARG_TITLE_TYPEFACE, this.titleTypeface)
        newBundle.putInt(ARG_TITLE_TYPEFACE_RES, this.titleTypefaceFontRes)
        newBundle.putInt(ARG_TITLE_COLOR, this.titleColor)
        newBundle.putString(ARG_DESC, this.descriptionString)
        newBundle.putString(ARG_DESC_TYPEFACE, this.descTypeface)
        newBundle.putInt(ARG_DESC_TYPEFACE_RES, this.descTypefaceFontRes)
        newBundle.putInt(ARG_DESC_COLOR, this.descColor)
        newBundle.putInt(ARG_DRAWABLE, this.imageDrawable)
        newBundle.putInt(ARG_BG_COLOR, this.bgColor)
        newBundle.putInt(ARG_BG_DRAWABLE, this.bgDrawable)
        return newBundle
    }
}
