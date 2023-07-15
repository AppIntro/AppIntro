package com.github.appintro.internal.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.adapter.FragmentStateAdapter

internal class PagerAdapter(
    fragmentActivity: FragmentActivity,
    private val fragments: MutableList<Fragment>
) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount() = this.fragments.size

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }

    fun getItem(position: Int, fragmentManager: FragmentManager): Fragment? {
        return fragmentManager.findFragmentByTag("f$position")
    }
}
