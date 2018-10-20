package com.github.paolorotolo.appintro.model

import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.annotation.FontRes

/**
 * A builder to help creating [SliderPage] classes.
 * Please use this class only in Java context. From Kotlin just create
 * a [SliderPage] directly.
 */
class SliderPagerBuilder {

    private var title: CharSequence? = null

    private var description: CharSequence? = null

    @DrawableRes
    private var imageDrawable: Int = 0

    @ColorInt
    private var bgColor: Int = 0

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

    fun title(title: CharSequence): SliderPagerBuilder {
        this.title = title
        return this
    }

    fun description(description: CharSequence): SliderPagerBuilder {
        this.description = description
        return this
    }

    fun imageDrawable(imageDrawable: Int): SliderPagerBuilder {
        this.imageDrawable = imageDrawable
        return this
    }

    fun bgColor(bgColor: Int): SliderPagerBuilder {
        this.bgColor = bgColor
        return this
    }

    fun titleColor(titleColor: Int): SliderPagerBuilder {
        this.titleColor = titleColor
        return this
    }

    fun descColor(descColor: Int): SliderPagerBuilder {
        this.descColor = descColor
        return this
    }

    fun titleTypeface(titleTypeface: String): SliderPagerBuilder {
        this.titleTypeface = titleTypeface
        return this
    }

    fun titleTypefaceRes(@FontRes titleTypefaceRes: Int): SliderPagerBuilder {
        this.titleTypefaceRes = titleTypefaceRes
        return this
    }

    fun descTypeface(descTypeface: String): SliderPagerBuilder {
        this.descTypeface = descTypeface
        return this
    }

    fun descTypefaceRes(@FontRes descTypefaceRes: Int): SliderPagerBuilder {
        this.descTypefaceRes = descTypefaceRes
        return this
    }

    fun build() = SliderPage(
            title = this.title,
            description = this.description,
            imageDrawable = this.imageDrawable,
            bgColor = this.bgColor,
            titleColor = this.titleColor,
            descColor = this.descColor,
            titleTypeface = this.titleTypeface,
            titleTypefaceFontRes = this.titleTypefaceRes,
            descTypeface = this.descTypeface,
            descTypefaceFontRes = this.descTypefaceRes
    )
}
