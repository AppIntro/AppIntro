package com.github.appintro.example.ui.permsTabs

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class PermsPagerAdapter(fm: FragmentManager,
                        private val titleList: Array<String>) : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment = when (position) {
                0 -> PermissionsLayout1Intro()
                else -> PermissionsLayout2Intro()
    }

    override fun getPageTitle(position: Int) = titleList[position]

    override fun getCount(): Int = titleList.size

}