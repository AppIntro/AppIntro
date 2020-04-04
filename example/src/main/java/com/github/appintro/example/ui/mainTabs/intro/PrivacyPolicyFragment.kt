package com.github.appintro.example.ui.mainTabs.intro

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.github.appintro.ISlidePolicy
import com.github.appintro.appintroexample.R

class PrivacyPolicyFragment : Fragment(), ISlidePolicy {

    lateinit var checkBox: CheckBox

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_checkbox, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        checkBox = view.findViewById(R.id.checkbox)
    }

    override val isPolicyRespected: Boolean
        get() = checkBox.isChecked

    override fun onUserIllegallyRequestedNextPage() {
        Toast.makeText(context, "Please click on the checkbox to continue", Toast.LENGTH_SHORT).show()
    }

    companion object {
        fun newInstance() = PrivacyPolicyFragment()
    }
}