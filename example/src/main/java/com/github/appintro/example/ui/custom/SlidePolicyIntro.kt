package com.github.appintro.example.ui.custom

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.github.appintro.AppIntro
import com.github.appintro.AppIntroFragment
import com.github.appintro.example.ui.custom.fragments.CustomSlidePolicyFragment

class SlidePolicyIntro : AppIntro() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        addSlide(AppIntroFragment.createInstance(
                "Welcome",
                "This is a demo of the AppIntro library, using the SlidePolicy feature."
        ))

        addSlide(CustomSlidePolicyFragment.newInstance())

        addSlide(AppIntroFragment.createInstance(
                "Policy Respected!",
                "If the user arrived here, the SlidePolicy was respected."
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