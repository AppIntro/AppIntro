package com.github.paolorotolo.appintro

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.annotation.FontRes
import com.github.paolorotolo.appintro.internal.TypefaceContainer

abstract class AppIntro : AppIntroBase() {

    override fun getLayoutId() = R.layout.appintro_intro_layout

    /**
     * Override viewpager bar color
     * @param color your color resource
     */
    fun setBarColor(@ColorInt color: Int) {
        val bottomBar = findViewById<View>(R.id.bottom)
        bottomBar.setBackgroundColor(color)
    }

    /**
     * Override next button arrow color
     *
     * @param color your color
     */
    fun setNextArrowColor(@ColorInt color: Int) {
        val nextButton = findViewById<ImageButton>(R.id.next)
        nextButton.setColorFilter(color)
    }

    /**
     * Override separator color
     *
     * @param color your color resource
     */
    fun setSeparatorColor(@ColorInt color: Int) {
        val separator = findViewById<View>(R.id.bottom_separator)
        separator.setBackgroundColor(color)
    }

    /**
     * Override skip text
     *
     * @param text your text
     */
    fun setSkipText(text: CharSequence?) {
        val skipText = findViewById<TextView>(R.id.skip)
        skipText.text = text
    }

    /**
     * Override skip text typeface
     *
     * @param typeface the typeface to apply to Skip button
     */
    fun setSkipTextTypeface(@FontRes typeface: Int) {
        val view = findViewById<TextView>(R.id.skip)
        TypefaceContainer(null, typeface).applyTo(view)
    }

    /**
     * Override skip text typeface
     *
     * @param typeURL URL of font file located in Assets folder
     */
    fun setSkipTextTypeface(typeURL: String?) {
        val view = findViewById<TextView>(R.id.skip)
        TypefaceContainer(typeURL, 0).applyTo(view)
    }

    /**
     * Override done text
     *
     * @param text your text
     */
    fun setDoneText(text: CharSequence?) {
        val doneText = findViewById<TextView>(R.id.done)
        doneText.text = text
    }

    /**
     * Override done text typeface
     *
     * @param typeURL URL of font file located in Assets folder
     */
    fun setDoneTextTypeface(typeURL: String?) {
        val view = findViewById<TextView>(R.id.done)
        TypefaceContainer(typeURL, 0).applyTo(view)
    }

    /**
     * Override done text typeface
     *
     * @param typeface the typeface to apply to Done button
     */
    fun setDoneTextTypeface(@FontRes typeface: Int) {
        val view = findViewById<TextView>(R.id.done)
        TypefaceContainer(null, typeface).applyTo(view)
    }

    /**
     * Override done button text color
     *
     * @param colorDoneText your color resource
     */
    fun setColorDoneText(@ColorInt colorDoneText: Int) {
        val doneText = findViewById<TextView>(R.id.done)
        doneText.setTextColor(colorDoneText)
    }

    /**
     * Override skip button color
     *
     * @param colorSkipButton your color resource
     */
    fun setColorSkipButton(@ColorInt colorSkipButton: Int) {
        val skip = findViewById<TextView>(R.id.skip)
        skip.setTextColor(colorSkipButton)
    }

    /**
     * Override Next button
     *
     * @param imageNextButton your drawable resource
     */
    fun setImageNextButton(imageNextButton: Drawable) {
        val nextButton = findViewById<ImageView>(R.id.next)
        nextButton.setImageDrawable(imageNextButton)
    }

    /**
     * Shows or hides Done button, replaced with setProgressButtonEnabled
     *
     */
    @Deprecated("use {@link #setProgressButtonEnabled(boolean)} instead.", ReplaceWith("isProgressButtonEnabled = showDone"))
    fun showDoneButton(showDone: Boolean) {
        isProgressButtonEnabled = showDone
    }

    /**
     * Show or hide the Separator line.
     * This is a static setting and Separator state is maintained across slides
     * until explicitly changed.
     *
     * @param showSeparator Set : true to display. false to hide.
     */
    fun showSeparator(showSeparator: Boolean) {
        val bottomSeparator = findViewById<View>(R.id.bottom_separator)
        if (showSeparator) {
            bottomSeparator.visibility = View.VISIBLE
        } else {
            bottomSeparator.visibility = View.INVISIBLE
        }
    }
}
