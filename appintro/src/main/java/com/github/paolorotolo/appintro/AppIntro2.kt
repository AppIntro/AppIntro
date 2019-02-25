package com.github.paolorotolo.appintro

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.annotation.ColorInt
import android.widget.FrameLayout
import androidx.constraintlayout.widget.ConstraintLayout

abstract class AppIntro2 : AppIntroBase() {

    var backgroundView: View? = null
        set(value) {
            field = value
            if (field != null) {
                backgroundFrame.addView(field, 0)
            }
        }

    private lateinit var backgroundFrame: ConstraintLayout
    private lateinit var bottomBar: FrameLayout
    private lateinit var skipImageButton: ImageButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        backgroundFrame = findViewById(R.id.background)
        bottomBar = findViewById(R.id.bottom)
        skipImageButton = findViewById(R.id.skip)
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
