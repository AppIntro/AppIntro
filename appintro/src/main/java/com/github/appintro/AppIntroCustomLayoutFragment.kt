package com.github.appintro

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

/**
 * Util class to be used when creating a slide with a custom layout.
 * [AppIntroCustomLayoutFragment.newInstance] passing the Layout ID of your custom layout.
 *
 * You can then use this Slide with the [AppIntroBase.addSlide] methods to add this slide
 * to your AppIntro.
 */
class AppIntroCustomLayoutFragment : Fragment() {

    private var layoutResId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layoutResId = arguments?.getInt(ARG_LAYOUT_RES_ID) ?: 0
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(layoutResId, container, false)

    companion object {
        private const val ARG_LAYOUT_RES_ID = "layoutResId"

        @JvmStatic
        fun newInstance(layoutResId: Int): AppIntroCustomLayoutFragment {
            val customSlide = AppIntroCustomLayoutFragment()
            val args = Bundle()
            args.putInt(ARG_LAYOUT_RES_ID, layoutResId)
            customSlide.arguments = args
            return customSlide
        }
    }
}
