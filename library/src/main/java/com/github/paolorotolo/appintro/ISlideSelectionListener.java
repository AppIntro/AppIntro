package com.github.paolorotolo.appintro;

public interface ISlideSelectionListener {
    /**
     * Called when this slide becomes selected
     */
    void onSlideSelected();

    /**
     * Called when this slide gets deselected.
     * Please note, that this method won't be called if the user exits the intro in any way.
     */
    void onSlideDeselected();
}
