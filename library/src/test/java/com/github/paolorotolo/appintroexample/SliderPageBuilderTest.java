package com.github.paolorotolo.appintroexample;

import android.graphics.Color;

import com.github.paolorotolo.appintro.model.SliderPage;
import com.github.paolorotolo.appintro.model.SliderPagerBuilder;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Created by Mike on 21/02/2018.
 */

public class SliderPageBuilderTest {

    @Test
    public void sliderPageBuilder_correctParametersAssignment(){

        CharSequence title = "Title";
        CharSequence description = "Description";
        int imageDrawable = 0x7f020000;
        int bgColor = Color.GRAY;
        int titleColor = Color.WHITE;
        int descColor = Color.BLACK;
        String titleTypeface = "robotobold.ttf";
        String descTypeface = "OpenSans-Light.ttf";

        SliderPage sliderPage = new SliderPagerBuilder()
                .title(title)
                .description(description)
                .imageDrawable(imageDrawable)
                .bgColor(bgColor)
                .titleColor(titleColor)
                .descColor(descColor)
                .titleTypeface(titleTypeface)
                .descTypeface(descTypeface)
                .build();

        assertEquals(sliderPage.getTitle(), title);
        assertEquals(sliderPage.getDescription(), description);
        assertEquals(sliderPage.getImageDrawable(), imageDrawable);
        assertEquals(sliderPage.getBgColor(), bgColor);
        assertEquals(sliderPage.getTitleColor(), titleColor);
        assertEquals(sliderPage.getDescColor(), descColor);
        assertEquals(sliderPage.getTitleTypeface(), titleTypeface);
        assertEquals(sliderPage.getDescTypeface(), descTypeface);
    }
}
