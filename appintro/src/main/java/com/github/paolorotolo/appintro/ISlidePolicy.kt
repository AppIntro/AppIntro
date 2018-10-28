package com.github.paolorotolo.appintro

interface ISlidePolicy {

    /**
     * Whether the user has fulfilled the slides policy and should be allowed to navigate through the intro further.
     * If false is returned, [.onUserIllegallyRequestedNextPage] will be called.
     *
     * @return True if the user should be allowed to leave the slide, else false.
     */
    val isPolicyRespected: Boolean

    /**
     * Called if a user tries to go to the next slide while into navigation has been locked.
     */
    fun onUserIllegallyRequestedNextPage()
}
