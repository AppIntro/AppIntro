package com.github.appintro.example.ui.permsTabs.intro

import android.Manifest
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.github.appintro.example.util.AppIntroFragmentFactory.makeFragment
import com.github.paolorotolo.appintro.AppIntro
import com.github.paolorotolo.appintroexample.R

class PermissionsIntro1 : AppIntro() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        addSlide(makeFragment(
                "Welcome!",
                "This is a demo of the AppIntro library, with permissions being requested on a slide!",
                R.drawable.ic_slide1))

        addSlide(makeFragment(
                "Permission Request",
                "In order to access your camera, you must give permissions.",
                R.drawable.ic_slide2))

        addSlide(makeFragment(
                "Simple, yet Customizable",
                "The library offers a lot of customization, while keeping it simple for those that like simple.",
                R.drawable.ic_slide3))

        addSlide(makeFragment(
                "Explore",
                "Feel free to explore the rest of the library demo!",
                R.drawable.ic_slide4))

        // Here we ask for camera permission on slide 2
        askForPermissions(arrayOf(Manifest.permission.CAMERA), 2)
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