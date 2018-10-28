package com.github.paolorotolo.appintro.internal.viewpager

import android.util.SparseArray
import android.view.ViewGroup

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

internal class PagerAdapter(
        fragmentManager: FragmentManager,
        val fragments: List<Fragment>
) : FragmentPagerAdapter(fragmentManager) {

    private val retainedFragments: SparseArray<Fragment> = SparseArray()

    override fun getItem(position: Int): Fragment? {
        return if (!fragments.isEmpty()) {
            // Check if the fragment at this position has been retained by the PagerAdapter
            if (retainedFragments.get(position) != null)
                retainedFragments.get(position)
            else
                fragments[position]

        } else null
    }

    override fun getCount() = this.fragments.size

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val fragment = super.instantiateItem(container, position) as Fragment
        retainedFragments.put(position, fragment)

        return fragment
    }

    override fun destroyItem(container: ViewGroup, position: Int, view: Any) {
        if (retainedFragments.get(position) != null) {
            retainedFragments.remove(position)
        }
        super.destroyItem(container, position, view)
    }
}
