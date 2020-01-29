package com.github.appintro.example.ui.mainTabs

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.appintro.example.ui.mainTabs.intro.CustomBackgroundIntro
import com.github.paolorotolo.appintroexample.R
import kotlinx.android.synthetic.main.tab_custom_background_intro.*

class CustomBackgroundIntro : Fragment() {
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        intro4.setOnClickListener {
            startActivity(Intent(context, CustomBackgroundIntro::class.java))
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.tab_custom_background_intro, container, false)
}