package com.github.appintro.example.ui.mainTabs

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.appintro.appintroexample.R
import com.github.appintro.example.ui.mainTabs.intro.DefaultIntro
import kotlinx.android.synthetic.main.tab_default_intro.*

class DefaultLayoutIntro : Fragment() {
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        intro1.setOnClickListener {
            startActivity(Intent(context, DefaultIntro::class.java))
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.tab_default_intro, container, false)
}