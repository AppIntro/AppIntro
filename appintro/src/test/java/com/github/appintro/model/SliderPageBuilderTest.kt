package com.github.appintro.model

import com.appintro.core.models.SliderPagerBuilder
import org.junit.Assert.assertEquals
import org.junit.Test

class SliderPageBuilderTest {
    @Test
    fun sliderPageBuilder_correctParametersAssignment() {
        val title = "Title"
        val description = "Description"
        val imageDrawable = 0x7f020000
        val bgDrawable = 0x7f020000
        val bgColorRes = 0x7f020001
        val titleColorRes = 0x7f020002
        val descColorRes = 0x7f020003
        val titleTypeface = "robotobold.ttf"
        val descTypeface = "OpenSans-Light.ttf"
        val titleTypefaceRes = 0x12345678
        val descTypefaceRes = 0x789abcdf

        val sliderPage =
            SliderPagerBuilder()
                .title(title)
                .description(description)
                .imageDrawable(imageDrawable)
                .backgroundColorRes(bgColorRes)
                .backgroundDrawable(bgDrawable)
                .titleColorRes(titleColorRes)
                .descriptionColorRes(descColorRes)
                .titleTypeface(titleTypeface)
                .descriptionTypeface(descTypeface)
                .titleTypefaceFontRes(titleTypefaceRes)
                .descriptionTypefaceFontRes(descTypefaceRes)
                .build()

        assertEquals(sliderPage.title, title)
        assertEquals(sliderPage.description, description)
        assertEquals(sliderPage.imageDrawable, imageDrawable)
        assertEquals(sliderPage.backgroundColorRes, bgColorRes)
        assertEquals(sliderPage.backgroundDrawable, bgDrawable)
        assertEquals(sliderPage.titleColorRes, titleColorRes)
        assertEquals(sliderPage.descriptionColorRes, descColorRes)
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
        assertEquals(sliderPage.imageDrawable, null)
        assertEquals(sliderPage.backgroundColorRes, null)
        assertEquals(sliderPage.backgroundDrawable, null)
        assertEquals(sliderPage.titleColorRes, null)
        assertEquals(sliderPage.descriptionColorRes, null)
        assertEquals(sliderPage.titleTypeface, null)
        assertEquals(sliderPage.descriptionTypeface, null)
        assertEquals(sliderPage.titleTypefaceFontRes, null)
        assertEquals(sliderPage.descriptionTypefaceFontRes, null)
    }
}
