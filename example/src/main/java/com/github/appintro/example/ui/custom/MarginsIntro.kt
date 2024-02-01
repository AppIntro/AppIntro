package com.github.appintro.example.ui.custom

import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.github.appintro.AppIntro
import com.github.appintro.AppIntroFragment
import com.github.appintro.example.R
import com.github.appintro.example.ui.custom.fragments.MarginsSlideFragment

class MarginsIntro : AppIntro() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set a color to the bar so that it's not transparent
        setBarColor(ContextCompat.getColor(this, R.color.appintro_example_orange))

        addSlide(
            AppIntroFragment.createInstance(
                "Welcome",
                "This is a demo of the AppIntro library, with setBarMargin set to true."
            )
        )

        addSlide(
            MarginsSlideFragment.newInstance()
        )
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
