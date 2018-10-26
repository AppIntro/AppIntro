package com.github.paolorotolo.appintroexample

import android.graphics.Color
import com.github.paolorotolo.appintro.model.SliderPagerBuilder
import org.junit.Assert.assertEquals
import org.junit.Test

class SliderPageBuilderTest {

    @Test
    fun sliderPageBuilder_correctParametersAssignment() {

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

        val sliderPage = SliderPagerBuilder()
                .title(title)
                .description(description)
                .imageDrawable(imageDrawable)
                .bgColor(bgColor)
                .titleColor(titleColor)
                .descColor(descColor)
                .titleTypeface(titleTypeface)
                .descTypeface(descTypeface)
                .titleTypefaceRes(titleTypefaceRes)
                .descTypefaceRes(descTypefaceRes)
                .build()

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
    fun sliderPageBuilder_multipleAssignmentOverrides() {
        val sliderPage = SliderPagerBuilder()
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
        assertEquals(sliderPage.bgColor, 0)
        assertEquals(sliderPage.titleColor, 0)
        assertEquals(sliderPage.descColor, 0)
        assertEquals(sliderPage.titleTypeface, null)
        assertEquals(sliderPage.descTypeface, null)
        assertEquals(sliderPage.titleTypefaceFontRes, 0)
        assertEquals(sliderPage.descTypefaceFontRes, 0)
    }
}
