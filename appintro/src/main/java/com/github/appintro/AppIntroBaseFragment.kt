package com.github.appintro

import android.graphics.drawable.Animatable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.LayoutRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.github.appintro.internal.LogHelper
import com.github.appintro.internal.TypefaceContainer

internal const val ARG_TITLE = "title"
internal const val ARG_TITLE_TYPEFACE = "title_typeface"
internal const val ARG_TITLE_TYPEFACE_RES = "title_typeface_res"
internal const val ARG_DESC = "desc"
internal const val ARG_DESC_TYPEFACE = "desc_typeface"
internal const val ARG_DESC_TYPEFACE_RES = "desc_typeface_res"
internal const val ARG_DRAWABLE = "drawable"
internal const val ARG_BG_COLOR = "bg_color"
internal const val ARG_BG_COLOR_RES = "bg_color_res"
internal const val ARG_TITLE_COLOR = "title_color"
internal const val ARG_TITLE_COLOR_RES = "title_color_res"
internal const val ARG_DESC_COLOR = "desc_color"
internal const val ARG_DESC_COLOR_RES = "desc_color_res"
internal const val ARG_BG_DRAWABLE = "bg_drawable"

abstract class AppIntroBaseFragment : Fragment(), SlideSelectionListener, SlideBackgroundColorHolder {

    private val logTAG = LogHelper.makeLogTag(AppIntroBaseFragment::class.java)

    @get:LayoutRes
    protected abstract val layoutId: Int

    private var drawable: Int = 0
    private var bgDrawable: Int = 0

    @ColorInt
    private var titleColor: Int = 0

    @ColorRes
    private var titleColorRes: Int = 0

    @ColorInt
    private var descColor: Int = 0

    @ColorRes
    private var descColorRes: Int = 0

    @ColorInt
    final override var defaultBackgroundColor: Int = 0
        private set

    @ColorRes
    final override var defaultBackgroundColorRes: Int = 0
        private set

    private var title: String? = null
    private var description: String? = null
    private var titleTypeface: TypefaceContainer? = null
    private var descTypeface: TypefaceContainer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true

        val args = arguments
        if (args != null && args.size() != 0) {
            drawable = args.getInt(ARG_DRAWABLE)
            title = args.getString(ARG_TITLE)
            description = args.getString(ARG_DESC)
            bgDrawable = args.getInt(ARG_BG_DRAWABLE)

            val argsTitleTypeface = args.getString(ARG_TITLE_TYPEFACE)
            val argsDescTypeface = args.getString(ARG_DESC_TYPEFACE)
            val argsTitleTypefaceRes = args.getInt(ARG_TITLE_TYPEFACE_RES)
            val argsDescTypefaceRes = args.getInt(ARG_DESC_TYPEFACE_RES)
            titleTypeface = TypefaceContainer(argsTitleTypeface, argsTitleTypefaceRes)
            descTypeface = TypefaceContainer(argsDescTypeface, argsDescTypefaceRes)

            @Suppress("DEPRECATION")
            defaultBackgroundColor = args.getInt(ARG_BG_COLOR)
            defaultBackgroundColorRes = args.getInt(ARG_BG_COLOR_RES)
            titleColor = args.getInt(ARG_TITLE_COLOR, 0)
            titleColorRes = args.getInt(ARG_TITLE_COLOR_RES, 0)
            descColor = args.getInt(ARG_DESC_COLOR, 0)
            descColorRes = args.getInt(ARG_DESC_COLOR_RES, 0)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (savedInstanceState != null) {
            drawable = savedInstanceState.getInt(ARG_DRAWABLE)
            title = savedInstanceState.getString(ARG_TITLE)
            description = savedInstanceState.getString(ARG_DESC)

            titleTypeface = TypefaceContainer(
                savedInstanceState.getString(ARG_TITLE_TYPEFACE),
                savedInstanceState.getInt(ARG_TITLE_TYPEFACE_RES, 0)
            )
            descTypeface = TypefaceContainer(
                savedInstanceState.getString(ARG_DESC_TYPEFACE),
                savedInstanceState.getInt(ARG_DESC_TYPEFACE_RES, 0)
            )

            @Suppress("DEPRECATION")
            defaultBackgroundColor = savedInstanceState.getInt(ARG_BG_COLOR)
            defaultBackgroundColorRes = savedInstanceState.getInt(ARG_BG_COLOR_RES)
            bgDrawable = savedInstanceState.getInt(ARG_BG_DRAWABLE)
            titleColor = savedInstanceState.getInt(ARG_TITLE_COLOR)
            titleColorRes = savedInstanceState.getInt(ARG_TITLE_COLOR_RES)
            descColor = savedInstanceState.getInt(ARG_DESC_COLOR)
            descColorRes = savedInstanceState.getInt(ARG_DESC_COLOR_RES)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(layoutId, container, false)
        val titleText = view.findViewById<TextView>(R.id.title)
        val descriptionText = view.findViewById<TextView>(R.id.description)
        val slideImage = view.findViewById<ImageView>(R.id.image)
        val mainLayout = view.findViewById<ConstraintLayout>(R.id.main)

        titleText.text = title
        descriptionText.text = description

        if (titleColorRes != 0) {
            titleText.setTextColor(ContextCompat.getColor(requireContext(), titleColorRes))
        } else if (titleColor != 0) { // Fallback to deprecated static color
            titleText.setTextColor(titleColor)
        }
        if (descColorRes != 0) {
            descriptionText.setTextColor(ContextCompat.getColor(requireContext(), descColorRes))
        } else if (descColor != 0) { // Fallback to deprecated static color
            descriptionText.setTextColor(descColor)
        }

        titleTypeface?.applyTo(titleText)
        descTypeface?.applyTo(descriptionText)

        slideImage.setImageResource(drawable)
        when {
            bgDrawable != 0 -> {
                mainLayout?.setBackgroundResource(bgDrawable)
            }
            defaultBackgroundColorRes != 0 -> {
                mainLayout?.setBackgroundColor(ContextCompat.getColor(requireContext(), defaultBackgroundColorRes))
            }
            else -> {
                @Suppress("DEPRECATION")
                mainLayout?.setBackgroundColor(defaultBackgroundColor)
            }
        }

        return view
    }

    override fun onResume() {
        super.onResume()

        view?.findViewById<ImageView>(R.id.image).let {
            if (it is Animatable) {
                it.start()
            }
        }
    }

    override fun onPause() {
        super.onPause()

        view?.findViewById<ImageView>(R.id.image).let {
            if (it is Animatable) {
                it.start()
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(ARG_DRAWABLE, drawable)
        outState.putInt(ARG_BG_DRAWABLE, bgDrawable)
        outState.putString(ARG_TITLE, title)
        outState.putString(ARG_DESC, description)
        @Suppress("DEPRECATION")
        outState.putInt(ARG_BG_COLOR, defaultBackgroundColor)
        outState.putInt(ARG_BG_COLOR_RES, defaultBackgroundColorRes)
        outState.putInt(ARG_TITLE_COLOR, titleColor)
        outState.putInt(ARG_TITLE_COLOR_RES, titleColorRes)
        outState.putInt(ARG_DESC_COLOR, descColor)
        outState.putInt(ARG_DESC_COLOR_RES, descColorRes)
        if (titleTypeface != null) {
            outState.putString(ARG_TITLE_TYPEFACE, titleTypeface?.typeFaceUrl)
            outState.putInt(ARG_TITLE_TYPEFACE_RES, titleTypeface?.typeFaceResource ?: 0)
        }
        if (descTypeface != null) {
            outState.putString(ARG_DESC_TYPEFACE, descTypeface?.typeFaceUrl)
            outState.putInt(ARG_DESC_TYPEFACE_RES, descTypeface?.typeFaceResource ?: 0)
        }
        super.onSaveInstanceState(outState)
    }

    override fun onSlideDeselected() {
        LogHelper.d(logTAG, "Slide $title has been deselected.")
    }

    override fun onSlideSelected() {
        LogHelper.d(logTAG, "Slide $title has been selected.")
    }

    override fun setBackgroundColor(@ColorInt backgroundColor: Int) {
        view?.findViewById<ConstraintLayout>(R.id.main)?.setBackgroundColor(backgroundColor)
    }
}
