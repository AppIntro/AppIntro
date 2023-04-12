package com.github.appintro.example.ui.permissions

import android.Manifest
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.github.appintro.AppIntro2
import com.github.appintro.AppIntroFragment
import com.github.appintro.example.R

class PermissionsIntro2 : AppIntro2() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        addSlide(AppIntroFragment.createInstance(
                "Welcome!",
                "This is a demo of the AppIntro library, with permissions being requested on a slide!",
                imageDrawable = R.drawable.ic_slide1))

        addSlide(AppIntroFragment.createInstance(
                "Welcome!",
                "In order to access your camera, you must give permissions.",
                imageDrawable = R.drawable.ic_slide2))

        addSlide(AppIntroFragment.createInstance(
                "Simple, yet Customizable",
                "The library offers a lot of customization, while keeping it simple for those that like simple.",
                imageDrawable = R.drawable.ic_slide3))

        addSlide(AppIntroFragment.createInstance(
                "Explore",
                "Feel free to explore the rest of the library demo!",
                imageDrawable = R.drawable.ic_slide4))

        // Request mandatory camera and location permission
        askForPermissions(
                arrayOf(Manifest.permission.CAMERA, Manifest.permission.ACCESS_FINE_LOCATION),
                2, true)

        // Request optional storage permission only on Android 13.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            askForPermissions(arrayOf(Manifest.permission.READ_MEDIA_IMAGES),
                    3, false)
        }
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