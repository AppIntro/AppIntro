package com.github.appintro.model

import android.graphics.Color
import org.junit.Assert.assertEquals
import org.junit.Test

class SliderPageBuilderTest {

    @Test
    fun sliderPageBuilder_correctParametersAssignment() {
        val title = "Title"
        val description = "Description"
        val imageDrawable = 0x7f020000
        val bgDrawable = 0x7f020000
        val bgColor = Color.GRAY
        val titleColor = Color.WHITE
        val descColor = Color.BLACK
        val titleTypeface = "robotobold.ttf"
        val descTypeface = "OpenSans-Light.ttf"
        val titleTypefaceRes = 0x12345678
        val descTypefaceRes = 0x789abcdf

        val sliderPage =
            SliderPagerBuilder()
                .title(title)
                .description(description)
                .imageDrawable(imageDrawable)
                .backgroundColor(bgColor)
                .backgroundDrawable(bgDrawable)
                .titleColor(titleColor)
                .descriptionColor(descColor)
                .titleTypeface(titleTypeface)
                .descriptionTypeface(descTypeface)
                .titleTypefaceFontRes(titleTypefaceRes)
                .descriptionTypefaceFontRes(descTypefaceRes)
                .build()

        assertEquals(sliderPage.title, title)
        assertEquals(sliderPage.description, description)
        assertEquals(sliderPage.imageDrawable, imageDrawable)
        assertEquals(sliderPage.backgroundColor, bgColor)
        assertEquals(sliderPage.backgroundDrawable, bgDrawable)
        assertEquals(sliderPage.titleColor, titleColor)
        assertEquals(sliderPage.descriptionColor, descColor)
        assertEquals(sliderPage.titleTypeface, titleTypeface)
        assertEquals(sliderPage.descriptionTypeface, descTypeface)
        assertEquals(sliderPage.titleTypefaceFontRes, titleTypefaceRes)
        assertEquals(sliderPage.descriptionTypefaceFontRes, descTypefaceRes)
    }

    @Test
    fun sliderPageBuilder_multipleAssignmentOverrides() {
        val sliderPage =
            SliderPagerBuilder()
                .title("title")
                .title("title2")
                .build()

        assertEquals(sliderPage.title, "title2")
    }

    @Test
    fun sliderPageBuilder_valuesAreDefaulting() {
        val sliderPage = SliderPagerBuilder().build()
        assertEquals(sliderPage.title, null)
        assertEquals(sliderPage.description, null)
        assertEquals(sliderPage.imageDrawable, 0)
        assertEquals(sliderPage.backgroundColor, 0)
        assertEquals(sliderPage.backgroundDrawable, 0)
        assertEquals(sliderPage.titleColor, 0)
        assertEquals(sliderPage.descriptionColor, 0)
        assertEquals(sliderPage.titleTypeface, null)
        assertEquals(sliderPage.descriptionTypeface, null)
        assertEquals(sliderPage.titleTypefaceFontRes, 0)
        assertEquals(sliderPage.descriptionTypefaceFontRes, 0)
    }
}
