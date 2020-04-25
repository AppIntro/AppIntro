package com.github.appintro

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
     */
    class Parallax(
        val titleParallaxFactor: Double = 1.0,
        val imageParallaxFactor: Double = -1.0,
        val descriptionParallaxFactor: Double = 2.0
    ) : AppIntroPageTransformerType()
}
