package com.github.appintro.example.ui.custom

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.github.appintro.AppIntro
import com.github.appintro.AppIntroBase
import com.github.appintro.SlidePageFragment
import com.github.appintro.example.R

class CustomBackgroundIntro : AppCompatActivity(), AppIntroBase.OnAppIntroListener {
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
                "This is a demo of the AppIntro library, with a custom background on each slide!",
                imageDrawable = R.drawable.ic_slide1
        ))

        appIntro.addSlide(SlidePageFragment.newInstance(
                "Clean App Intros",
                "This library offers developers the ability to add clean app intros at the start of their apps.",
                imageDrawable = R.drawable.ic_slide2
        ))

        appIntro.addSlide(SlidePageFragment.newInstance(
                "Simple, yet Customizable",
                "The library offers a lot of customization, while keeping it simple for those that like simple.",
                imageDrawable = R.drawable.ic_slide3
        ))

        appIntro.addSlide(SlidePageFragment.newInstance(
                "Explore",
                "Feel free to explore the rest of the library demo!",
                imageDrawable = R.drawable.ic_slide4
        ))

        //set color for the next and skip arrow
        appIntro.setNextArrowColor(Color.RED)
        appIntro.setSkipArrowColor(Color.BLUE)

        // Set intro custom background
        appIntro.backgroundResource = R.drawable.ic_drawer_header

        // Change the color of the dot indicator.
        appIntro.setIndicatorColor(Color.RED, Color.BLACK)
    }

    override fun onBackPressed() {
        val used = appIntro.onBackPressed()
        if (!used) {
            return super.onBackPressed()
        }
    }

    override fun onSkipPressed(currentFragment: Fragment?) {
        finish()
    }

    override fun onDonePressed(currentFragment: Fragment?) {
        finish()
    }
}