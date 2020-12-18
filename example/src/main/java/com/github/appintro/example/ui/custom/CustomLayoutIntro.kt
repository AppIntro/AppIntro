package com.github.appintro.example.ui.custom

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.github.appintro.AppIntro
import com.github.appintro.AppIntroBase
import com.github.appintro.AppIntroCustomLayoutFragment.Companion.newInstance
import com.github.appintro.example.R

class CustomLayoutIntro : AppCompatActivity(), AppIntroBase.OnAppIntroListener {

    private lateinit var appIntro: AppIntro
    private val appIntroTag: String = "AppIntro"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_frame)

        if (savedInstanceState == null) {
            appIntro = AppIntro.newInstance()
            supportFragmentManager.beginTransaction()
                    .replace(R.id.app_intro_container, appIntro, appIntroTag)
                    .commitNow()
        } else {
            appIntro = supportFragmentManager.findFragmentByTag(appIntroTag) as AppIntro
        }

        appIntro.setAppIntroListener(this)
    }

    override fun onCreateAppIntro() {
        appIntro.addSlide(newInstance(R.layout.intro_custom_layout1))
        appIntro.addSlide(newInstance(R.layout.intro_custom_layout2))
        appIntro.addSlide(newInstance(R.layout.intro_custom_layout3))
        appIntro.addSlide(newInstance(R.layout.intro_custom_layout4))

        appIntro.showStatusBar(true)
        appIntro.setStatusBarColorRes(R.color.orange)
        appIntro.setNavBarColorRes(R.color.orange)
        appIntro.setProgressIndicator()
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