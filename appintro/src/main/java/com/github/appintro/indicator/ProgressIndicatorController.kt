package com.github.appintro.indicator

import android.content.Context
import android.graphics.PorterDuff
import android.util.AttributeSet
import android.view.View
import android.widget.ProgressBar

internal const val DEFAULT_COLOR = 1

/**
 * An [IndicatorController] that shows a [ProgressBar] for express the number of
 * page that have been completed.
 * Use this when the number of page is higher and the [DotIndicatorController]
 * would not fit in the screen.
 */
class ProgressIndicatorController @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = android.R.attr.progressBarStyleHorizontal
) : IndicatorController, ProgressBar(context, attrs, defStyleAttr) {

    override var selectedIndicatorColor = DEFAULT_COLOR
        set(value) {
            field = value
            progressDrawable.setColorFilter(value, PorterDuff.Mode.SRC_IN)
        }

    override var unselectedIndicatorColor = DEFAULT_COLOR
        set(value) {
            field = value
            indeterminateDrawable.setColorFilter(value, PorterDuff.Mode.SRC_IN)
        }

    override fun newInstance(context: Context) = this

    override fun initialize(slideCount: Int) {
        this.max = slideCount
        if (slideCount == 1) {
            this.visibility = View.INVISIBLE
        }
        selectPosition(0)
    }

    override fun selectPosition(index: Int) {
        this.progress = index + 1
    }
}
