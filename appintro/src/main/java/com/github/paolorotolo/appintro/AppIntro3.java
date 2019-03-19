package com.github.paolorotolo.appintro;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import com.github.paolorotolo.appintro.internal.TypefaceContainer;

import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.FontRes;
import androidx.constraintlayout.widget.ConstraintLayout;

/**
 * Created by Anurag on 3/19/19.
 */

public abstract class AppIntro3 extends AppIntroBase {

    private ConstraintLayout backgroundFrame;
    private ImageButton skipImageButton;
    private Button nextButton;
    private Button doneButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        backgroundFrame = findViewById(R.id.background);
        nextButton = findViewById(R.id.next);
        doneButton = findViewById(R.id.done);
        skipImageButton = findViewById(R.id.skip);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.appintro_intro_layout3;
    }

    protected void setBackground(@DrawableRes int drawable) {
        backgroundFrame.setBackgroundResource(drawable);
    }

    protected void setBackground(Drawable drawable) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            backgroundFrame.setBackground(drawable);
        }
    }

    /**
     * Override next text
     *
     * @param text your text
     */
    protected void setNextText(CharSequence text) {
        nextButton.setText(text);
    }

    /**
     * Override Next text typeface
     *
     * @param typeface the typeface to apply to Next button
     */
    protected void setNextTextTypeface(@FontRes int typeface) {
        TypefaceContainer container = new TypefaceContainer(null, typeface);
        container.applyTo(nextButton);
    }

    /**
     * Override Next text typeface
     *
     * @param typeURL URL of font file located in Assets folder
     */
    protected void setNextTextTypeface(String typeURL) {
        TypefaceContainer container = new TypefaceContainer(typeURL, 0);
        container.applyTo(nextButton);
    }

    /**
     * Override next button text color
     *
     * @param colorNextText your color resource
     */
    protected void setColorNextText(@ColorInt int colorNextText) {
        nextButton.setTextColor(colorNextText);
    }

    /**
     * Override next button color
     *
     * @param colorNext your color resource
     */
    protected void setColorNext(@ColorInt int colorNext) {
        nextButton.setBackgroundColor(colorNext);
    }


    /**
     * Override done text
     *
     * @param text your text
     */
    protected void setDoneText(CharSequence text) {
        doneButton.setText(text);
    }


    /**
     * Override Done text typeface
     *
     * @param typeface the typeface to apply to Done button
     */
    protected void setDoneTextTypeface(@FontRes int typeface) {
        TypefaceContainer container = new TypefaceContainer(null, typeface);
        container.applyTo(doneButton);
    }

    /**
     * Override Done text typeface
     *
     * @param typeURL URL of font file located in Assets folder
     */
    protected void setDoneTextTypeface(String typeURL) {
        TypefaceContainer container = new TypefaceContainer(typeURL, 0);
        container.applyTo(doneButton);
    }

    /**
     * Override done button text color
     *
     * @param colorDoneText your color resource
     */
    protected void setColorDoneText(@ColorInt int colorDoneText) {
        doneButton.setTextColor(colorDoneText);
    }

    /**
     * Override done button color
     *
     * @param colorDone your color resource
     */
    protected void setColorDone(@ColorInt int colorDone) {
        doneButton.setBackgroundColor(colorDone);
    }

    /**
     * Override SkipImageButton drawable
     *
     * @param imageSkipButton your drawable resource
     */
    protected void setImageSkipButton(Drawable imageSkipButton) {
        skipImageButton.setImageDrawable(imageSkipButton);
    }

    /**
     * Override skip icon color
     *
     * @param colorSkip your color resource
     */
    protected void setColorSkipIcon(@ColorInt int colorSkip) {
        skipImageButton.setColorFilter(colorSkip);
    }
}
