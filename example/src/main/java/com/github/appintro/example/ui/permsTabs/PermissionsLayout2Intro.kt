package com.github.appintro.example.ui.permsTabs

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.appintro.example.ui.permsTabs.intro.PermissionsIntro2
import com.github.paolorotolo.appintroexample.R
import kotlinx.android.synthetic.main.tab_permissions_2.*

class PermissionsLayout2Intro : Fragment() {
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        intro2.setOnClickListener {
            startActivity(Intent(activity!!.baseContext, PermissionsIntro2::class.java))
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.tab_permissions_2, container, false)
}