package com.github.paolorotolo.appintro.indicator

import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.rd.PageIndicatorView
import com.rd.animation.type.AnimationType

class PageIndicatorViewIndicatorController(
        context: Context,
        attrs: AttributeSet? = null,
        private val animationType: PageIndicatorAnimationType = PageIndicatorAnimationType.NONE
) : IndicatorController {

    val pageIndicatorView = PageIndicatorView(context, attrs)

    override var selectedIndicatorColor: Int = 0
        set(value) {
            field = value
            pageIndicatorView.selectedColor = value
        }

    override var unselectedIndicatorColor: Int = 0
        set(value) {
            field = value
            pageIndicatorView.unselectedColor = value
        }

    override fun newInstance(context: Context): View {
        return pageIndicatorView
    }

    override fun initialize(slideCount: Int) {
        pageIndicatorView.count = slideCount
        selectPosition(0)
        pageIndicatorView.setAnimationType(unwrapAnimationType(animationType))
    }

    override fun selectPosition(index: Int) {
        pageIndicatorView.selection = index
    }

    private fun unwrapAnimationType(animationType: PageIndicatorAnimationType): AnimationType? {
        return when (animationType) {
            PageIndicatorAnimationType.NONE -> AnimationType.NONE
            PageIndicatorAnimationType.COLOR -> AnimationType.COLOR
            PageIndicatorAnimationType.SCALE -> AnimationType.SCALE
            PageIndicatorAnimationType.WORM -> AnimationType.WORM
            PageIndicatorAnimationType.SLIDE -> AnimationType.SLIDE
            PageIndicatorAnimationType.FILL -> AnimationType.FILL
            PageIndicatorAnimationType.THIN_WORM -> AnimationType.THIN_WORM
            PageIndicatorAnimationType.DROP -> AnimationType.DROP
            PageIndicatorAnimationType.SWAP -> AnimationType.SWAP
            PageIndicatorAnimationType.SCALE_DOWN -> AnimationType.SCALE_DOWN
        }
    }
}
