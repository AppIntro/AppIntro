package com.github.appintro.indicator

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ProgressBar
import androidx.core.graphics.BlendModeColorFilterCompat
import androidx.core.graphics.BlendModeCompat
import com.github.appintro.internal.LayoutUtil

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
            progressDrawable.colorFilter =
                BlendModeColorFilterCompat.createBlendModeColorFilterCompat(
                    value,
                    BlendModeCompat.SRC_ATOP
                )
        }

    override var unselectedIndicatorColor = DEFAULT_COLOR
        set(value) {
            field = value
            progressDrawable.colorFilter =
                BlendModeColorFilterCompat.createBlendModeColorFilterCompat(
                    value,
                    BlendModeCompat.SRC_ATOP
                )
        }

    override fun newInstance(context: Context) = this

    override fun initialize(slideCount: Int) {
        this.max = slideCount
        if (isRtl) {
            this.scaleX = -1F
        }
        if (slideCount == 1) {
            this.visibility = View.INVISIBLE
        }
        selectPosition(0)
    }

    override fun selectPosition(index: Int) {
        this.progress = if (isRtl) {
            max - index
        } else {
            index + 1
        }
    }

    private val isRtl: Boolean get() = LayoutUtil.isRtl(this.context)
}
