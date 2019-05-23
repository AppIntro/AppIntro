package com.github.paolorotolo.appintro.internal.viewpager

import android.view.View
import androidx.viewpager.widget.ViewPager

private const val MIN_SCALE_DEPTH = 0.75f
private const val MIN_SCALE_ZOOM = 0.85f
private const val MIN_ALPHA_ZOOM = 0.5f
private const val SCALE_FACTOR_SLIDE = 0.85f
private const val MIN_ALPHA_SLIDE = 0.35f

private const val FLOW_ROTATION_ANGLE = -30f

internal class ViewPagerTransformer(
    private val transformType: TransformType
) : ViewPager.PageTransformer {

    override fun transformPage(page: View, position: Float) {
        when (transformType) {
            TransformType.FLOW -> {
                page.rotationY = position * FLOW_ROTATION_ANGLE
            }
            TransformType.SLIDE_OVER -> transformSlideOver(position, page)
            TransformType.DEPTH -> transformDepth(position, page)
            TransformType.ZOOM -> transformZoom(position, page)
            TransformType.FADE -> transformFade(position, page)
        }
    }

    private fun transformFade(position: Float, page: View) {
        if (position <= -1.0f || position >= 1.0f) {
            page.translationX = page.width.toFloat()
            page.alpha = 0.0f
            page.isClickable = false
        } else if (position == 0.0f) {
            page.translationX = 0.0f
            page.alpha = 1.0f
            page.isClickable = true
        } else {
            // position is between -1.0F & 0.0F OR 0.0F & 1.0F
            page.translationX = page.width * -position
            page.alpha = 1.0f - Math.abs(position)
        }
    }

    private fun transformZoom(position: Float, page: View) {
        if (position >= -1 && position <= 1) {
            page.scaleXY = Math.max(MIN_SCALE_ZOOM, 1 - Math.abs(position))
            page.alpha = MIN_ALPHA_ZOOM + (page.scaleXY - MIN_SCALE_ZOOM) /
                    (1 - MIN_SCALE_ZOOM) * (1 - MIN_ALPHA_ZOOM)
            val vMargin = page.height * (1 - page.scaleXY) / 2
            val hMargin = page.width * (1 - page.scaleXY) / 2
            if (position < 0) {
                page.translationX = hMargin - vMargin / 2
            } else {
                page.translationX = -hMargin + vMargin / 2
            }
        } else {
            page.transformDefaults()
        }
    }

    private fun transformDepth(position: Float, page: View) {
        if (position > 0 && position < 1) {
            // moving to the right
            page.alpha = 1 - position
            page.scaleXY = MIN_SCALE_DEPTH + (1 - MIN_SCALE_DEPTH) * (1 - Math.abs(position))
            page.translationX = page.width * -position
        } else {
            page.transformDefaults()
        }
    }

    private fun transformSlideOver(position: Float, page: View) {
        if (position < 0 && position > -1) {
            // this is the page to the left
            page.scaleXY = Math.abs(Math.abs(position) - 1) * (1.0f - SCALE_FACTOR_SLIDE) + SCALE_FACTOR_SLIDE
            page.alpha = Math.max(MIN_ALPHA_SLIDE, 1 - Math.abs(position))
            val pageWidth = page.width
            val translateValue = position * -pageWidth
            if (translateValue > -pageWidth) {
                page.translationX = translateValue
            } else {
                page.translationX = 0f
            }
        } else {
            page.transformDefaults()
        }
    }
}

enum class TransformType {
    FLOW,
    DEPTH,
    ZOOM,
    SLIDE_OVER,
    FADE
}

private fun View.transformDefaults() {
    this.alpha = 1f
    this.scaleXY = 1f
    this.translationX = 0f
}

private var View.scaleXY: Float
    get() = this.scaleX
    set(value) {
        this.scaleX = value
        this.scaleY = value
    }
