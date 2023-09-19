@file:Suppress("TooManyFunctions")

package com.github.appintro

import android.animation.ArgbEvaluator
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.ImageButton
import androidx.activity.OnBackPressedCallback
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.TooltipCompat.setTooltipText
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat.Type.systemBars
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.github.appintro.indicator.DotIndicatorController
import com.github.appintro.indicator.IndicatorController
import com.github.appintro.indicator.ProgressIndicatorController
import com.github.appintro.internal.AppIntroViewPagerController
import com.github.appintro.internal.LayoutUtil
import com.github.appintro.internal.LogHelper
import com.github.appintro.internal.PermissionWrapper
import com.github.appintro.internal.VibrationHelper
import com.github.appintro.internal.viewpager.PagerAdapter
import com.github.appintro.internal.viewpager.ViewPagerTransformer

/**
 * The AppIntro Base Class. This class is the Activity that is responsible of handling
 * the lifecycle and all the event callbacks for AppIntro.
 */
abstract class AppIntroBase : AppCompatActivity(), AppIntroViewPagerListener {

    /** The layout ID that will be used during inflation. */
    @get:LayoutRes
    protected abstract val layoutId: Int

    /**
     * A subclass of [IndicatorController] to show the progress.
     * If not provided will be initialized with a [DotIndicatorController] */
    protected var indicatorController: IndicatorController? = null

    /** Toggles all the buttons visibility (between visible and invisible). */
    protected var isButtonsEnabled: Boolean = true
        set(value) {
            field = value
            updateButtonsVisibility()
        }

    /** Toggles only the SKIP button visibility (allows the user to skip the Intro). */
    protected var isSkipButtonEnabled = true
        set(value) {
            field = value
            updateButtonsVisibility()
        }

    /** Toggles the Wizard mode (back button instead of skip). */
    protected var isWizardMode: Boolean = false
        set(value) {
            field = value
            this.isSkipButtonEnabled = !value
            updateButtonsVisibility()
        }

    /** Toggles the [IndicatorController] visibility. */
    protected var isIndicatorEnabled = true
        set(value) {
            field = value
            indicatorContainer.isVisible = value
        }

    /**
     * Toggles leaving the device's software/hardware back button.
     * If set to true, the device's back button will be ignored.
     * Note, that does does NOT lock swiping back through the slides.
     */
    protected var isSystemBackButtonLocked = false

    /** Toggles color cross-fading between slides.
     * Please note that slides should implement [SlideBackgroundColorHolder] */
    protected var isColorTransitionsEnabled = false

    /** Vibration duration in milliseconds */
    protected var vibrateDuration = DEFAULT_VIBRATE_DURATION

    /**
     * Toggles vibration on button clicks If you enable it, don't forget to grant
     * vibration permissions on your AndroidManifest.xml file
     * */
    protected var isVibrate = false

    /**
     * Read-only property with the total number of slides for this AppIntro.
     */
    protected val totalSlidesNumber: Int get() = slidesNumber

    // Private Fields

    private lateinit var pagerAdapter: PagerAdapter
    private lateinit var pagerController: AppIntroViewPagerController
    private var slidesNumber: Int = 0
    private var savedCurrentItem: Int = 0
    private var currentlySelectedItem = -1
    private val fragments: MutableList<Fragment> = mutableListOf()

    private lateinit var nextButton: View
    private lateinit var doneButton: View
    private lateinit var skipButton: View
    private lateinit var backButton: View
    private lateinit var indicatorContainer: ViewGroup

    // Asks the ViewPager for the current slide number. Useful to query the [permissionsMap]
    private val currentSlideNumber: Int
        get() = pagerController.getCurrentSlideNumber(fragments.size)

    /** HashMap that contains the [PermissionWrapper] objects */
    private var permissionsMap = HashMap<Int, PermissionWrapper>()

    private var retainIsButtonsEnabled = true

    private val argbEvaluator = ArgbEvaluator()

    internal val isRtl: Boolean
        get() = LayoutUtil.isRtl(this)

    /*
     PUBLIC API
     =================================== */

    /**
     * Adds a new slide at the end of the Intro
     * @param fragment Instance of Fragment which should be added as slide.
     */
    protected fun addSlide(fragment: Fragment) {
        if (isRtl) {
            fragments.add(0, fragment)
            pagerAdapter.notifyItemInserted(0)
        } else {
            fragments.add(fragment)
            pagerAdapter.notifyItemInserted(fragments.size)
        }
        if (isWizardMode) {
            pagerController.setOffscreenPageLimit(fragments.size)
        }
    }

    /**
     * Called by the user to associate permissions with slides.
     *
     * @param permissions - Array of permissions
     * @param slideNumber - The slide at which permission is to be asked.
     * @param required - Whether the user can change this slide without granting the permissions.
     */
    @JvmOverloads
    protected fun askForPermissions(permissions: Array<String>, slideNumber: Int, required: Boolean = true) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (slideNumber <= 0) {
                error("Invalid Slide Number: $slideNumber")
            } else {
                permissionsMap[slideNumber] = PermissionWrapper(permissions, slideNumber, required)
            }
        }
    }

    /** Moves the AppIntro to the previous slide */
    protected fun goToPreviousSlide() {
        pagerController.goToPreviousSlide()
    }

    /** Moves the AppIntro to the next slide */
    @JvmOverloads
    protected fun goToNextSlide(isLastSlide: Boolean = pagerController.isLastSlide(fragments.size)) {
        if (isLastSlide) {
            onIntroFinished()
        } else {
            pagerController.goToNextSlide()
            onNextSlide()
        }
    }

    /** Enable the Immersive Sticky Mode */
    protected fun setImmersiveMode() {
        WindowCompat.getInsetsController(window, window.decorView).apply {
            systemBarsBehavior =
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            hide(systemBars())
        }
    }

    /** Customize the color of the Status Bar */
    protected fun setStatusBarColor(@ColorInt color: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // We set the light status bar/translucent first via the WindowInsetsControllerCompat
            WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = true
            window.statusBarColor = color
        }
    }

    /** Customize the color of the Status Bar */
    protected fun setStatusBarColorRes(@ColorRes color: Int) {
        setStatusBarColor(ContextCompat.getColor(this, color))
    }

    /** Customize the color of the Navigation Bar */
    protected fun setNavBarColor(@ColorInt color: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.navigationBarColor = color
        }
    }

    /** Customize the color of the Navigation Bar */
    protected fun setNavBarColorRes(@ColorRes color: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.navigationBarColor = ContextCompat.getColor(this, color)
        }
    }

    /** Toggle the Status Bar visibility */
    protected fun showStatusBar(show: Boolean) {
        val controller = WindowCompat.getInsetsController(window, window.decorView)
        if (show) {
            controller.show(systemBars())
        } else {
            controller.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_BARS_BY_SWIPE
            controller.hide(systemBars())
        }
    }

    /**
     * @param lock Set true to disable forward swiping. False to enable.
     * @deprecated setNextPageSwipeLock has been deprecated in favor of setSwipeLock or SlidePolicy
     */
    @Deprecated(
        "setNextPageSwipeLock has been deprecated in favor of setSwipeLock or SlidePolicy",
        ReplaceWith("setSwipeLock"),
        DeprecationLevel.ERROR
    )
    @Suppress("UnusedPrivateMember", "UNUSED_PARAMETER")
    protected fun setNextPageSwipeLock(lock: Boolean) {
        LogHelper.w(
            TAG,
            "Calling setNextPageSwipeLock has not effect here. Please switch to setSwipeLock or SlidePolicy"
        )
    }

    /**
     * Setting to disable swiping left and right on current page. This also
     * hides/shows the Next and Done buttons accordingly.
     *
     * @param lock Set true to disable forward swiping. False to enable.
     */
    protected fun setSwipeLock(lock: Boolean) {
        // We retain the button state in order to be able to restore
        // it properly afterwards.
        if (lock) {
            retainIsButtonsEnabled = this.isButtonsEnabled
            this.isButtonsEnabled = true
        } else {
            this.isButtonsEnabled = retainIsButtonsEnabled
        }
        pagerController.isFullPagingEnabled = !lock
    }

    /**
     * Set a [ProgressIndicatorController] instead of dots (a [DotIndicatorController].
     * This is recommended for a large amount of slides. In this case there
     * could not be enough space to display all dots on smaller device screens.
     */
    protected fun setProgressIndicator() {
        indicatorController = ProgressIndicatorController(this)
    }

    /**
     * Overrides color of selected and unselected indicator colors
     * @param selectedIndicatorColor your selected color
     * @param unselectedIndicatorColor your unselected color
     */
    protected fun setIndicatorColor(
        @ColorInt selectedIndicatorColor: Int,
        @ColorInt unselectedIndicatorColor: Int
    ) {
        indicatorController?.selectedIndicatorColor = selectedIndicatorColor
        indicatorController?.unselectedIndicatorColor = unselectedIndicatorColor
    }

    /*
     TRANSFORMERS
     =================================== */

    /** Allows to specify one of the [AppIntroPageTransformerType] for the ViewPager */
    protected fun setTransformer(appIntroTransformer: AppIntroPageTransformerType) {
        pagerController.setAppIntroPageTransformer(appIntroTransformer)
    }

    /** Overrides viewpager transformer with you custom [ViewPagerTransformer] */
    protected fun setCustomTransformer(transformer: ViewPager2.PageTransformer?) {
        pagerController.setPageTransformer(transformer)
    }

    /*
     CALLBACKS
     =================================== */

    /**
     * Called by [AppIntroViewPager] when the user swipes forward on a slide which contains permissions.
     * [.setSwipeLock] is called to prevent user from swiping while permission is requested.
     * [.checkAndRequestPermissions] is called to request permissions from the user.
     */
    override fun onUserRequestedPermissionsDialog() {
        LogHelper.d(TAG, "Requesting Permissions on $currentSlideNumber")
        requestPermissions()
    }

    /**
     * Called when the user checks "Don't ask again" in the permission dialog
     * or when the permission is disabled by the device policy.
     *
     * @param permissionName - Name of the permission disabled.
     */
    protected open fun onUserDisabledPermission(permissionName: String) {}

    /**
     * Called when the user denies a permission to the app.
     * This method is called both when the permission was required (so we
     * block the user) and when it was not (so the user can actually proceed).
     *
     * @param permissionName - Name of the permission denied.
     */
    protected open fun onUserDeniedPermission(permissionName: String) {}

    /**
     * Called after a new slide has been selected
     * @param position Position of the new selected slide
     */
    protected open fun onPageSelected(position: Int) {}

    /** Called when the user clicked the done button */
    protected open fun onDonePressed(currentFragment: Fragment?) {}

    /** Called when the user clicked the next button */
    protected open fun onNextPressed(currentFragment: Fragment?) {}

    /** Called when the user clicked the skip button */
    protected open fun onSkipPressed(currentFragment: Fragment?) {}

    /** Called when the user request to go to the next slide either
     *  via keyboard (Enter, etc.) or via button */
    protected open fun onNextSlide() {}

    /** Called when the AppIntro reached the end. */
    protected open fun onIntroFinished() {}

    /**
     * Called when the selected fragment changed.
     * @param oldFragment Instance of the fragment which was displayed before.
     * This might be null if the the intro has just started.
     * @param newFragment Instance of the fragment which is displayed now.
     * This might be null if the intro has finished
     */
    protected open fun onSlideChanged(oldFragment: Fragment?, newFragment: Fragment?) {}

    /*
     LIFECYCLE
     =================================== */

    @Suppress("DEPRECATION")
    override fun onCreate(savedInstanceState: Bundle?) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        super.onCreate(savedInstanceState)

        // Add back handler
        addBackHandler()

        // We default the indicator controller to the Dotted one.
        indicatorController = DotIndicatorController(this)

        // We default to don't show the Status Bar. User can override this.
        showStatusBar(false)

        setContentView(layoutId)

        indicatorContainer = findViewById(R.id.indicator_container)
            ?: error("Missing Indicator Container: R.id.indicator_container")
        nextButton = findViewById(R.id.next) ?: error("Missing Next button: R.id.next")
        doneButton = findViewById(R.id.done) ?: error("Missing Done button: R.id.done")
        skipButton = findViewById(R.id.skip) ?: error("Missing Skip button: R.id.skip")
        backButton = findViewById(R.id.back) ?: error("Missing Back button: R.id.back")

        setTooltipText(nextButton, getString(R.string.app_intro_next_button))
        if (skipButton is ImageButton) {
            setTooltipText(skipButton, getString(R.string.app_intro_skip_button))
        }
        if (doneButton is ImageButton) {
            setTooltipText(doneButton, getString(R.string.app_intro_done_button))
        }
        if (backButton is ImageButton) {
            setTooltipText(backButton, getString(R.string.app_intro_back_button))
        }

        if (isRtl) {
            nextButton.scaleX = -1f
            backButton.scaleX = -1f
        }

        pagerAdapter = PagerAdapter(this@AppIntroBase, fragments)
        pagerController = AppIntroViewPagerController(
            viewPager = findViewById(R.id.view_pager),
            viewPagerGestureOverlay = findViewById(R.id.pager_gesture_overlay)
        )

        doneButton.setOnClickListener(NextSlideOnClickListener(isLastSlide = true))
        nextButton.setOnClickListener(NextSlideOnClickListener(isLastSlide = false))
        backButton.setOnClickListener { pagerController.goToPreviousSlide() }
        skipButton.setOnClickListener {
            dispatchVibration()
            onSkipPressed(getPagerItem(pagerController.getCurrentItem()))
        }

        pagerController.setAdapter(this.pagerAdapter)
        pagerController.registerOnPageChangeCallback(OnPageChangeCallback())
        pagerController.onNextPageRequestedListener = this
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        slidesNumber = fragments.size
        initializeIndicator()

        // Makes sure we correctly retain the `isButtonsEnabled` just after onCreate
        retainIsButtonsEnabled = isButtonsEnabled

        // Required for triggering onPageSelected and onSlideChanged for the first page.
        if (isRtl) {
            pagerController.setCurrentViewPagerItem(fragments.size - savedCurrentItem)
        } else {
            pagerController.setCurrentViewPagerItem(savedCurrentItem)
        }

        pagerController.post {
            if (pagerController.getCurrentItem() < pagerAdapter.itemCount) {
                dispatchSlideChangedCallbacks(
                    null,
                    getPagerItem(pagerController.getCurrentItem())
                )
            } else {
                // Close the intro if there are no slides to show
                finish()
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.apply {
            putInt(ARG_BUNDLE_SLIDES_NUMBER, slidesNumber)
            putBoolean(ARG_BUNDLE_RETAIN_IS_BUTTONS_ENABLED, retainIsButtonsEnabled)
            putBoolean(ARG_BUNDLE_IS_BUTTONS_ENABLED, isButtonsEnabled)
            putBoolean(ARG_BUNDLE_IS_SKIP_BUTTON_ENABLED, isSkipButtonEnabled)
            putBoolean(ARG_BUNDLE_IS_INDICATOR_ENABLED, isIndicatorEnabled)

            // We can't use pager.currentItem as we need the current item that is RTL-invariant.
            val currentItem = if (isRtl) {
                fragments.size - pagerController.getCurrentItem()
            } else {
                pagerController.getCurrentItem()
            }
            putInt(ARG_BUNDLE_CURRENT_ITEM, currentItem)
            putBoolean(ARG_BUNDLE_IS_FULL_PAGING_ENABLED, pagerController.isFullPagingEnabled)

            putSerializable(ARG_BUNDLE_PERMISSION_MAP, permissionsMap)

            putBoolean(ARG_BUNDLE_COLOR_TRANSITIONS_ENABLED, isColorTransitionsEnabled)
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        with(savedInstanceState) {
            slidesNumber = getInt(ARG_BUNDLE_SLIDES_NUMBER)
            retainIsButtonsEnabled = getBoolean(ARG_BUNDLE_RETAIN_IS_BUTTONS_ENABLED)
            isButtonsEnabled = getBoolean(ARG_BUNDLE_IS_BUTTONS_ENABLED)
            isSkipButtonEnabled = getBoolean(ARG_BUNDLE_IS_SKIP_BUTTON_ENABLED)
            isIndicatorEnabled = getBoolean(ARG_BUNDLE_IS_INDICATOR_ENABLED)

            savedCurrentItem = getInt(ARG_BUNDLE_CURRENT_ITEM)
            pagerController.isFullPagingEnabled = getBoolean(ARG_BUNDLE_IS_FULL_PAGING_ENABLED)

            @Suppress("UNCHECKED_CAST", "DEPRECATION")
            permissionsMap = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                (getSerializable(ARG_BUNDLE_PERMISSION_MAP, HashMap::class.java) as HashMap<Int, PermissionWrapper>?)
                    ?: hashMapOf()
            } else {
                (getSerializable(ARG_BUNDLE_PERMISSION_MAP) as HashMap<Int, PermissionWrapper>?)
                    ?: hashMapOf()
            }

            isColorTransitionsEnabled = getBoolean(ARG_BUNDLE_COLOR_TRANSITIONS_ENABLED)
        }
    }

    private fun initializeIndicator() {
        indicatorContainer.addView(indicatorController?.newInstance(this))
        indicatorController?.initialize(slidesNumber)
        indicatorController?.selectPosition(currentlySelectedItem)
    }

    override fun onKeyDown(code: Int, event: KeyEvent): Boolean {
        // Handle the navigation with 'Enter' or Dpad events.
        if (code == KeyEvent.KEYCODE_ENTER ||
            code == KeyEvent.KEYCODE_BUTTON_A ||
            code == KeyEvent.KEYCODE_DPAD_CENTER
        ) {
            val isLastSlide = pagerController.isLastSlide(fragments.size)
            goToNextSlide(isLastSlide)
            if (isLastSlide) {
                // We emulate the onDonePressed here to keep backward compatibility
                // with the previous API (users expect an onDonePressed to kill the Activity).
                // Ideally we should get rid of this extra callback in one of the future release.
                onDonePressed(getPagerItem(pagerController.getCurrentItem()))
            }
            return false
        }
        return super.onKeyDown(code, event)
    }

    /*
     BACK HANDLER
     =================================== */

    private fun addBackHandler() {
        onBackPressedDispatcher.addCallback(
            this,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    // Do nothing if go back lock is enabled or slide has custom policy.
                    if (isSystemBackButtonLocked) {
                        return
                    }
                    if (pagerController.isFirstSlide(fragments.size)) {
                        finish()
                    } else {
                        pagerController.goToPreviousSlide()
                    }
                }
            }
        )
    }

    /*
     BUTTONS VISIBILITY FLAGS
     =================================== */

    private fun updateButtonsVisibility() {
        if (isButtonsEnabled) {
            val isLastSlide = pagerController.isLastSlide(fragments.size)
            val isFirstSlide = pagerController.isFirstSlide(fragments.size)
            nextButton.isVisible = !isLastSlide
            doneButton.isVisible = isLastSlide
            skipButton.isVisible = isSkipButtonEnabled && !isLastSlide
            backButton.isVisible = isWizardMode && !isFirstSlide
        } else {
            nextButton.isVisible = false
            doneButton.isVisible = false
            backButton.isVisible = false
            skipButton.isVisible = false
        }
    }

    /*
     SLIDE POLICY
     =================================== */

    /**
     * Called before a slide change happens. By returning false, one can
     * disallow the slide change.
     *
     * @return true, if the slide change should be allowed, else false
     */
    override fun onCanRequestNextPage(): Boolean {
        val currentFragment = getPagerItem(pagerController.getCurrentItem())

        // Check if the current fragment implements SlidePolicy, else a change is always allowed.
        return if (currentFragment is SlidePolicy && !currentFragment.isPolicyRespected) {
            LogHelper.d(TAG, "Slide policy not respected, denying change request.")
            false
        } else {
            LogHelper.d(TAG, "Change request will be allowed.")
            true
        }
    }

    override fun onIllegallyRequestedNextPage() {
        val currentFragment = getPagerItem(pagerController.getCurrentItem())
        if (currentFragment is SlidePolicy) {
            if (!currentFragment.isPolicyRespected) {
                currentFragment.onUserIllegallyRequestedNextPage()
            }
        }
    }

    /*
     PERMISSION
     =================================== */

    private fun shouldRequestPermission() = permissionsMap.containsKey(currentSlideNumber)

    // Request one or more permission to the Android SDK.
    private fun requestPermissions() {
        setSwipeLock(true)
        val permissionToRequest = permissionsMap[currentSlideNumber]
        permissionToRequest?.let {
            ActivityCompat.requestPermissions(
                this,
                it.permissions,
                PERMISSIONS_REQUEST_ALL_PERMISSIONS
            )
        }
    }

    /**
     * Handles the Permission Result from Android SDK.
     */
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        setSwipeLock(false)

        if (requestCode != PERMISSIONS_REQUEST_ALL_PERMISSIONS) {
            return
        }

        val deniedPermissions = grantResults
            .mapIndexed { index, result -> (permissions[index] to result) }
            .filter { (_, result) -> result == PackageManager.PERMISSION_DENIED }
            .map { (permission, _) -> permission }

        // Check if all permissions are granted.
        if (deniedPermissions.isEmpty()) {
            // If the permission request was a success, we remove
            // the permission from the permissionsMap.
            permissionsMap.remove(currentSlideNumber)
            goToNextSlide()
        } else {
            // At least one or all of the permissions have been denied.
            deniedPermissions.forEach(::handleDeniedPermission)
            // Let's force a recenter of the current slide.
            pagerController.reCenterCurrentSlide()
        }
    }

    /**
     * Function to supports the event dispatching and the handling of the
     * various "Permission Denied" scenarios.
     */
    private fun handleDeniedPermission(permission: String) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
            // Permission is denied for the first time (never ask again box is not checked).
            // Ask again explaining the usage of the permission (Show an AlertDialog or Snackbar)
            onUserDeniedPermission(permission)
        } else {
            // Permission is disabled (never ask again is checked)
            // Ask the user to go to settings to enable permission.
            onUserDisabledPermission(permission)
        }

        // If the permission was not required, we can remove it from the App and let the user proceed.
        permissionsMap[currentSlideNumber]?.let { requestedPermission ->
            if (!requestedPermission.required) {
                permissionsMap.remove(requestedPermission.position)
                goToNextSlide()
            }
        }
    }

    private fun dispatchVibration() {
        if (isVibrate) {
            VibrationHelper.vibrate(this, vibrateDuration)
        }
    }

    /**
     * Getter used to notify [AppIntroViewPager] if the currently selected slide
     * has permissions attached to it.
     */
    private val isPermissionSlide: Boolean
        get() = pagerController.getCurrentSlideNumber(fragments.size) in permissionsMap

    /** Takes care of calling all the necessary callbacks on Slide Changing. */
    private fun dispatchSlideChangedCallbacks(oldFragment: Fragment?, newFragment: Fragment?) {
        if (oldFragment is SlideSelectionListener) {
            oldFragment.onSlideDeselected()
        }
        if (newFragment is SlideSelectionListener) {
            newFragment.onSlideSelected()
        }
        onSlideChanged(oldFragment, newFragment)
    }

    /** Performs color interpolation between two slides.. */
    private fun performColorTransition(currentSlide: Fragment?, nextSlide: Fragment?, positionOffset: Float) {
        if (nextSlide == null) return

        if (currentSlide is SlideBackgroundColorHolder &&
            nextSlide is SlideBackgroundColorHolder
        ) {
            // Check if both fragments are attached to an activity,
            // otherwise getDefaultBackgroundColor may fail.
            if (currentSlide.isAdded && nextSlide.isAdded) {
                val newColor = argbEvaluator.evaluate(
                    positionOffset,
                    getSlideColor(currentSlide),
                    getSlideColor(nextSlide)
                ) as Int
                currentSlide.setBackgroundColor(newColor)
                nextSlide.setBackgroundColor(newColor)
            }
        } else {
            error("Color transitions are only available if all slides implement SlideBackgroundColorHolder.")
        }
    }

    @ColorInt
    @Suppress("DEPRECATION")
    private fun getSlideColor(slide: SlideBackgroundColorHolder): Int {
        if (slide.defaultBackgroundColorRes != 0) {
            return ContextCompat.getColor(this, slide.defaultBackgroundColorRes)
        }

        return slide.defaultBackgroundColor
    }

    private fun getPagerItem(position: Int): Fragment? = pagerAdapter.getItem(position, supportFragmentManager)

    /**
     * Onclick listener for the Next/Done button.
     * @param isLastSlide True if you're using this for the DONE button.
     */
    private inner class NextSlideOnClickListener(var isLastSlide: Boolean) : View.OnClickListener {
        override fun onClick(view: View) {
            dispatchVibration()
            // Check if changing to the next slide is allowed
            if (!onCanRequestNextPage()) {
                onIllegallyRequestedNextPage()
                return
            }
            if (shouldRequestPermission()) {
                requestPermissions()
                return
            }

            // We can successfully change slide, let's do it.
            val currentFragment = getPagerItem(pagerController.getCurrentItem())
            if (isLastSlide) {
                onDonePressed(currentFragment)
            } else {
                onNextPressed(currentFragment)
            }
            goToNextSlide(isLastSlide)
        }
    }

    /**
     * [OnPageChangeCallback] used to handle all the callbacks coming from the ViewPager.
     * Moreover, if [isColorTransitionsEnabled] a color interpolation will happen in the [onPageScrolled]
     */
    internal inner class OnPageChangeCallback : ViewPager2.OnPageChangeCallback() {

        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            if (isColorTransitionsEnabled && position < pagerAdapter.itemCount - 1) {
                val currentSlide = getPagerItem(position)
                val nextSlide = getPagerItem(position + 1)
                performColorTransition(currentSlide, nextSlide, positionOffset)
            }
        }

        override fun onPageSelected(position: Int) {
            if (slidesNumber >= 1) {
                indicatorController?.selectPosition(position)
            }
            updateButtonsVisibility()

            pagerController.isPermissionSlide = this@AppIntroBase.isPermissionSlide

            // Firing all the necessary Callbacks
            this@AppIntroBase.onPageSelected(position)
            if (slidesNumber > 0) {
                if (currentlySelectedItem == -1) {
                    dispatchSlideChangedCallbacks(
                        null,
                        getPagerItem(position)
                    )
                } else {
                    dispatchSlideChangedCallbacks(
                        getPagerItem(currentlySelectedItem),
                        getPagerItem(pagerController.getCurrentItem())
                    )
                }
            }
            currentlySelectedItem = position
        }
    }

    private companion object {
        private val TAG = LogHelper.makeLogTag(AppIntroBase::class.java)

        private const val DEFAULT_VIBRATE_DURATION = 20L
        private const val PERMISSIONS_REQUEST_ALL_PERMISSIONS = 1

        private const val ARG_BUNDLE_COLOR_TRANSITIONS_ENABLED = "colorTransitionEnabled"
        private const val ARG_BUNDLE_CURRENT_ITEM = "currentItem"
        private const val ARG_BUNDLE_IS_BUTTONS_ENABLED = "isButtonsEnabled"
        private const val ARG_BUNDLE_IS_FULL_PAGING_ENABLED = "isFullPagingEnabled"
        private const val ARG_BUNDLE_IS_INDICATOR_ENABLED = "isIndicatorEnabled"
        private const val ARG_BUNDLE_IS_SKIP_BUTTON_ENABLED = "isSkipButtonsEnabled"
        private const val ARG_BUNDLE_PERMISSION_MAP = "permissionMap"
        private const val ARG_BUNDLE_RETAIN_IS_BUTTONS_ENABLED = "retainIsButtonsEnabled"
        private const val ARG_BUNDLE_SLIDES_NUMBER = "slidesNumber"
    }
}

/** Extension property to toggle visibility/invisibility of a view */
private var View.isVisible: Boolean
    get() = this.visibility == View.VISIBLE
    set(value) {
        this.visibility = if (value) View.VISIBLE else View.INVISIBLE
    }
