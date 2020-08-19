package com.github.appintro.example.ui

import androidx.annotation.StringRes
import com.github.appintro.example.R
import com.github.appintro.example.ui.custom.CustomBackgroundIntro
import com.github.appintro.example.ui.custom.CustomLayoutIntro
import com.github.appintro.example.ui.custom.SlidePolicyIntro
import com.github.appintro.example.ui.default.DefaultIntro
import com.github.appintro.example.ui.default.DefaultIntro2
import com.github.appintro.example.ui.permissions.PermissionsIntro
import com.github.appintro.example.ui.permissions.PermissionsIntro2

data class IntroEntry(
        @StringRes val title: Int,
        @StringRes val description: Int,
        val activityClass: Class<*>
)

val defaultEntries = listOf(
        IntroEntry(R.string.default_intro_title, R.string.default_intro, DefaultIntro::class.java),
        IntroEntry(R.string.default_intro2_title, R.string.default_intro2, DefaultIntro2::class.java),
        IntroEntry(R.string.custom_layout_intro_title, R.string.custom_layout_intro, CustomLayoutIntro::class.java),
        IntroEntry(R.string.custom_background_intro_title, R.string.custom_background_intro, CustomBackgroundIntro::class.java),
        IntroEntry(R.string.slide_policy_intro_title, R.string.slide_policy_intro, SlidePolicyIntro::class.java),
        IntroEntry(R.string.perms_intro1_title, R.string.perms_intro1, PermissionsIntro::class.java),
        IntroEntry(R.string.perms_intro2_title, R.string.perms_intro2, PermissionsIntro2::class.java)
)

