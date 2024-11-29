package dev.appintro.core.models

import android.os.Bundle
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.FontRes


/**
 * Slide Page Model.
 *
 * This data class represent a single page that can be visualized with AppIntro.
 */
internal const val ARG_TITLE = "title"
internal const val ARG_TITLE_TYPEFACE_URL = "title_typeface"
internal const val ARG_TITLE_TYPEFACE_RES = "title_typeface_res"
internal const val ARG_DESC = "desc"
internal const val ARG_DESC_TYPEFACE_URL = "desc_typeface"
internal const val ARG_DESC_TYPEFACE_RES = "desc_typeface_res"
internal const val ARG_DRAWABLE = "drawable"
internal const val ARG_BG_COLOR = "bg_color"
internal const val ARG_BG_COLOR_RES = "bg_color_res"
internal const val ARG_TITLE_COLOR = "title_color"
internal const val ARG_TITLE_COLOR_RES = "title_color_res"
internal const val ARG_DESC_COLOR = "desc_color"
internal const val ARG_DESC_COLOR_RES = "desc_color_res"
internal const val ARG_BG_DRAWABLE = "bg_drawable"
data class SliderPage
    @JvmOverloads
    constructor(
        var title: CharSequence? = null,
        var description: CharSequence? = null,
        @DrawableRes var imageDrawable: Int? = null,
        @ColorInt
        @Deprecated(
            "`backgroundColor` has been deprecated to support configuration changes",
            ReplaceWith("backgroundColorRes"),
        )
        var backgroundColor: Int? = null,
        @ColorInt
        @Deprecated(
            "`titleColor` has been deprecated to support configuration changes",
            ReplaceWith("titleColorRes"),
        )
        var titleColor: Int? = null,
        @ColorInt
        @Deprecated(
            "`descriptionColor` has been deprecated to support configuration changes",
            ReplaceWith("descriptionColorRes"),
        )
        var descriptionColor: Int? = null,
        @ColorRes var backgroundColorRes: Int? = null,
        @ColorRes var titleColorRes: Int? = null,
        @ColorRes var descriptionColorRes: Int? = null,
        @FontRes var titleTypefaceFontRes: Int? = null,
        @FontRes var descriptionTypefaceFontRes: Int? = null,
        var titleTypeface: String? = null,
        var descriptionTypeface: String? = null,
        @DrawableRes var backgroundDrawable: Int? = null,
    ) {
        val titleString: CharSequence? get() = title
        val descriptionString: CharSequence? get() = description

        /**
         * Util method to convert a [SliderPage] into an Android [Bundle].
         * This method will be used to pass the [SliderPage] to [AppIntroBaseFragment] implementations.
         */
        @Suppress("DEPRECATION")
        fun toBundle(): Bundle {
            val newBundle = Bundle()
            newBundle.putCharSequence(ARG_TITLE, this.titleString)
            newBundle.putString(ARG_TITLE_TYPEFACE_URL, this.titleTypeface)
            newBundle.putCharSequence(ARG_DESC, this.descriptionString)
            newBundle.putString(ARG_DESC_TYPEFACE_URL, this.descriptionTypeface)

            this.titleTypefaceFontRes?.let { newBundle.putInt(ARG_TITLE_TYPEFACE_RES, it) }
            this.titleColor?.let { newBundle.putInt(ARG_TITLE_COLOR, it) }
            this.titleColorRes?.let { newBundle.putInt(ARG_TITLE_COLOR_RES, it) }
            this.descriptionTypefaceFontRes?.let { newBundle.putInt(ARG_DESC_TYPEFACE_RES, it) }
            this.descriptionColor?.let { newBundle.putInt(ARG_DESC_COLOR, it) }
            this.descriptionColorRes?.let { newBundle.putInt(ARG_DESC_COLOR_RES, it) }
            this.imageDrawable?.let { newBundle.putInt(ARG_DRAWABLE, it) }
            this.backgroundColor?.let { newBundle.putInt(ARG_BG_COLOR, it) }
            this.backgroundColorRes?.let { newBundle.putInt(ARG_BG_COLOR_RES, it) }
            this.backgroundDrawable?.let { newBundle.putInt(ARG_BG_DRAWABLE, it) }

            return newBundle
        }
    }
