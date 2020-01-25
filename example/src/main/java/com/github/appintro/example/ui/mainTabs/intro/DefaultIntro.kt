package com.github.appintro.example.ui.mainTabs.intro

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.github.appintro.example.util.AppIntroFragmentFactory
import com.github.paolorotolo.appintro.AppIntro
import com.github.paolorotolo.appintroexample.R

class DefaultIntro : AppIntro() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        addSlide(AppIntroFragmentFactory.makeFragment(
                "Welcome!",
                "This is a demo of the AppIntro library, with a custom background on each slide!",
                R.drawable.ic_slide1
        ))

        addSlide(AppIntroFragmentFactory.makeFragment(
                "Clean App Intros",
                "This library offers developers the ability to add clean app intros at the start of their apps.",
                R.drawable.ic_slide2
        ))

        addSlide(AppIntroFragmentFactory.makeFragment(
                "Simple, yet Customizable",
                "The library offers a lot of customization, while keeping it simple for those that like simple.",
                R.drawable.ic_slide3
        ))

        addSlide(AppIntroFragmentFactory.makeFragment(
                "Explore",
                "Feel free to explore the rest of the library demo!",
                R.drawable.ic_slide4
        ))
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