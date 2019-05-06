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

    class Builder {

        private var title: CharSequence? = null

        private var description: CharSequence? = null

        @DrawableRes
        private var imageDrawable: Int = 0

        @ColorInt
        private var bgColor: Int = 0

        @DrawableRes
        private var bgDrawable: Int = 0

        @ColorInt
        private var titleColor: Int = 0

        @ColorInt
        private var descColor: Int = 0

        private var titleTypeface: String? = null

        private var descTypeface: String? = null

        @FontRes
        private var titleTypefaceRes: Int = 0

        @FontRes
        private var descTypefaceRes: Int = 0

        fun title(title: CharSequence): Builder {
            this.title = title
            return this
        }

        fun description(description: CharSequence): Builder {
            this.description = description
            return this
        }

        fun imageDrawable(imageDrawable: Int): Builder {
            this.imageDrawable = imageDrawable
            return this
        }

        fun bgColor(bgColor: Int): Builder {
            this.bgColor = bgColor
            return this
        }

        fun bgDrawable(bgDrawable: Int): Builder {
            this.bgDrawable = bgDrawable
            return this
        }

        fun titleColor(titleColor: Int): Builder {
            this.titleColor = titleColor
            return this
        }

        fun descColor(descColor: Int): Builder {
            this.descColor = descColor
            return this
        }

        fun titleTypeface(titleTypeface: String): Builder {
            this.titleTypeface = titleTypeface
            return this
        }

        fun titleTypefaceRes(@FontRes titleTypefaceRes: Int): Builder {
            this.titleTypefaceRes = titleTypefaceRes
            return this
        }

        fun descTypeface(descTypeface: String): Builder {
            this.descTypeface = descTypeface
            return this
        }

        fun descTypefaceRes(@FontRes descTypefaceRes: Int): Builder {
            this.descTypefaceRes = descTypefaceRes
            return this
        }

        fun build() = SliderPage(
                title = this.title,
                description = this.description,
                imageDrawable = this.imageDrawable,
                bgDrawable = this.bgDrawable,
                bgColor = this.bgColor,
                titleColor = this.titleColor,
                descColor = this.descColor,
                titleTypeface = this.titleTypeface,
                titleTypefaceFontRes = this.titleTypefaceRes,
                descTypeface = this.descTypeface,
                descTypefaceFontRes = this.descTypefaceRes
        )
    }
}
