package com.github.appintro.example.ui.permsTabs

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.appintro.example.ui.permsTabs.intro.PermissionsIntro1
import com.github.paolorotolo.appintroexample.R
import kotlinx.android.synthetic.main.tab_permissions_1.*

class PermissionsLayout1Intro : Fragment() {
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        intro1.setOnClickListener {
            startActivity(Intent(context, PermissionsIntro1::class.java))
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.tab_permissions_1, container, false)
}