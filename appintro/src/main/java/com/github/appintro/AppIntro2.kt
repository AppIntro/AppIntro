package com.github.appintro

import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.constraintlayout.widget.ConstraintLayout

abstract class AppIntro2 : AppIntroBase() {

    override val layoutId = R.layout.appintro_intro_layout2

    @DrawableRes
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
    private lateinit var skipImageButton: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        backgroundFrame = findViewById(R.id.background)
        bottomBar = findViewById(R.id.bottom)
        skipImageButton = findViewById(R.id.skip)
        if (isRtl) {
            skipImageButton.scaleX = -1F
        }
    }

    /**
     * Override viewpager bar color
     * @param color your color resource
     */
    fun setBarColor(@ColorInt color: Int) {
        bottomBar.setBackgroundColor(color)
    }

    /**
     * Override Skip button drawable
     * @param imageSkipButton your drawable resource
     */
    fun setImageSkipButton(imageSkipButton: Drawable) {
        skipImageButton.setImageDrawable(imageSkipButton)
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
     * Override skip button color
     *
     * @param colorSkipButton your color resource
     */
    fun setSkipArrowColor(@ColorInt colorSkipButton: Int) {
        val skip = findViewById<ImageButton>(R.id.skip)
        skip.setColorFilter(colorSkipButton)
    }

    /**
     * Override done button drawable resource
     *
     * @param imageDoneButton your drawable resource
     */
    fun setImageDoneButton(imageDoneButton: Drawable) {
        val done = findViewById<ImageButton>(R.id.done)
        done.setImageDrawable(imageDoneButton)
    }
}
