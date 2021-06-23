package com.github.appintro.example.ui.custom.fragments

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.github.appintro.example.R

class AnimatedDrawableFragment : Fragment() {

    private lateinit var myAnimation: AnimationDrawable

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.appintro_fragment_intro, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<TextView>(R.id.title).text = getString(R.string.animated_drawable_example_heading)
        view.findViewById<TextView>(R.id.description).text = getString(R.string.animated_drawable_example_description)

        val imageView = view.findViewById<ImageView>(R.id.image)
        imageView.apply {
            setImageResource(R.drawable.animated_drawable)
            myAnimation = drawable as AnimationDrawable
        }

        myAnimation.start()
    }

    companion object {
        fun newInstance() : AnimatedDrawableFragment {
            return AnimatedDrawableFragment()
        }
    }
}