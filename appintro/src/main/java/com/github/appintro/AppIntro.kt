package com.github.appintro

import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.TextViewCompat
import com.github.appintro.internal.TypefaceContainer

internal const val ARG_USE_ICON_BUTTONS = "icon_buttons"

/**
 * Container for the other fragments used to show every step of the app intro
 */
class AppIntro : AppIntroBase() {

    /** It shows images instead of text buttons if the value is true.
     * In other words it controls which value for layoutId to be applied.*/
    private var withIconButtons = false
    override val layoutId by lazy {
        if (withIconButtons) {
            R.layout.appintro_intro_layout2
        } else {
            R.layout.appintro_intro_layout
        }
    }

    // only if withIconButtons = true
    private lateinit var backgroundFrame: ConstraintLayout
    private lateinit var bottomBar: View
    private lateinit var skipImageButton: ImageButton

    companion object {
        /**
         * Generates a new instance for [AppIntro]
         *
         * @param withIconButtons Boolean which will be the type of the buttons
         *
         * @return An [AppIntro] created instance
         */
        @JvmOverloads
        @JvmStatic
        fun newInstance(withIconButtons: Boolean = false): AppIntro {
            val appIntroContainer = AppIntro()
            appIntroContainer.arguments = Bundle(1).apply {
                putBoolean(ARG_USE_ICON_BUTTONS, withIconButtons)
            }
            return appIntroContainer
        }
    }

    // Used also from AppIntro2
    /**
     * Override viewpager bar color
     * @param color your color resource
     */
    fun setBarColor(@ColorInt color: Int) {
        val bottomBar = view?.findViewById<View>(R.id.bottom)
        bottomBar?.setBackgroundColor(color)
    }

    //Used from AppIntro2
    /**
     * Override next button arrow color
     *
     * @param color your color
     */
    fun setNextArrowColor(@ColorInt color: Int) {
        val nextButton = view?.findViewById<ImageButton>(R.id.next)
        nextButton?.setColorFilter(color)
    }

    /**
     * Override back button arrow color
     *
     * @param color your color
     */
    fun setBackArrowColor(@ColorInt color: Int) {
        val backButton = view?.findViewById<ImageButton>(R.id.back)
        backButton?.setColorFilter(color)
    }

    /**
     * Override separator color
     *
     * @param color your color resource
     */
    fun setSeparatorColor(@ColorInt color: Int) {
        val separator = view?.findViewById<View>(R.id.bottom_separator)
        separator?.setBackgroundColor(color)
    }

    /**
     * Override skip text
     *
     * @param text your text
     */
    fun setSkipText(text: CharSequence?) {
        val skipText = view?.findViewById<TextView>(R.id.skip)
        skipText?.text = text
    }

    /**
     * Override skip text
     *
     * @param skipResId your text resource Id
     */
    fun setSkipText(@StringRes skipResId: Int) {
        val skipText = view?.findViewById<TextView>(R.id.skip)
        skipText?.setText(skipResId)
    }

    /**
     * Override skip text typeface
     *
     * @param typeface the typeface to apply to Skip button
     */
    fun setSkipTextTypeface(@FontRes typeface: Int) {
        val skip = view?.findViewById<TextView>(R.id.skip)
        TypefaceContainer(null, typeface).applyTo(skip)
    }

    /**
     * Override skip text typeface
     *
     * @param typeURL URL of font file located in Assets folder
     */
    fun setSkipTextTypeface(typeURL: String?) {
        val skip = view?.findViewById<TextView>(R.id.skip)
        TypefaceContainer(typeURL, 0).applyTo(skip)
    }

    /**
     * Override done text
     *
     * @param text your text
     */
    fun setDoneText(text: CharSequence?) {
        val doneText = view?.findViewById<TextView>(R.id.done)
        doneText?.text = text
    }

    /**
     * Override done text
     *
     * @param doneResId your text resource Id
     */
    fun setDoneText(@StringRes doneResId: Int) {
        val doneText = view?.findViewById<TextView>(R.id.done)
        doneText?.setText(doneResId)
    }

    /**
     * Override done text typeface
     *
     * @param typeURL URL of font file located in Assets folder
     */
    fun setDoneTextTypeface(typeURL: String?) {
        val done = view?.findViewById<TextView>(R.id.done)
        TypefaceContainer(typeURL, 0).applyTo(done)
    }

    /**
     * Override done text typeface
     *
     * @param typeface the typeface to apply to Done button
     */
    fun setDoneTextTypeface(@FontRes typeface: Int) {
        val done = view?.findViewById<TextView>(R.id.done)
        TypefaceContainer(null, typeface).applyTo(done)
    }

    /**
     * Override done button text color
     *
     * @param colorDoneText your color resource
     */
    fun setColorDoneText(@ColorInt colorDoneText: Int) {
        val doneText = view?.findViewById<TextView>(R.id.done)
        doneText?.setTextColor(colorDoneText)
    }

    /**
     * Override done button text overall style
     *
     * @param textAppearance your text style from resource
     */
    fun setDoneTextAppearance(@StyleRes textAppearance: Int) {
        val doneText = view?.findViewById<TextView>(R.id.done)
        if (doneText != null) {
            TextViewCompat.setTextAppearance(doneText, textAppearance)
        }
    }

    /**
     * Override skip button color
     *
     * @param colorSkipButton your color resource
     */
    fun setColorSkipButton(@ColorInt colorSkipButton: Int) {
        val skip = view?.findViewById<TextView>(R.id.skip)
        skip?.setTextColor(colorSkipButton)
    }

    /**
     * Override skip button text overall style
     *
     * @param textAppearance your text style from resource
     */
    fun setSkipTextAppearance(@StyleRes textAppearance: Int) {
        val skip = view?.findViewById<TextView>(R.id.skip)
        if (skip != null) {
            TextViewCompat.setTextAppearance(skip, textAppearance)
        }
    }

    /**
     * Override Next button
     *
     * @param imageNextButton your drawable resource
     */
    fun setImageNextButton(imageNextButton: Drawable) {
        val nextButton = view?.findViewById<ImageView>(R.id.next)
        nextButton?.setImageDrawable(imageNextButton)
    }

    /**
     * Show or hide the Separator line.
     * This is a static setting and Separator state is maintained across slides
     * until explicitly changed.
     *
     * @param showSeparator Set : true to display. false to hide.
     */
    fun showSeparator(showSeparator: Boolean) {
        val bottomSeparator = view?.findViewById<View>(R.id.bottom_separator)
        if (showSeparator) {
            bottomSeparator?.visibility = View.VISIBLE
        } else {
            bottomSeparator?.visibility = View.INVISIBLE
        }
    }


    //    override val layoutId = R.layout.appintro_intro_layout2
//
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

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        withIconButtons = arguments?.getBoolean(ARG_USE_ICON_BUTTONS, false) == true
        val v = super.onCreateView(inflater, container, savedInstanceState)

        if (withIconButtons) {
            v?.apply {
                backgroundFrame = findViewById(R.id.background)
                bottomBar = findViewById(R.id.bottom)
                skipImageButton = findViewById(R.id.skip)
            }
            if (isRtl) {
                skipImageButton.scaleX = -1F
            }
        }

        return v
    }

    /**
     * Override Skip button drawable
     * @param imageSkipButton your drawable resource
     */
    fun setImageSkipButton(imageSkipButton: Drawable) {
        skipImageButton.setImageDrawable(imageSkipButton)
    }

    /**
     * Override skip button color
     *
     * @param colorSkipButton your color resource
     */
    fun setSkipArrowColor(@ColorInt colorSkipButton: Int) {
        val skip = view?.findViewById<ImageButton>(R.id.skip)
        skip?.setColorFilter(colorSkipButton)
    }
}
