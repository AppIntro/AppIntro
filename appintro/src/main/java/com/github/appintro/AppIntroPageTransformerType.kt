package com.github.appintro

import androidx.annotation.IdRes

/**
 * Sealed class to represent all the possible Page Transformers
 * offered by AppIntro.
 */
sealed class AppIntroPageTransformerType {
    /** Sets the animation of the intro to a flow animation */
    object Flow : AppIntroPageTransformerType()

    /** Sets the animation of the intro to a depth animation */
    object Depth : AppIntroPageTransformerType()

    /** Sets the animation of the intro to a zoom animation */
    object Zoom : AppIntroPageTransformerType()

    /** Sets the animation of the intro to a slide over animation */
    object SlideOver : AppIntroPageTransformerType()

    /** Sets the animation of the intro to a fade animation */
    object Fade : AppIntroPageTransformerType()

    /**
     * Sets the animation of the intro to a parallax animation
     * @property titleParallaxFactor Parallax factor of title
     * @property imageParallaxFactor Parallax factor of image
     * @property descriptionParallaxFactor Parallax factor of description
     * @property titleViewId The ID to use for the title view to animate
     * @property imageViewId The ID to use for the image view to animate
     * @property descriptionViewId The ID to use for the description view to animate
     */
    class Parallax(
        val titleParallaxFactor: Double = 1.0,
        val imageParallaxFactor: Double = -1.0,
        val descriptionParallaxFactor: Double = 2.0,
        @IdRes val titleViewId: Int = R.id.title,
        @IdRes val imageViewId: Int = R.id.image,
        @IdRes val descriptionViewId: Int = R.id.description,
    ) : AppIntroPageTransformerType()
}
