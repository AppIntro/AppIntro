package com.github.appintro.internal.viewpager

import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

internal class PagerAdapter(
    fragmentManager: FragmentManager,
    private val fragments: MutableList<Fragment>
) : FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val frament = super.instantiateItem(container, position)
        fragments[position] = (frament as Fragment)
        return frament
    }

    override fun getCount() = this.fragments.size
}
