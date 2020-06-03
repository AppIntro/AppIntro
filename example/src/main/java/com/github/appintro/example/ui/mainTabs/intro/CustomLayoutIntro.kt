package com.github.appintro.example.ui.mainTabs.intro

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.github.appintro.AppIntro
import com.github.appintro.AppIntroCustomLayoutFragment.Companion.newInstance
import com.github.appintro.appintroexample.R


class CustomLayoutIntro : AppIntro() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Navigation.findNavController(this, R.id.nav_appintro_fragment).setGraph(R.navigation.slide_navigation)

        addSlide(IntroCustomLayout1.newInstance(R.layout.intro_custom_layout1));
        addSlide(newInstance(R.layout.intro_custom_layout1))
        addSlide(newInstance(R.layout.intro_custom_layout2))
        addSlide(newInstance(R.layout.intro_custom_layout3))
        addSlide(newInstance(R.layout.intro_custom_layout4))

        showStatusBar(true)
        setStatusBarColorRes(R.color.accent)
        setNavBarColorRes(R.color.accent)
        setProgressIndicator()
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