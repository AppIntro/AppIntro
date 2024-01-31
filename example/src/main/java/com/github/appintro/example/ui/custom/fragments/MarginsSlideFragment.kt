package com.github.appintro.example.ui.custom.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.github.appintro.example.R

class MarginsSlideFragment : Fragment() {

    @StringRes
    private var text: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        text = arguments?.getInt(ATTR_TEXT)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.intro_margins, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val textView = view.findViewById<TextView>(R.id.text)
        textView.text = text?.let { getString(it) }
    }

    companion object {
        private const val ATTR_TEXT = "text"

        fun newInstance(@StringRes text: Int) : MarginsSlideFragment {
            return MarginsSlideFragment().apply {
                arguments = bundleOf(ATTR_TEXT to text)
            }
        }
    }
}
