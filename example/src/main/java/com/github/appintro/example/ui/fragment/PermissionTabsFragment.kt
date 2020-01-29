package com.github.appintro.example.ui.fragment

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.appintro.example.ui.permsTabs.PermsPagerAdapter
import com.github.paolorotolo.appintroexample.R
import kotlinx.android.synthetic.main.fragment_permission_tabs.*

class PermissionTabsFragment : Fragment() {
    var titles = arrayOf("Layout 1", "Layout 2")

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        permPager.adapter = PermsPagerAdapter(childFragmentManager, titles)

        with(permTabs) {
            setDistributeEvenly(true)
            setBackgroundColor(Color.parseColor("#1976D2"))
            setCustomTabColorizer { Color.parseColor("#1976D2") }
            setViewPager(permPager)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_permission_tabs, container, false)
}