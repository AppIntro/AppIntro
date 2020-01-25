package com.github.appintro.example.ui.mainTabs.intro

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.github.appintro.example.util.AppIntroFragmentFactory
import com.github.paolorotolo.appintro.AppIntro2
import com.github.paolorotolo.appintroexample.R

class DefaultIntro2 : AppIntro2() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        addSlide(AppIntroFragmentFactory.makeFragment(
                "Welcome!",
                "This is a demo of the AppIntro library, using the second layout.",
                R.drawable.ic_slide1,
                backgroundDrawable = R.drawable.back_slide1,
                titleFont = R.font.lato,
                descFont = R.font.lato
        ))

        addSlide(AppIntroFragmentFactory.makeFragment(
                "Gradients!",
                "This text is written on a gradient background",
                R.drawable.ic_slide2,
                backgroundDrawable = R.drawable.back_slide3,
                titleTypeface = "OpenSans-Light.ttf",
                descTypeface = "OpenSans-Light.ttf"
        ))

        addSlide(AppIntroFragmentFactory.makeFragment(
                "Simple, yet Customizable",
                "The library offers a lot of customization, while keeping it simple for those that like simple.",
                R.drawable.ic_slide4,
                backgroundDrawable = R.drawable.back_slide4,
                titleFont = R.font.opensans_regular,
                descFont = R.font.opensans_regular
        ))

        addSlide(AppIntroFragmentFactory.makeFragment(
                "Explore",
                "Feel free to explore the rest of the library demo!",
                R.drawable.ic_slide4,
                backgroundDrawable = R.drawable.back_slide5
        ))

        setParallaxAnimation()
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