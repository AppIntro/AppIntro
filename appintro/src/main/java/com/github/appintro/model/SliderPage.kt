package com.github.appintro.model

import android.os.Bundle
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.annotation.FontRes
import com.github.appintro.ARG_BG_COLOR
import com.github.appintro.ARG_BG_DRAWABLE
import com.github.appintro.ARG_DESC
import com.github.appintro.ARG_DESC_COLOR
import com.github.appintro.ARG_DESC_TYPEFACE
import com.github.appintro.ARG_DESC_TYPEFACE_RES
import com.github.appintro.ARG_DRAWABLE
import com.github.appintro.ARG_TITLE
import com.github.appintro.ARG_TITLE_COLOR
import com.github.appintro.ARG_TITLE_TYPEFACE
import com.github.appintro.ARG_TITLE_TYPEFACE_RES

/**
 * Slide Page Model.
 *
 * This data class represent a single page that can be visualized with AppIntro.
 */
data class SliderPage @JvmOverloads constructor(
    var title: CharSequence? = null,
    var description: CharSequence? = null,
    @DrawableRes var imageDrawable: Int = 0,
    @ColorInt var backgroundColor: Int = 0,
    @ColorInt var titleColor: Int = 0,
    @ColorInt var descriptionColor: Int = 0,
    @FontRes var titleTypefaceFontRes: Int = 0,
    @FontRes var descriptionTypefaceFontRes: Int = 0,
    var titleTypeface: String? = null,
    var descriptionTypeface: String? = null,
    @DrawableRes var backgroundDrawable: Int = 0
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
        newBundle.putString(ARG_DESC_TYPEFACE, this.descriptionTypeface)
        newBundle.putInt(ARG_DESC_TYPEFACE_RES, this.descriptionTypefaceFontRes)
        newBundle.putInt(ARG_DESC_COLOR, this.descriptionColor)
        newBundle.putInt(ARG_DRAWABLE, this.imageDrawable)
        newBundle.putInt(ARG_BG_COLOR, this.backgroundColor)
        newBundle.putInt(ARG_BG_DRAWABLE, this.backgroundDrawable)
        return newBundle
    }
}
