package com.github.paolorotolo.appintro.model

import android.graphics.Color
import org.junit.Assert.assertEquals
import org.junit.Test

class SliderPageTest {

    @Test
    fun sliderPage_correctParametersAreAssigned() {

        val title = "Title"
        val description = "Description"
        val imageDrawable = 0x7f020000
        val bgColor = Color.GRAY
        val titleColor = Color.WHITE
        val descColor = Color.BLACK
        val titleTypeface = "robotobold.ttf"
        val descTypeface = "OpenSans-Light.ttf"
        val titleTypefaceRes = 0x12345678
        val descTypefaceRes = 0x789abcdf

        val sliderPage = SliderPage(
                title = title,
                description = description,
                imageDrawable = imageDrawable,
                bgColor = bgColor,
                titleColor = titleColor,
                descColor = descColor,
                titleTypeface = titleTypeface,
                titleTypefaceFontRes = titleTypefaceRes,
                descTypeface = descTypeface,
                descTypefaceFontRes = descTypefaceRes
        )

        assertEquals(sliderPage.title, title)
        assertEquals(sliderPage.description, description)
        assertEquals(sliderPage.imageDrawable, imageDrawable)
        assertEquals(sliderPage.bgColor, bgColor)
        assertEquals(sliderPage.titleColor, titleColor)
        assertEquals(sliderPage.descColor, descColor)
        assertEquals(sliderPage.titleTypeface, titleTypeface)
        assertEquals(sliderPage.descTypeface, descTypeface)
        assertEquals(sliderPage.titleTypefaceFontRes, titleTypefaceRes)
        assertEquals(sliderPage.descTypefaceFontRes, descTypefaceRes)
    }

    @Test
    fun sliderPage_valuesAreDefaulting() {
        val sliderPage = SliderPage()

        assertEquals(sliderPage.title, null)
        assertEquals(sliderPage.description, null)
        assertEquals(sliderPage.imageDrawable, 0)
        assertEquals(sliderPage.bgColor, 0)
        assertEquals(sliderPage.titleColor, 0)
        assertEquals(sliderPage.descColor, 0)
        assertEquals(sliderPage.titleTypeface, null)
        assertEquals(sliderPage.descTypeface, null)
        assertEquals(sliderPage.titleTypefaceFontRes, 0)
        assertEquals(sliderPage.descTypefaceFontRes, 0)
    }

    @Test
    fun sliderPage_titleString_worksAfterReassignment() {
        val title1 : CharSequence = "title1"
        val title2 : CharSequence = "title2"

        val sliderPage = SliderPage(title = title1)
        assertEquals(title1, sliderPage.titleString)
        sliderPage.title = title2
        assertEquals(title2, sliderPage.titleString)
    }

    @Test
    fun sliderPage_titleDescription_worksAfterReassignment() {
        val desc1 : CharSequence = "desc1"
        val desc2 : CharSequence = "desc2"

        val sliderPage = SliderPage(description = desc1)
        assertEquals(desc1, sliderPage.descriptionString)
        sliderPage.description = desc2
        assertEquals(desc2, sliderPage.descriptionString)
    }
}
