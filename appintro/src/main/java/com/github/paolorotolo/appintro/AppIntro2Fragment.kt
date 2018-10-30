package com.github.paolorotolo.appintro

import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.annotation.FontRes
import com.github.paolorotolo.appintro.model.SliderPage

class AppIntro2Fragment : AppIntroBaseFragment() {

    override val layoutId: Int get() = R.layout.appintro_fragment_intro2

    companion object {
        /**
         * Generates new instance for AppIntro2Fragment
         *
         * @param title         CharSequence which will be the slide title
         * @param description   CharSequence which will be the slide description
         * @param imageDrawable @DrawableRes (Integer) the image that will be
         * displayed, obtained from Resources
         * @param bgColor       @ColorInt (Integer) custom background color
         * @param titleColor    @ColorInt (Integer) custom title color
         * @param descColor     @ColorInt (Integer) custom description color
         * @return AppIntro2Fragment created instance
         */
        @JvmOverloads
        @JvmStatic
        fun newInstance(
                title: CharSequence? = null,
                description: CharSequence? = null,
                @DrawableRes imageDrawable: Int = 0,
                @ColorInt bgColor: Int = 0,
                @ColorInt titleColor: Int = 0,
                @ColorInt descColor: Int = 0,
                @FontRes titleTypefaceFontRes: Int = 0,
                @FontRes descTypefaceFontRes: Int = 0
        ): AppIntro2Fragment {
            return newInstance(
                    SliderPage(
                            title = title,
                            description = description,
                            imageDrawable = imageDrawable,
                            bgColor = bgColor,
                            titleColor = titleColor,
                            descColor = descColor,
                            titleTypefaceFontRes = titleTypefaceFontRes,
                            descTypefaceFontRes = descTypefaceFontRes
                            )
            )
        }

        /**
         * Generates an AppIntro2Fragment by a given SliderPage
         *
         * @param sliderPage the SliderPage object which contains all attributes for
         * the current slide
         * @return AppIntro2Fragment created instance
         */
        @JvmStatic
        fun newInstance(sliderPage: SliderPage): AppIntro2Fragment {
            val slide = AppIntro2Fragment()
            slide.arguments = sliderPage.toBundle()
            return slide
        }
    }
}