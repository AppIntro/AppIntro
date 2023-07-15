package com.github.appintro.internal.viewpager

import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.github.appintro.AppIntroPageTransformerType
import com.github.appintro.internal.LogHelper
import kotlin.math.abs
import kotlin.math.max

private const val MIN_SCALE_DEPTH = 0.75f
private const val MIN_SCALE_ZOOM = 0.85f
private const val MIN_ALPHA_ZOOM = 0.5f
private const val SCALE_FACTOR_SLIDE = 0.85f
private const val MIN_ALPHA_SLIDE = 0.35f

private const val FLOW_ROTATION_ANGLE = -30f

internal class ViewPagerTransformer(
    private val transformType: AppIntroPageTransformerType
) : ViewPager2.PageTransformer {

    private var titlePF: Double = 0.0
    private var imagePF: Double = 0.0
    private var descriptionPF: Double = 0.0

    override fun transformPage(page: View, position: Float) {
        when (transformType) {
            AppIntroPageTransformerType.Flow -> {
                page.rotationY = position * FLOW_ROTATION_ANGLE
            }
            AppIntroPageTransformerType.SlideOver -> transformSlideOver(position, page)
            AppIntroPageTransformerType.Depth -> transformDepth(position, page)
            AppIntroPageTransformerType.Zoom -> transformZoom(position, page)
            AppIntroPageTransformerType.Fade -> transformFade(position, page)
            is AppIntroPageTransformerType.Parallax -> {
                titlePF = transformType.titleParallaxFactor
                imagePF = transformType.imageParallaxFactor
                descriptionPF = transformType.descriptionParallaxFactor
                transformParallax(
                    position,
                    page,
                    transformType.titleViewId,
                    transformType.imageViewId,
                    transformType.descriptionViewId
                )
            }
        }
    }

    private fun transformParallax(
        position: Float,
        page: View,
        titleViewId: Int,
        imageViewId: Int,
        descriptionViewId: Int
    ) {
        if (position > -1 && position < 1) {
            try {
                applyParallax(page, position, titleViewId, titlePF, "title")
                applyParallax(page, position, imageViewId, imagePF, "image")
                applyParallax(page, position, descriptionViewId, descriptionPF, "description")
            } catch (e: IllegalStateException) {
                LogHelper.e(TAG, "Failed to apply parallax effect", e)
            }
        }
    }

    private fun applyParallax(
        page: View,
        position: Float,
        viewId: Int,
        parallaxFactor: Double,
        logLabel: String
    ) {
        page.findViewById<View>(viewId)?.let {
            it.translationX = computeParallax(page, position, parallaxFactor)
        } ?: {
            LogHelper.e(
                TAG,
                "Could not parallax animate view '$logLabel' as " +
                    "the provided view ID can't be found in the layout"
            )
        }
    }

    private fun computeParallax(page: View, position: Float, parallaxFactor: Double): Float {
        return (-position * (page.width / parallaxFactor)).toFloat()
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
            page.alpha = 1.0f - abs(position)
        }
    }

    private fun transformZoom(position: Float, page: View) {
        if (position >= -1 && position <= 1) {
            page.scaleXY = max(MIN_SCALE_ZOOM, 1 - abs(position))
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
            page.scaleXY = MIN_SCALE_DEPTH + (1 - MIN_SCALE_DEPTH) * (1 - abs(position))
            page.translationX = page.width * -position
        } else {
            page.transformDefaults()
        }
    }

    private fun transformSlideOver(position: Float, page: View) {
        if (position < 0 && position > -1) {
            // this is the page to the left
            page.scaleXY = abs(abs(position) - 1) * (1.0f - SCALE_FACTOR_SLIDE) + SCALE_FACTOR_SLIDE
            page.alpha = max(MIN_ALPHA_SLIDE, 1 - abs(position))
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

    companion object {
        private val TAG = LogHelper.makeLogTag(ViewPagerTransformer::class)
    }
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
