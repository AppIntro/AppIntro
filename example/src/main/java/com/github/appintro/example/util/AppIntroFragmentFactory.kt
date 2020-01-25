package com.github.appintro.example.util

import android.graphics.Color
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.annotation.FontRes
import com.github.paolorotolo.appintro.AppIntroFragment
import com.github.paolorotolo.appintro.model.SliderPage

object AppIntroFragmentFactory {
    fun makeFragment(title: String, desc: String,
                     @DrawableRes drawable: Int,
                     @ColorInt background: Int = Color.TRANSPARENT,
                     @FontRes titleFont: Int? = null,
                     @FontRes descFont: Int? = null,
                     @DrawableRes backgroundDrawable: Int? = null,
                     titleTypeface: String? = null,
                     descTypeface: String? = null): AppIntroFragment =
            AppIntroFragment.newInstance(SliderPage().apply {
                this.title = title
                this.description = desc
                this.imageDrawable = drawable
                this.bgColor = background

                titleTypeface?.let { this.titleTypeface = it}
                descTypeface?.let { this.descTypeface = it}

                titleFont?.let { titleTypefaceFontRes = it }
                descFont?.let { descTypefaceFontRes = it }

                backgroundDrawable?.let { bgDrawable = it }
            })
}