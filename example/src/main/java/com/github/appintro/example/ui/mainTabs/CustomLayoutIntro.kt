package com.github.appintro.example.ui.mainTabs

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.appintro.appintroexample.R
import com.github.appintro.example.ui.mainTabs.intro.CustomLayoutIntro
import kotlinx.android.synthetic.main.tab_custom_layout_intro.*

class CustomLayoutIntro : Fragment() {
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        intro3.setOnClickListener {
            startActivity(Intent(context, CustomLayoutIntro::class.java))
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.tab_custom_layout_intro, container, false)
}