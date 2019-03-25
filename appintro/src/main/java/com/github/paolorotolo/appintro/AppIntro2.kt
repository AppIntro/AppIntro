package com.github.paolorotolo.appintro

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.annotation.ColorInt
import androidx.annotation.IdRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment

abstract class AppIntro2 : AppIntroBase() {

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
    private lateinit var skipImageButton: ImageButton
    private lateinit var nextImageButton: ImageButton
    private lateinit var doneImageButton: ImageButton
    private lateinit var backImageButton: ImageButton
    var dynamicThemeDisabled: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        backgroundFrame = findViewById(R.id.background)
        bottomBar = findViewById(R.id.bottom)
        skipImageButton = skipButton as ImageButton
        nextImageButton = nextButton as ImageButton
        backImageButton = backButton as ImageButton
        doneImageButton = doneButton as ImageButton
        if (isRtl) {
            skipImageButton.scaleX = -1F
        }
    }

    override fun getLayoutId() = R.layout.appintro_intro_layout2

    /**
     * Shows or hides Done button, replaced with setProgressButtonEnabled
     */
    @Deprecated("use {@link #setProgressButtonEnabled(boolean)} instead.", ReplaceWith("isProgressButtonEnabled = showDone"))
    fun showDoneButton(showDone: Boolean) {
        isProgressButtonEnabled = showDone
    }

    /**
     * @see AppIntroBase.updateBackground
     * Users can set [dynamicThemeDisabled] to prevent this behaviour.
     */
    override fun updateBackground(fragment: Fragment?): Boolean {
        if (!dynamicThemeDisabled) {
            if (super.updateBackground(fragment)) {
                nextImageButton.setColorFilter(Color.BLACK)
                doneImageButton.setColorFilter(Color.BLACK)
                skipImageButton.setColorFilter(Color.BLACK)
                backImageButton.setColorFilter(Color.BLACK)
            } else {
                nextImageButton.clearColorFilter()
                doneImageButton.clearColorFilter()
                skipImageButton.clearColorFilter()
                backImageButton.clearColorFilter()
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
     * Override Next button
     * @param imageSkipButton your drawable resource
     */
    fun setImageSkipButton(imageSkipButton: Drawable) {
        skipImageButton.setImageDrawable(imageSkipButton)
    }
}
