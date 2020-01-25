package com.github.appintro.example.ui.permsTabs.intro

import android.Manifest
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.github.appintro.example.util.AppIntroFragmentFactory
import com.github.paolorotolo.appintro.AppIntro2
import com.github.paolorotolo.appintroexample.R

class PermissionsIntro2 : AppIntro2() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        addSlide(AppIntroFragmentFactory.makeFragment(
                "Welcome!",
                "This is a demo of the AppIntro library, with permissions being requested on a slide!",
                R.drawable.ic_slide1))

        addSlide(AppIntroFragmentFactory.makeFragment(
                "Welcome!",
                "In order to access your camera, you must give permissions.",
                R.drawable.ic_slide2))

        addSlide(AppIntroFragmentFactory.makeFragment(
                "Simple, yet Customizable",
                "The library offers a lot of customization, while keeping it simple for those that like simple.",
                R.drawable.ic_slide3))

        addSlide(AppIntroFragmentFactory.makeFragment(
                "Explore",
                "Feel free to explore the rest of the library demo!",
                R.drawable.ic_slide4))

        // Request mandatory camera and location permission
        askForPermissions(
                arrayOf(Manifest.permission.CAMERA, Manifest.permission.ACCESS_FINE_LOCATION),
                2, true)

        // Request optional storage permission
        askForPermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                3, false)
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