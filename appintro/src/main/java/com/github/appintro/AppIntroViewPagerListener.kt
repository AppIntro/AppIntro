package com.github.appintro

/**
 * Register an instance of AppIntroViewPagerListener.
 * Before the user swipes to the next page, this listener will be called and
 * can interrupt swiping by returning false to [onCanRequestNextPage]
 *
 * [onIllegallyRequestedNextPage] will be called if the user tries to swipe and was not allowed
 * to do so (useful for showing a toast or something similar).
 *
 * [onUserRequestedPermissionsDialog] will be called when the user swipes forward on a slide
 * that contains permissions.
 */
interface AppIntroViewPagerListener {
    fun onCanRequestNextPage(): Boolean

    fun onIllegallyRequestedNextPage()

    fun onUserRequestedPermissionsDialog()
}
