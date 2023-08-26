package com.github.appintro

import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.FontRes
import com.github.appintro.model.SliderPage

@Suppress("LongParameterList")
class AppIntroFragment : AppIntroBaseFragment() {

    override val layoutId: Int get() = R.layout.appintro_fragment_intro

    companion object {

        /**
         * Generates a new instance for [AppIntroFragment]
         *
         * @param title CharSequence which will be the slide title
         * @param description CharSequence which will be the slide description
         * @param imageDrawable @DrawableRes (Integer) the image that will be
         *                             displayed, obtained from Resources
         * @param backgroundColor @ColorInt (Integer) custom background color
         * @param titleColor @ColorInt (Integer) custom title color
         * @param descriptionColor @ColorInt (Integer) custom description color
         * @param titleTypefaceFontRes @FontRes (Integer) custom title typeface obtained
         *                             from Resources
         * @param descriptionTypefaceFontRes @FontRes (Integer) custom description typeface obtained
         *                             from Resources
         * @param backgroundDrawable @DrawableRes (Integer) custom background drawable
         *
         * @return An [AppIntroFragment] created instance
         */
        @JvmOverloads
        @JvmStatic
        @Deprecated(
            "`newInstance` is deprecated to support color resources instead of color int " +
                "for configuration changes and dark theme support",
            ReplaceWith(
                "createInstance(title, description, imageDrawable, backgroundColor, " +
                    "titleColor, descriptionColor, titleTypefaceFontRes, descriptionTypefaceFontRes, " +
                    "backgroundDrawable)"
            )
        )
        fun newInstance(
            title: CharSequence? = null,
            description: CharSequence? = null,
            @DrawableRes imageDrawable: Int? = null,
            @ColorInt backgroundColor: Int? = null,
            @ColorInt titleColor: Int? = null,
            @ColorInt descriptionColor: Int? = null,
            @FontRes titleTypefaceFontRes: Int? = null,
            @FontRes descriptionTypefaceFontRes: Int? = null,
            @DrawableRes backgroundDrawable: Int? = null
        ): AppIntroFragment {
            return createInstance(
                SliderPage(
                    title = title,
                    description = description,
                    imageDrawable = imageDrawable,
                    backgroundColor = backgroundColor,
                    titleColor = titleColor,
                    descriptionColor = descriptionColor,
                    titleTypefaceFontRes = titleTypefaceFontRes,
                    descriptionTypefaceFontRes = descriptionTypefaceFontRes,
                    backgroundDrawable = backgroundDrawable
                )
            )
        }

        /**
         * Generates a new instance for [AppIntroFragment]
         *
         * @param title CharSequence which will be the slide title
         * @param description CharSequence which will be the slide description
         * @param imageDrawable @DrawableRes (Integer) the image that will be
         *                             displayed, obtained from Resources
         * @param backgroundColorRes @ColorRes (Integer) custom background color
         * @param titleColorRes @ColorRes (Integer) custom title color
         * @param descriptionColorRes @ColorRes (Integer) custom description color
         * @param titleTypefaceFontRes @FontRes (Integer) custom title typeface obtained
         *                             from Resources
         * @param descriptionTypefaceFontRes @FontRes (Integer) custom description typeface obtained
         *                             from Resources
         * @param backgroundDrawable @DrawableRes (Integer) custom background drawable
         *
         * @return An [AppIntroFragment] created instance
         */
        @JvmOverloads
        @JvmStatic
        fun createInstance(
            title: CharSequence? = null,
            description: CharSequence? = null,
            @DrawableRes imageDrawable: Int? = null,
            @ColorRes backgroundColorRes: Int? = null,
            @ColorRes titleColorRes: Int? = null,
            @ColorRes descriptionColorRes: Int? = null,
            @FontRes titleTypefaceFontRes: Int? = null,
            @FontRes descriptionTypefaceFontRes: Int? = null,
            @DrawableRes backgroundDrawable: Int? = null
        ): AppIntroFragment {
            return createInstance(
                SliderPage(
                    title = title,
                    description = description,
                    imageDrawable = imageDrawable,
                    backgroundColorRes = backgroundColorRes,
                    titleColorRes = titleColorRes,
                    descriptionColorRes = descriptionColorRes,
                    titleTypefaceFontRes = titleTypefaceFontRes,
                    descriptionTypefaceFontRes = descriptionTypefaceFontRes,
                    backgroundDrawable = backgroundDrawable
                )
            )
        }

        /**
         * Generates an [AppIntroFragment] from a given [SliderPage]
         *
         * @param sliderPage the [SliderPage] object which contains all attributes for
         * the current slide
         *
         * @return An [AppIntroFragment] created instance
         */
        @JvmStatic
        @Deprecated(
            "`newInstance` is deprecated to support color resources instead of color int " +
                "for configuration changes and dark theme support",
            ReplaceWith(
                "createInstance(sliderPage)"
            )
        )
        fun newInstance(sliderPage: SliderPage) = createInstance(sliderPage)

        /**
         * Generates an [AppIntroFragment] from a given [SliderPage]
         *
         * @param sliderPage the [SliderPage] object which contains all attributes for
         * the current slide
         *
         * @return An [AppIntroFragment] created instance
         */
        @JvmStatic
        fun createInstance(sliderPage: SliderPage): AppIntroFragment {
            val slide = AppIntroFragment()
            slide.arguments = sliderPage.toBundle()
            return slide
        }
    }
}
