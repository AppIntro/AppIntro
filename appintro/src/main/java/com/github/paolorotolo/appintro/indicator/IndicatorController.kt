package com.github.paolorotolo.appintro.indicator

import android.content.Context
import android.view.View

internal const val DEFAULT_COLOR = 1

/**
 * A controller that is used to provide custom indicator implementations and to control
 * their behaviour. This is used for [AppIntro.setCustomIndicator] and
 * [AppIntro2.setCustomIndicator]
 */
interface IndicatorController {

    var selectedIndicatorColor : Int

    var unselectedIndicatorColor : Int

    /**
     * Create a new instance of the view to be inserted in the AppIntro layout.
     * This method is only called once for each creation of the activity.
     *
     * [IndicatorController.initialize] is called after this.
     *
     * @param context A context to be used for the view instantiation
     * @return An instance of the indicator view
     */
    fun newInstance(context: Context): View

    /**
     * Initialize the indicator view with the requested amount of elements.
     * As with [IndicatorController.newInstance], this method is only called once for each
     * creation of the activity as well.
     *
     * [IndicatorController.newInstance] is called before this.
     *
     * @param slideCount The amount of slides present in the AppIntro
     */
    fun initialize(slideCount: Int)

    /**
     * Select the position for the new page that became selected.
     * This method is called every time the selected page changed.
     *
     * @param index The index of the page that became selected
     */
    fun selectPosition(index: Int)
}
