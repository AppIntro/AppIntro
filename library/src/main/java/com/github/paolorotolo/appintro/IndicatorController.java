package com.github.paolorotolo.appintro;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;

/**
 * A controller that is used to provide custom indicator implementations and to control their behaviour.
 * This is used for {@link AppIntro#setCustomIndicator(IndicatorController)} and
 * {@link AppIntro2#setCustomIndicator(IndicatorController)}
 */
public interface IndicatorController {
    /**
     * Create a new instance of the view to be inserted in the AppIntro layout.
     * This method is only called once for each creation of the activity.
     * <p/>
     * {@link IndicatorController#initialize(int)} is called after this.
     *
     * @param context A context to be used for the view instantiation
     * @return An instance of the indicator view
     */
    View newInstance(@NonNull Context context);

    /**
     * Initialize the indicator view with the requested amount of elements.
     * As with {@link IndicatorController#newInstance(Context)}, this method is only called once for each creation of
     * the activity as well.
     * <p/>
     * {@link IndicatorController#newInstance(Context)} is called before this.
     *
     * @param slideCount The amount of slides present in the AppIntro
     */
    void initialize(int slideCount);

    /**
     * Select the position for the new page that became selected.
     * This method is called every time the selected page changed.
     *
     * @param index The index of the page that became selected
     */
    void selectPosition(int index);

    void setSelectedIndicatorColor(int color);

    void setUnselectedIndicatorColor(int color);
}
