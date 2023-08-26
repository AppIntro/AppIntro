package com.github.appintro.model

import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
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
    private var imageDrawable: Int? = null

    @ColorInt
    private var backgroundColor: Int? = null

    @ColorRes
    private var backgroundColorRes: Int? = null

    @ColorInt
    private var titleColor: Int? = null

    @ColorRes
    private var titleColorRes: Int? = null

    @ColorInt
    private var descriptionColor: Int? = null

    @ColorRes
    private var descriptionColorRes: Int? = null

    @FontRes
    private var titleTypefaceFontRes: Int? = null

    @FontRes
    private var descriptionTypefaceFontRes: Int? = null

    private var titleTypeface: String? = null

    private var descriptionTypeface: String? = null

    @DrawableRes
    private var backgroundDrawable: Int? = null

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

    @Deprecated(
        "`backgroundColor(...)` has been deprecated to support configuration changes",
        ReplaceWith("backgroundColorRes(backgroundColor)")
    )
    fun backgroundColor(@ColorInt backgroundColor: Int): SliderPagerBuilder {
        this.backgroundColor = backgroundColor
        return this
    }

    fun backgroundColorRes(@ColorRes backgroundColorRes: Int): SliderPagerBuilder {
        this.backgroundColorRes = backgroundColorRes
        return this
    }

    @Deprecated(
        "`titleColor(...)` has been deprecated to support configuration changes",
        ReplaceWith("titleColorRes(titleColor)")
    )
    fun titleColor(@ColorInt titleColor: Int): SliderPagerBuilder {
        this.titleColor = titleColor
        return this
    }

    fun titleColorRes(@ColorRes titleColorRes: Int): SliderPagerBuilder {
        this.titleColorRes = titleColorRes
        return this
    }

    @Deprecated(
        "`descriptionColor(...)` has been deprecated to support configuration changes",
        ReplaceWith("descriptionColorRes(descriptionColor)")
    )
    fun descriptionColor(@ColorInt descriptionColor: Int): SliderPagerBuilder {
        this.descriptionColor = descriptionColor
        return this
    }

    fun descriptionColorRes(@ColorRes descriptionColorRes: Int): SliderPagerBuilder {
        this.descriptionColorRes = descriptionColorRes
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
        backgroundColorRes = this.backgroundColorRes,
        titleColor = this.titleColor,
        titleColorRes = this.titleColorRes,
        descriptionColor = this.descriptionColor,
        descriptionColorRes = this.descriptionColorRes,
        titleTypefaceFontRes = this.titleTypefaceFontRes,
        descriptionTypeface = this.descriptionTypeface,
        titleTypeface = this.titleTypeface,
        descriptionTypefaceFontRes = this.descriptionTypefaceFontRes,
        backgroundDrawable = this.backgroundDrawable
    )
}
