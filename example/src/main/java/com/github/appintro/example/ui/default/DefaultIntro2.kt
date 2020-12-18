package com.github.appintro.example.ui.default

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.github.appintro.*
import com.github.appintro.example.R
import com.github.appintro.model.SliderPage

class DefaultIntro2 : AppCompatActivity(), AppIntroBase.OnAppIntroListener {
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
                "This is a demo of the AppIntro library, using the second layout.",
                imageDrawable = R.drawable.ic_slide1,
                backgroundDrawable = R.drawable.back_slide1,
                titleTypefaceFontRes = R.font.lato,
                descriptionTypefaceFontRes = R.font.lato
        ))

        appIntro.addSlide(SlidePageFragment.newInstance(SliderPage(
                "Gradients!",
                "This text is written on a gradient background",
                imageDrawable = R.drawable.ic_slide2,
                backgroundDrawable = R.drawable.back_slide2,
                titleTypeface = "OpenSans-Light.ttf",
                descriptionTypeface = "OpenSans-Light.ttf"
        )))

        appIntro.addSlide(SlidePageFragment.newInstance(
                "Simple, yet Customizable",
                "The library offers a lot of customization, while keeping it simple for those that like simple.",
                imageDrawable = R.drawable.ic_slide3,
                backgroundDrawable = R.drawable.back_slide3,
                titleTypefaceFontRes = R.font.opensans_regular,
                descriptionTypefaceFontRes = R.font.opensans_regular
        ))

        appIntro.addSlide(SlidePageFragment.newInstance(
                "Explore",
                "Feel free to explore the rest of the library demo!",
                imageDrawable = R.drawable.ic_slide4,
                backgroundDrawable = R.drawable.back_slide4
        ))

        appIntro.addSlide(SlidePageFragment.newInstance(
                ":)",
                "...gradients are awesome!",
                imageDrawable = R.mipmap.ic_launcher,
                backgroundDrawable = R.drawable.back_slide5
        ))

        appIntro.setTransformer(AppIntroPageTransformerType.Parallax())
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