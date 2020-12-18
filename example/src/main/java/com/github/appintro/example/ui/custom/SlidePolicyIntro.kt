package com.github.appintro.example.ui.custom

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.github.appintro.AppIntro
import com.github.appintro.AppIntroBase
import com.github.appintro.SlidePageFragment
import com.github.appintro.example.R
import com.github.appintro.example.ui.custom.fragments.CustomSlidePolicyFragment

class SlidePolicyIntro : AppCompatActivity(), AppIntroBase.OnAppIntroListener {
    private lateinit var appIntro: AppIntro
    private val appIntroTag: String = "AppIntro"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_frame)

        if (savedInstanceState == null) {
            appIntro = AppIntro.newInstance(false)
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
                "Welcome",
                "This is a demo of the AppIntro library, using the SlidePolicy feature."
        ))

        appIntro.addSlide(CustomSlidePolicyFragment.newInstance())

        appIntro.addSlide(SlidePageFragment.newInstance(
                "Policy Respected!",
                "If the user arrived here, the SlidePolicy was respected."
        ))
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