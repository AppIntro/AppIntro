package com.github.appintro

import android.graphics.drawable.Animatable
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
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
import androidx.fragment.app.viewModels
import com.appintro.core.contracts.SlideBackgroundColorHolder
import com.appintro.core.contracts.SlideSelectionListener
import com.appintro.core.utils.LogHelper
import com.appintro.core.utils.TypefaceContainer

abstract class AppIntroBaseFragment :
    Fragment(),
    SlideSelectionListener,
    SlideBackgroundColorHolder {
    private val viewModel: AppIntroFragmentViewModel by viewModels()

    private val logTAG = LogHelper.makeLogTag(AppIntroBaseFragment::class.java)

    @get:LayoutRes
    protected abstract val layoutId: Int

    @ColorInt
    @Deprecated(
        "`defaultBackgroundColor` has been deprecated to support configuration changes",
        ReplaceWith("defaultBackgroundColorRes"),
    )
    final override var defaultBackgroundColor: Int = 0
        private set

    @ColorRes
    final override var defaultBackgroundColorRes: Int = 0
        private set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        @Suppress("DEPRECATION")
        defaultBackgroundColor = viewModel.defaultBackgroundColor ?: 0
        defaultBackgroundColorRes = viewModel.defaultBackgroundColorRes ?: 0

        val args = arguments
        if (args != null && args.size() != 0) {
            viewModel.drawable = args.getInt(ARG_DRAWABLE)
            viewModel.title = args.getCharSequence(ARG_TITLE)
            viewModel.description = args.getCharSequence(ARG_DESC)

            if (args.containsKey(ARG_BG_DRAWABLE)) {
                viewModel.bgDrawable = args.getInt(ARG_BG_DRAWABLE)
            }

            viewModel.titleTypefaceUrl = args.getString(ARG_TITLE_TYPEFACE_URL)
            viewModel.titleTypefaceRes = args.getInt(ARG_TITLE_TYPEFACE_RES)

            viewModel.descTypefaceUrl = args.getString(ARG_DESC_TYPEFACE_URL)
            viewModel.descTypefaceRes = args.getInt(ARG_DESC_TYPEFACE_RES)

            viewModel.defaultBackgroundColor = args.getInt(ARG_BG_COLOR)
            viewModel.defaultBackgroundColorRes = args.getInt(ARG_BG_COLOR_RES)

            if (args.containsKey(ARG_TITLE_COLOR)) {
                viewModel.titleColor = args.getInt(ARG_TITLE_COLOR)
            }

            if (args.containsKey(ARG_TITLE_COLOR_RES)) {
                viewModel.titleColorRes = args.getInt(ARG_TITLE_COLOR_RES)
            }

            if (args.containsKey(ARG_DESC_COLOR)) {
                viewModel.descColor = args.getInt(ARG_DESC_COLOR)
            }

            if (args.containsKey(ARG_DESC_COLOR_RES)) {
                viewModel.descColorRes = args.getInt(ARG_DESC_COLOR_RES)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(layoutId, container, false)
        val titleText = view.findViewById<TextView>(R.id.title)
        val descriptionText = view.findViewById<TextView>(R.id.description)
        val slideImage = view.findViewById<ImageView>(R.id.image)
        val mainLayout = view.findViewById<ConstraintLayout>(R.id.main)

        titleText.text = viewModel.title
        descriptionText.text = viewModel.description
        val titleColorRes = viewModel.titleColorRes
        val titleColor = viewModel.titleColor

        if (titleColorRes != null) {
            titleText.setTextColor(ContextCompat.getColor(requireContext(), titleColorRes))
        } else if (titleColor != null) { // Fallback to deprecated static color
            titleText.setTextColor(titleColor)
        }

        val descColorRes = viewModel.descColorRes
        val descColor = viewModel.descColor

        if (descColorRes != null) {
            descriptionText.setTextColor(ContextCompat.getColor(requireContext(), descColorRes))
        } else if (descColor != null) { // Fallback to deprecated static color
            descriptionText.setTextColor(descColor)
        }

        TypefaceContainer(
            typeFaceUrl = viewModel.titleTypefaceUrl,
            typeFaceResource = viewModel.titleTypefaceRes ?: 0,
        ).applyTo(titleText)

        TypefaceContainer(
            typeFaceUrl = viewModel.descTypefaceUrl,
            typeFaceResource = viewModel.descTypefaceRes ?: 0,
        ).applyTo(descriptionText)

        viewModel.drawable?.let {
            slideImage.setImageResource(it)
        }

        val bgDrawable = viewModel.bgDrawable

        when {
            bgDrawable != null -> {
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

        titleText.movementMethod = ScrollingMovementMethod()
        descriptionText.movementMethod = ScrollingMovementMethod()

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

    override fun onSlideDeselected() {
        LogHelper.d(logTAG, "Slide ${viewModel.title} has been deselected.")
    }

    override fun onSlideSelected() {
        LogHelper.d(logTAG, "Slide ${viewModel.title} has been selected.")
    }

    override fun setBackgroundColor(
        @ColorInt backgroundColor: Int,
    ) {
        view?.findViewById<ConstraintLayout>(R.id.main)?.setBackgroundColor(backgroundColor)
    }
}
