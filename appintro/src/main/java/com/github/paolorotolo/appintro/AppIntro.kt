package com.github.paolorotolo.appintro

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.annotation.FontRes
import androidx.annotation.IdRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.github.paolorotolo.appintro.internal.TypefaceContainer

abstract class AppIntro : AppIntroBase() {

    @IdRes
    var backgroundResource: Int? = null
        set(value) {
            field = value
            if (field != null) {
                field?.let { backgroundFrame.setBackgroundResource(it) }
            }
        }

    var backgroundDrawable: Drawable? = null
        set(value) {
            field = value
            if (field != null) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    backgroundFrame.background = field
                }
            }
        }

    private lateinit var backgroundFrame: ConstraintLayout
    private lateinit var bottomBar: View
    private lateinit var separator: View
    private lateinit var skipButtonCasted: Button
    private lateinit var doneButtonCasted: Button
    private lateinit var nextButtonCasted: ImageButton

    var dynamicThemeDisabled: Boolean = false

    override fun getLayoutId() = R.layout.appintro_intro_layout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        backgroundFrame = findViewById(R.id.background)
        bottomBar = findViewById(R.id.bottom)
        separator = findViewById(R.id.bottom_separator)
        skipButtonCasted = skipButton as Button
        doneButtonCasted = doneButton as Button
        nextButtonCasted = nextButton as ImageButton
    }

    /**
     * @see AppIntroBase.updateBackground
     * Users can set [dynamicThemeDisabled] to prevent this behaviour.
     */
    override fun updateBackground(fragment: Fragment?): Boolean {
        if (!dynamicThemeDisabled) {
            if (super.updateBackground(fragment)) {
                nextButtonCasted.setColorFilter(Color.BLACK)
            } else {
                nextButtonCasted.clearColorFilter()
            }
            return super.updateBackground(fragment)
        }
        return false
    }

    /**
     * Override viewpager bar color
     * @param color your color resource
     */
    fun setBarColor(@ColorInt color: Int) {
        bottomBar.setBackgroundColor(color)
    }

    /**
     * Override next button arrow color
     *
     * @param color your color
     */
    fun setNextArrowColor(@ColorInt color: Int) {
        nextButtonCasted.setColorFilter(color)
    }

    /**
     * Override separator color
     *
     * @param color your color resource
     */
    fun setSeparatorColor(@ColorInt color: Int) {
        separator.setBackgroundColor(color)
    }

    /**
     * Override skip text
     *
     * @param text your text
     */
    fun setSkipText(text: CharSequence?) {
        skipButtonCasted.text = text
    }

    /**
     * Override skip text typeface
     *
     * @param typeface the typeface to apply to Skip button
     */
    fun setSkipTextTypeface(@FontRes typeface: Int) {
        TypefaceContainer(null, typeface).applyTo(skipButtonCasted)
    }

    /**
     * Override skip text typeface
     *
     * @param typeURL URL of font file located in Assets folder
     */
    fun setSkipTextTypeface(typeURL: String?) {
        TypefaceContainer(typeURL, 0).applyTo(skipButtonCasted)
    }

    /**
     * Override done text
     *
     * @param text your text
     */
    fun setDoneText(text: CharSequence?) {
        doneButtonCasted.text = text
    }

    /**
     * Override done text typeface
     *
     * @param typeURL URL of font file located in Assets folder
     */
    fun setDoneTextTypeface(typeURL: String?) {
        TypefaceContainer(typeURL, 0).applyTo(doneButtonCasted)
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
        doneButtonCasted.setTextColor(colorDoneText)
    }

    /**
     * Override skip button color
     *
     * @param colorSkipButton your color resource
     */
    fun setColorSkipButton(@ColorInt colorSkipButton: Int) {
        skipButtonCasted.setTextColor(colorSkipButton)
    }

    /**
     * Override Next button
     *
     * @param imageNextButton your drawable resource
     */
    fun setImageNextButton(imageNextButton: Drawable) {
        nextButtonCasted.setImageDrawable(imageNextButton)
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
        if (showSeparator) {
            separator.visibility = View.VISIBLE
        } else {
            separator.visibility = View.INVISIBLE
        }
    }
}
