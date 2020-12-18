package com.github.appintro.example.ui.permissions

import android.Manifest
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.github.appintro.AppIntro
import com.github.appintro.AppIntroBase
import com.github.appintro.SlidePageFragment
import com.github.appintro.example.R

class PermissionsIntro2 : AppCompatActivity(), AppIntroBase.OnAppIntroListener {
    private lateinit var appIntro: AppIntro
    private val appIntroTag: String = "AppIntro"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_frame)

        if (savedInstanceState == null) {
            appIntro = AppIntro.newInstance(true)
            supportFragmentManager.beginTransaction()
                    .replace(R.id.app_intro_container, appIntro, appIntroTag)
                    .commitNow()
        } else {
            appIntro = supportFragmentManager.findFragmentByTag(appIntroTag) as AppIntro
        }
        appIntro.setAppIntroListener(this)
    }

    override fun onCreateAppIntro() {
        appIntro.addSlide(SlidePageFragment.newInstance(
                "Welcome!",
                "This is a demo of the AppIntro library, with permissions being requested on a slide!",
                imageDrawable = R.drawable.ic_slide1))

        appIntro.addSlide(SlidePageFragment.newInstance(
                "Welcome!",
                "In order to access your camera, you must give permissions.",
                imageDrawable = R.drawable.ic_slide2))

        appIntro.addSlide(SlidePageFragment.newInstance(
                "Simple, yet Customizable",
                "The library offers a lot of customization, while keeping it simple for those that like simple.",
                imageDrawable = R.drawable.ic_slide3))

        appIntro.addSlide(SlidePageFragment.newInstance(
                "Explore",
                "Feel free to explore the rest of the library demo!",
                imageDrawable = R.drawable.ic_slide4))

        // Request mandatory camera and location permission
        appIntro.askForPermissions(
                arrayOf(Manifest.permission.CAMERA, Manifest.permission.ACCESS_FINE_LOCATION),
                2, true)

        // Request optional storage permission
        appIntro.askForPermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                3, false)
    }
    override fun onSkipPressed(currentFragment: Fragment?) {
        super.onSkipPressed(currentFragment)
        finish()
    }

    override fun onDonePressed(currentFragment: Fragment?) {
        super.onDonePressed(currentFragment)
        finish()
    }
}