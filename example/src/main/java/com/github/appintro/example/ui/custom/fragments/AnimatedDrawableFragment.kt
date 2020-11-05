package com.github.appintro.example.ui.custom.fragments

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.github.appintro.example.R

class AnimatedDrawableFragment : Fragment() {

    private lateinit var myAnimation: AnimationDrawable

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.intro_animated_drawable, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<ImageView>(R.id.animated_image_view).apply {
            setBackgroundResource(R.drawable.animated_drawable)
            myAnimation = background as AnimationDrawable
            width
        }

        myAnimation.start()
    }

    companion object {
        fun newInstance() : AnimatedDrawableFragment {
            return AnimatedDrawableFragment()
        }
    }
}