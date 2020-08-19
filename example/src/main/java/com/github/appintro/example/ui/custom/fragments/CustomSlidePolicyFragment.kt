package com.github.appintro.example.ui.custom.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.github.appintro.SlidePolicy
import com.github.appintro.example.R

class CustomSlidePolicyFragment : Fragment(), SlidePolicy {

    private lateinit var checkBox: CheckBox

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.intro_slide_policy, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkBox = view.findViewById(R.id.check_box)
    }

    override val isPolicyRespected: Boolean
        get() = checkBox.isChecked

    override fun onUserIllegallyRequestedNextPage() {
        Toast.makeText(
                requireContext(),
                R.string.please_select_the_checkbox_before_proceeding,
                Toast.LENGTH_SHORT
        ).show()
    }

    companion object {
        fun newInstance() : CustomSlidePolicyFragment {
            return CustomSlidePolicyFragment()
        }
    }
}
