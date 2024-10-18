package com.github.appintro.model

import com.appintro.core.models.SliderPage
import org.junit.Assert.assertEquals
import org.junit.Test

class SliderPageTest {
    @Test
    fun sliderPage_correctParametersAreAssigned() {
        val title = "Title"
        val description = "Description"
        val imageDrawable = 0x7f020000
        val bgColorRes = 0x7f020001
        val titleColorRes = 0x7f020002
        val descColorRes = 0x7f020003
        val titleTypeface = "robotobold.ttf"
        val descTypeface = "OpenSans-Light.ttf"
        val titleTypefaceRes = 0x12345678
        val descTypefaceRes = 0x789abcdf

        val sliderPage =
            SliderPage(
                title = title,
                description = description,
                imageDrawable = imageDrawable,
                backgroundColorRes = bgColorRes,
                titleColorRes = titleColorRes,
                descriptionColorRes = descColorRes,
                titleTypeface = titleTypeface,
                titleTypefaceFontRes = titleTypefaceRes,
                descriptionTypeface = descTypeface,
                descriptionTypefaceFontRes = descTypefaceRes,
            )

        assertEquals(sliderPage.title, title)
        assertEquals(sliderPage.description, description)
        assertEquals(sliderPage.imageDrawable, imageDrawable)
        assertEquals(sliderPage.backgroundColorRes, bgColorRes)
        assertEquals(sliderPage.titleColorRes, titleColorRes)
        assertEquals(sliderPage.descriptionColorRes, descColorRes)
        assertEquals(sliderPage.titleTypeface, titleTypeface)
        assertEquals(sliderPage.descriptionTypeface, descTypeface)
        assertEquals(sliderPage.titleTypefaceFontRes, titleTypefaceRes)
        assertEquals(sliderPage.descriptionTypefaceFontRes, descTypefaceRes)
    }

    @Test
    fun sliderPage_valuesAreDefaulting() {
        val sliderPage = SliderPage()

        assertEquals(sliderPage.title, null)
        assertEquals(sliderPage.description, null)
        assertEquals(sliderPage.imageDrawable, null)
        assertEquals(sliderPage.backgroundColorRes, null)
        assertEquals(sliderPage.titleColorRes, null)
        assertEquals(sliderPage.descriptionColorRes, null)
        assertEquals(sliderPage.titleTypeface, null)
        assertEquals(sliderPage.descriptionTypeface, null)
        assertEquals(sliderPage.titleTypefaceFontRes, null)
        assertEquals(sliderPage.descriptionTypefaceFontRes, null)
    }

    @Test
    fun sliderPage_titleString_worksAfterReassignment() {
        val title1: CharSequence = "title1"
        val title2: CharSequence = "title2"

        val sliderPage = SliderPage(title = title1)
        assertEquals(title1, sliderPage.titleString)
        sliderPage.title = title2
        assertEquals(title2, sliderPage.titleString)
    }

    @Test
    fun sliderPage_titleDescription_worksAfterReassignment() {
        val desc1: CharSequence = "desc1"
        val desc2: CharSequence = "desc2"

        val sliderPage = SliderPage(description = desc1)
        assertEquals(desc1, sliderPage.descriptionString)
        sliderPage.description = desc2
        assertEquals(desc2, sliderPage.descriptionString)
    }
}
