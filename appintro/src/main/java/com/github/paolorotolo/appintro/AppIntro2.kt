package com.github.paolorotolo.appintro

import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.annotation.ColorInt
import androidx.annotation.IdRes
import androidx.constraintlayout.widget.ConstraintLayout

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        backgroundFrame = findViewById(R.id.background)
        bottomBar = findViewById(R.id.bottom)
        skipImageButton = findViewById(R.id.skip)
        if (isRtl) {
            skipImageButton.scaleX = -1F
        }
    }

    override fun getLayoutId() = R.layout.appintro_intro_layout2

    /**
     * Returns the backgroundFrame to use with image loading libraries like Glide
     */
    fun getBackgroundFrame() = backgroundFrame

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
