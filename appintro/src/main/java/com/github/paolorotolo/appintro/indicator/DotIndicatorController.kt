package com.github.paolorotolo.appintro.indicator

import android.content.Context
import android.graphics.PorterDuff
import android.view.Gravity
import android.view.Gravity.CENTER
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.github.paolorotolo.appintro.R

/**
 * An [IndicatorController] that shows a list of dots and highlight the selected dot.
 * Use this when the number of page you're dealing with is not too high.
 */
class DotIndicatorController(context: Context) : IndicatorController, LinearLayout(context) {

    override var selectedIndicatorColor = -1
        set(value) {
            field = value
            selectPosition(currentPosition)
        }

    override var unselectedIndicatorColor = -1
        set(value) {
            field = value
            selectPosition(currentPosition)
        }

    private var currentPosition = 0
    private var slideCount = 0

    override fun newInstance(context: Context): View {
        val newLayoutParams = LinearLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT)
        newLayoutParams.gravity = Gravity.CENTER_VERTICAL
        layoutParams = newLayoutParams
        orientation = HORIZONTAL
        gravity = CENTER
        return this
    }

    override fun initialize(slideCount: Int) {
        this.slideCount = slideCount

        for (i in 0 until slideCount) {
            val dot = ImageView(this.context)
            dot.setImageDrawable(ContextCompat.getDrawable(this.context,
                    R.drawable.appintro_indicator_dot_grey))

            val params = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            )
            this.addView(dot, params)
        }
        selectPosition(0)
    }

    override fun selectPosition(index: Int) {
        currentPosition = index
        for (i in 0 until slideCount) {
            val drawableId = if (i == index)
                R.drawable.appintro_indicator_dot_white
            else
                R.drawable.appintro_indicator_dot_grey
            val drawable = ContextCompat.getDrawable(this.context, drawableId)

            if (selectedIndicatorColor != DEFAULT_COLOR && i == index)
                drawable!!.mutate().setColorFilter(selectedIndicatorColor,
                        PorterDuff.Mode.SRC_IN)
            if (unselectedIndicatorColor != DEFAULT_COLOR && i != index)
                drawable!!.mutate().setColorFilter(unselectedIndicatorColor,
                        PorterDuff.Mode.SRC_IN)
            (getChildAt(i) as ImageView).setImageDrawable(drawable)
        }
    }
}
