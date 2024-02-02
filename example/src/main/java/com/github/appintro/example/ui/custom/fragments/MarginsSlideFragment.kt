package com.github.appintro.example.ui.custom.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.github.appintro.AppIntro
import com.github.appintro.example.R

class MarginsSlideFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.intro_margins, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val checkbox = view.findViewById<CheckBox>(R.id.margin_checkbox)
        checkbox.setOnCheckedChangeListener { _, checked ->
            (requireActivity() as AppIntro).setBarMargin(checked)
        }
    }

    companion object {
        fun newInstance() : MarginsSlideFragment {
            return MarginsSlideFragment()
        }
    }
}
