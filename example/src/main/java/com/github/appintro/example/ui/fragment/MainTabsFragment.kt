package com.github.appintro.example.ui.fragment

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.appintro.appintroexample.R
import com.github.appintro.example.ui.mainTabs.MainPagerAdapter
import kotlinx.android.synthetic.main.fragment_main_tabs.*

class MainTabsFragment : Fragment() {
    private val titles = arrayOf(
            "Layout 1",
            "Layout 2",
            "Custom Layout",
            "Custom Background")

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mainPager.adapter = MainPagerAdapter(childFragmentManager, titles)

        with(mainTabs) {
            setBackgroundColor(Color.parseColor("#1976D2"))
            setDistributeEvenly(false)
            setCustomTabColorizer { Color.parseColor("#1976D2") }
            setViewPager(mainPager)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_main_tabs, container, false)
}