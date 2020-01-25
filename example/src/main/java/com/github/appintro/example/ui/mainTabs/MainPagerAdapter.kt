package com.github.appintro.example.ui.mainTabs

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class MainPagerAdapter (fm: FragmentManager,
                        private val titleList: Array<String>) : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int) = when (position) {
        0 -> DefaultLayoutIntro()
        1 -> DefaultLayout2Intro()
        2 -> CustomLayoutIntro()
        else -> CustomBackgroundIntro()
    }

    override fun getPageTitle(position: Int) = titleList[position]

    override fun getCount() = titleList.size
}