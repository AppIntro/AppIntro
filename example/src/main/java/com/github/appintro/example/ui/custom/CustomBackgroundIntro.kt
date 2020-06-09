package com.github.appintro.example.ui.custom

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.github.appintro.AppIntro2
import com.github.appintro.AppIntroFragment
import com.github.appintro.example.R

class CustomBackgroundIntro : AppIntro2() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        addSlide(AppIntroFragment.newInstance(
                "Welcome!",
                "This is a demo of the AppIntro library, with a custom background on each slide!",
                imageDrawable = R.drawable.ic_slide1
        ))

        addSlide(AppIntroFragment.newInstance(
                "Clean App Intros",
                "This library offers developers the ability to add clean app intros at the start of their apps.",
                imageDrawable = R.drawable.ic_slide2
        ))

        addSlide(AppIntroFragment.newInstance(
                "Simple, yet Customizable",
                "The library offers a lot of customization, while keeping it simple for those that like simple.",
                imageDrawable = R.drawable.ic_slide3
        ))

        addSlide(AppIntroFragment.newInstance(
                "Explore",
                "Feel free to explore the rest of the library demo!",
                imageDrawable = R.drawable.ic_slide4
        ))

        // Set intro custom background
        backgroundResource = R.drawable.ic_drawer_header

        // Change the color of the dot indicator.
        setIndicatorColor(Color.RED, Color.BLACK)
    }

    public override fun onSkipPressed(currentFragment: Fragment?) {
        super.onSkipPressed(currentFragment)
        finish()
    }

    public override fun onDonePressed(currentFragment: Fragment?) {
        super.onDonePressed(currentFragment)
        finish()
    }
}