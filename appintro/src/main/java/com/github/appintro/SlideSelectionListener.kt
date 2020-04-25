package com.github.appintro

interface SlideSelectionListener {
    /**
     * Called when this slide becomes selected
     */
    fun onSlideSelected()

    /**
     * Called when this slide gets deselected.
     * Please note, that this method won't be called if the user exits the intro in any way.
     */
    fun onSlideDeselected()
}
