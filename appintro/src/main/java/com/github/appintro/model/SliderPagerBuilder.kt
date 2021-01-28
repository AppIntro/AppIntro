package com.github.appintro.model

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
    private var backgroundColor: Int = 0

    @ColorInt
    private var titleColor: Int = 0

    @ColorInt
    private var descriptionColor: Int = 0

    @FontRes
    private var titleTypefaceFontRes: Int = 0

    @FontRes
    private var descriptionTypefaceFontRes: Int = 0

    private var titleTypeface: String? = null

    private var descriptionTypeface: String? = null

    @DrawableRes
    private var backgroundDrawable: Int = 0

    fun title(title: CharSequence): SliderPagerBuilder {
        this.title = title
        return this
    }

    fun description(description: CharSequence): SliderPagerBuilder {
        this.description = description
        return this
    }

    fun imageDrawable(@DrawableRes imageDrawable: Int): SliderPagerBuilder {
        this.imageDrawable = imageDrawable
        return this
    }

    fun backgroundColor(@ColorInt backgroundColor: Int): SliderPagerBuilder {
        this.backgroundColor = backgroundColor
        return this
    }

    fun titleColor(@ColorInt titleColor: Int): SliderPagerBuilder {
        this.titleColor = titleColor
        return this
    }

    fun descriptionColor(@ColorInt descriptionColor: Int): SliderPagerBuilder {
        this.descriptionColor = descriptionColor
        return this
    }

    fun titleTypefaceFontRes(@FontRes titleTypefaceFontRes: Int): SliderPagerBuilder {
        this.titleTypefaceFontRes = titleTypefaceFontRes
        return this
    }

    fun descriptionTypefaceFontRes(@FontRes descriptionTypefaceFontRes: Int): SliderPagerBuilder {
        this.descriptionTypefaceFontRes = descriptionTypefaceFontRes
        return this
    }

    fun titleTypeface(titleTypeface: String): SliderPagerBuilder {
        this.titleTypeface = titleTypeface
        return this
    }

    fun descriptionTypeface(descriptionTypeface: String): SliderPagerBuilder {
        this.descriptionTypeface = descriptionTypeface
        return this
    }

    fun backgroundDrawable(@DrawableRes backgroundDrawable: Int): SliderPagerBuilder {
        this.backgroundDrawable = backgroundDrawable
        return this
    }

    fun build() = SliderPage(
        title = this.title,
        description = this.description,
        imageDrawable = this.imageDrawable,
        backgroundColor = this.backgroundColor,
        titleColor = this.titleColor,
        descriptionColor = this.descriptionColor,
        titleTypefaceFontRes = this.titleTypefaceFontRes,
        descriptionTypeface = this.descriptionTypeface,
        titleTypeface = this.titleTypeface,
        descriptionTypefaceFontRes = this.descriptionTypefaceFontRes,
        backgroundDrawable = this.backgroundDrawable
    )
}
