package com.github.paolorotolo.appintro

import android.animation.ArgbEvaluator
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Vibrator
import android.view.KeyEvent
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.widget.ImageButton
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.TooltipCompat.setTooltipText
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.github.paolorotolo.appintro.indicator.DotIndicatorController
import com.github.paolorotolo.appintro.indicator.IndicatorController
import com.github.paolorotolo.appintro.indicator.ProgressIndicatorController
import com.github.paolorotolo.appintro.internal.LayoutUtil
import com.github.paolorotolo.appintro.internal.LogHelper
import com.github.paolorotolo.appintro.internal.PermissionWrapper
import com.github.paolorotolo.appintro.internal.viewpager.PagerAdapter
import com.github.paolorotolo.appintro.internal.viewpager.TransformType
import com.github.paolorotolo.appintro.internal.viewpager.ViewPagerTransformer

/**
 * The AppIntro Base Class. This class is the Activity that is responsible of handling
 * the lifecycle and all the event callbacks for AppIntro.
 */
abstract class AppIntroBase : AppCompatActivity(), AppIntroViewPager.OnNextPageRequestedListener {

    /** The layout ID that will be used during inflation. */
    @get:LayoutRes
    protected abstract val layoutId: Int

    /**
     * A subclass of [IndicatorController] to show the progress.
     * If not provided will be initialized with a [DotIndicatorController] */
    protected lateinit var indicatorController: IndicatorController

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
     * Please note that slides should implement [ISlideBackgroundColorHolder] */
    protected var isColorTransitionsEnabled = false

    /** Vibration duration in milliseconds */
    protected var vibrateDuration = 20L

    /**
     * Toggles vibration on button clicks If you enable it, don't forget to grant
     * vibration permissions on your AndroidManifest.xml file
     * */
    protected var isVibrateOn = false

    // Private Fields

    private lateinit var pagerAdapter: PagerAdapter
    private lateinit var pager: AppIntroViewPager
    private var slidesNumber: Int = 0
    private var savedCurrentItem: Int = 0
    private var currentlySelectedItem = -1
    private val fragments: MutableList<Fragment> = mutableListOf()

    private lateinit var nextButton: View
    private lateinit var doneButton: View
    private lateinit var skipButton: View
    private lateinit var backButton: View
    private lateinit var indicatorContainer: ViewGroup

    private var retainIsButtonEnabled = true

    private var permissionsArray = ArrayList<PermissionWrapper>()

    // Android SDK
    private lateinit var vibrator: Vibrator
    private val argbEvaluator = ArgbEvaluator()

    internal val isRtl: Boolean
        get() = LayoutUtil.isRtl(applicationContext)

    /*
     PUBLIC API
     =================================== */

    /**
     * Adds a new slide at the end of the Intro
     * @param fragment Instance of Fragment which should be added as slide.
     */
    protected fun addSlide(fragment: Fragment) {
        if (isRtl)
            fragments.add(0, fragment)
        else
            fragments.add(fragment)
        if (isWizardMode) {
            pager.offscreenPageLimit = fragments.size
        }
        pagerAdapter.notifyDataSetChanged()
    }

    /**
     * Request for a specific set of permission at a given slide.
     * @param permissions A list of permission Strings
     * @param slideNumber The slide position where to ask for permission
     */
    protected fun askForPermissions(permissions: Array<String>, slideNumber: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (slideNumber <= 0) {
                error("Invalid Slide Number: $slideNumber")
            } else {
                permissionsArray.add(PermissionWrapper(permissions, slideNumber))
            }
        }
    }

    /** Moves the AppIntro to the previous slide */
    private fun goToPreviousSlide() {
        pager.goToPreviousSlide()
    }

    /** Moves the AppIntro to the next slide */
    protected fun goToNextSlide(isLastSlide: Boolean) {
        if (isLastSlide) {
            onIntroFinished()
        } else {
            pager.goToNextSlide()
            onNextSlide()
        }
    }

    /** Enable the Immersive Sticky Mode */
    protected fun setImmersiveMode() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_FULLSCREEN
                )
        }
    }

    /** Customize the color of the Status Bar */
    protected fun setStatusBarColor(@ColorInt color: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
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
        if (show) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
    }

    /**
     * Setting to disable forward swiping right on current page and allow swiping left. If a swipe
     * left occurs, the lock state is reset and swiping is re-enabled. (one shot disable) This also
     * hides/shows the Next and Done buttons accordingly.
     *
     * @param lock Set true to disable forward swiping. False to enable.
     */
    protected fun setNextPageSwipeLock(lock: Boolean) {
        // We retain the button state in order to be able to restore
        // it properly afterwards.
        if (lock) {
            retainIsButtonEnabled = this.isButtonsEnabled
            this.isButtonsEnabled = true
        } else {
            this.isButtonsEnabled = retainIsButtonEnabled
        }
        pager.isNextPagingEnabled = !lock
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
            retainIsButtonEnabled = this.isButtonsEnabled
            this.isButtonsEnabled = true
        } else {
            this.isButtonsEnabled = retainIsButtonEnabled
        }
        pager.isFullPagingEnabled = !lock
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
        selectedIndicatorColor: Int,
        unselectedIndicatorColor: Int
    ) {
        indicatorController.selectedIndicatorColor = selectedIndicatorColor
        indicatorController.unselectedIndicatorColor = unselectedIndicatorColor
    }

    /*
     ANIMATIONS
     =================================== */

    /**
     * Sets the scroll duration factor - by default it is 1. This factor will
     * multiply duration
     * @param factor the new factor that will be applied to the scroll - default: 1
     */
    protected fun setScrollDurationFactor(factor: Int) {
        pager.setScrollDurationFactor(factor.toDouble())
    }

    /** Sets the animation of the intro to a fade animation */
    protected fun setFadeAnimation() {
        pager.setPageTransformer(true, ViewPagerTransformer(TransformType.FADE))
    }

    /** Sets the animation of the intro to a zoom animation */
    protected fun setZoomAnimation() {
        pager.setPageTransformer(true, ViewPagerTransformer(TransformType.ZOOM))
    }

    /** Sets the animation of the intro to a flow animation */
    protected fun setFlowAnimation() {
        pager.setPageTransformer(
            true,
            ViewPagerTransformer(TransformType.FLOW)
        )
    }

    /** Sets the animation of the intro to a slide over animation */
    protected fun setSlideOverAnimation() {
        pager.setPageTransformer(
            true,
            ViewPagerTransformer(TransformType.SLIDE_OVER)
        )
    }

    /** Sets the animation of the intro to a depth animation */
    protected fun setDepthAnimation() {
        pager.setPageTransformer(
            true,
            ViewPagerTransformer(TransformType.DEPTH)
        )
    }

    /** Overrides viewpager transformer with you custom [ViewPagerTransformer] */
    protected fun setCustomTransformer(transformer: ViewPager.PageTransformer?) {
        pager.setPageTransformer(true, transformer)
    }

    /*
     CALLBACKS
     =================================== */

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

    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        super.onCreate(savedInstanceState)

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

        vibrator = this.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

        pagerAdapter = PagerAdapter(supportFragmentManager, fragments)
        pager = findViewById(R.id.view_pager)

        doneButton.setOnClickListener(NextSlideOnClickListener(isLastSlide = true))
        nextButton.setOnClickListener(NextSlideOnClickListener(isLastSlide = false))
        backButton.setOnClickListener { pager.goToPreviousSlide() }
        skipButton.setOnClickListener {
            dispatchVibration()
            onSkipPressed(pagerAdapter.getItem(pager.currentItem))
        }

        pager.adapter = this.pagerAdapter
        pager.addOnPageChangeListener(OnPageChangeListener())
        pager.onNextPageRequestedListener = this

        setScrollDurationFactor(DEFAULT_SCROLL_DURATION_FACTOR)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        // Required for triggering onPageSelected and onSlideChanged for the first page.
        if (isRtl) {
            pager.currentItem = fragments.size - savedCurrentItem
        } else {
            pager.currentItem = savedCurrentItem
        }

        pager.post {
            val fragment = pagerAdapter.getItem(pager.currentItem)
            // Fragment is null when no slides are passed to AppIntro
            if (fragment != null) {
                dispatchSlideChangedCallbacks(
                    null,
                    pagerAdapter
                        .getItem(pager.currentItem)
                )
            } else {
                // Close the intro if there are no slides to show
                finish()
            }
        }
        slidesNumber = fragments.size
        initializeIndicator()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.apply {
            putBoolean("retainIsButtonEnabled", retainIsButtonEnabled)
            putBoolean("isButtonsEnabled", isButtonsEnabled)
            putBoolean("isSkipButtonEnabled", isSkipButtonEnabled)
            putBoolean("isIndicatorEnabled", isIndicatorEnabled)

            putInt("lockPage", pager.lockPage)
            putInt("currentItem", pager.currentItem)
            putBoolean("isFullPagingEnabled", pager.isFullPagingEnabled)
            putBoolean("isNextPagingEnabled", pager.isNextPagingEnabled)

            putBoolean(COLOR_TRANSITIONS_ENABLED, isColorTransitionsEnabled)
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        with(savedInstanceState) {
            retainIsButtonEnabled = getBoolean("retainIsButtonEnabled")
            isButtonsEnabled = getBoolean("isButtonsEnabled")
            isSkipButtonEnabled = getBoolean("isSkipButtonEnabled")
            isIndicatorEnabled = getBoolean("isIndicatorEnabled")

            pager.lockPage = getInt("lockPage")
            savedCurrentItem = getInt("currentItem")
            pager.isFullPagingEnabled = getBoolean("isFullPagingEnabled")
            pager.isNextPagingEnabled = getBoolean("isNextPagingEnabled")

            isColorTransitionsEnabled = getBoolean(COLOR_TRANSITIONS_ENABLED)
        }
    }

    private fun initializeIndicator() {
        if (!::indicatorController.isInitialized) {
            // Let's default the indicator to the Dotted one.
            indicatorController = DotIndicatorController(this)
        }
        indicatorContainer.addView(indicatorController.newInstance(this))
        indicatorController.initialize(slidesNumber)
        indicatorController.selectPosition(currentlySelectedItem)
    }

    override fun onKeyDown(code: Int, event: KeyEvent): Boolean {
        // Handle the navigation with 'Enter' or Dpad events.
        if (code == KeyEvent.KEYCODE_ENTER ||
            code == KeyEvent.KEYCODE_BUTTON_A ||
            code == KeyEvent.KEYCODE_DPAD_CENTER
        ) {
            val isLastSlide = pager.currentItem == pagerAdapter.count - 1
            goToNextSlide(isLastSlide)
            if (isLastSlide) {
                // We emulate the onDonePressed here to keep backward compatibility
                // with the previous API (users expect an onDonePressed to kill the Activity).
                // Ideally we should get rid of this extra callback in one of the future release.
                onDonePressed(pagerAdapter.getItem(pager.currentItem))
            }
            return false
        }
        return super.onKeyDown(code, event)
    }

    override fun onBackPressed() {
        // Do nothing if go back lock is enabled or slide has custom policy.
        if (isSystemBackButtonLocked) {
            return
        }
        if (pager.isFirstSlide(fragments.size)) {
            super.onBackPressed()
        } else {
            pager.goToPreviousSlide()
        }
    }

    /*
     BUTTONS VISIBILITY FLAGS
     =================================== */

    private fun updateButtonsVisibility() {
        if (isButtonsEnabled) {
            val isLastSlide =
                !isRtl && pager.currentItem == slidesNumber - 1 ||
                    isRtl && pager.currentItem == 0
            nextButton.isVisible = !isLastSlide
            doneButton.isVisible = isLastSlide
            skipButton.isVisible = isSkipButtonEnabled && !isLastSlide
            backButton.isVisible = isWizardMode
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
        val currentFragment = pagerAdapter.getItem(pager.currentItem)

        // Check if the current fragment implements ISlidePolicy, else a change is always allowed.
        return if (currentFragment is ISlidePolicy && !currentFragment.isPolicyRespected) {
            LogHelper.d(TAG, "Slide policy not respected, denying change request.")
            false
        } else {
            LogHelper.d(TAG, "Change request will be allowed.")
            true
        }
    }

    override fun onIllegallyRequestedNextPage() {
        val currentFragment = pagerAdapter.getItem(pager.currentItem)
        if (currentFragment is ISlidePolicy) {
            if (!currentFragment.isPolicyRespected) {
                currentFragment.onUserIllegallyRequestedNextPage()
            }
        }
    }

    /*
     PERMISSION
     =================================== */

    // Returns true if a permission has been requested
    private fun checkAndRequestPermissions(): Boolean {
        // Let's search for a matching [PermissionWrapper] for this position.
        val permissionToRequest = permissionsArray.find {
            !it.pending && pager.getNextItem(fragments.size) == it.position
        } ?: return false

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            permissionToRequest.pending = true
            requestPermissions(
                permissionToRequest.permissions,
                PERMISSIONS_REQUEST_ALL_PERMISSIONS
            )
            true
        } else {
            false
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSIONS_REQUEST_ALL_PERMISSIONS) {
            val pendingPermission = permissionsArray.find {
                it.pending && pager.getNextItem(fragments.size) == it.position
            }
            if (grantResults.isNotEmpty() &&
                grantResults[0] != PackageManager.PERMISSION_GRANTED
            ) {
                // If one of the permission failed, let's go back to the previous slide.
                pendingPermission?.pending = false
                goToPreviousSlide()
            } else {
                // If is a success, let's remove the permission from the permission array.
                permissionsArray.remove(pendingPermission)

                val isLastSlide = (pager.currentItem + 1 == slidesNumber)
                if (isLastSlide) {
                    // We emulate the onDonePressed here to keep backward compatibility
                    // with the previous API (users expect an onDonePressed to kill the Activity).
                    // Ideally we should get rid of this extra callback in one of the future release.
                    onDonePressed(pagerAdapter.getItem(pager.currentItem))
                }
            }
        } else {
            LogHelper.e(TAG, "Unexpected request code from onRequestPermissionsResult")
        }
    }

    // You must grant vibration permissions on your AndroidManifest.xml file
    @SuppressLint("MissingPermission")
    private fun dispatchVibration() {
        if (isVibrateOn) {
            vibrator.vibrate(vibrateDuration)
        }
    }

    /** Takes care of calling all the necessary callbacks on Slide Changing. */
    private fun dispatchSlideChangedCallbacks(oldFragment: Fragment?, newFragment: Fragment?) {
        if (oldFragment is ISlideSelectionListener) {
            oldFragment.onSlideDeselected()
        }
        if (newFragment is ISlideSelectionListener) {
            newFragment.onSlideSelected()
        }
        onSlideChanged(oldFragment, newFragment)
    }

    /** Performs color interpolation between two slides.. */
    private fun performColorTransition(currentSlide: Fragment?, nextSlide: Fragment?, positionOffset: Float) {
        if (currentSlide is ISlideBackgroundColorHolder &&
            nextSlide is ISlideBackgroundColorHolder
        ) {
            // Check if both fragments are attached to an activity,
            // otherwise getDefaultBackgroundColor may fail.
            if (currentSlide.isAdded && nextSlide.isAdded) {
                val newColor = argbEvaluator.evaluate(
                    positionOffset,
                    currentSlide.defaultBackgroundColor,
                    nextSlide.defaultBackgroundColor
                ) as Int
                currentSlide.setBackgroundColor(newColor)
                nextSlide.setBackgroundColor(newColor)
            }
        } else {
            error("Color transitions are only available if all slides implement ISlideBackgroundColorHolder.")
        }
    }

    /**
     * Onclick listener for the Next/Done button.
     * @param isLastSlide True if you're using this for the DONE button.
     */
    private inner class NextSlideOnClickListener(var isLastSlide: Boolean) : View.OnClickListener {
        override fun onClick(view: View) {
            dispatchVibration()
            // Check if changing to the next slide is allowed
            val isSlideChangingAllowed = onCanRequestNextPage()
            if (isSlideChangingAllowed) {
                checkAndRequestPermissions()
                val currentFragment = pagerAdapter.getItem(pager.currentItem)
                if (isLastSlide) {
                    onDonePressed(currentFragment)
                } else {
                    onNextPressed(currentFragment)
                }
                goToNextSlide(isLastSlide)
            } else {
                onIllegallyRequestedNextPage()
            }
        }
    }

    /**
     * [OnPageChangeListener] used to handle all the callbacks coming from the ViewPager.
     * Moreover, if [isColorTransitionsEnabled] a color interpolation will happen in the [onPageScrolled]
     */
    internal inner class OnPageChangeListener : ViewPager.OnPageChangeListener {

        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            if (isColorTransitionsEnabled && position < pagerAdapter.count - 1) {
                val currentSlide = pagerAdapter.getItem(position)
                val nextSlide = pagerAdapter.getItem(position + 1)
                performColorTransition(currentSlide, nextSlide, positionOffset)
            }
            checkAndRequestPermissions()
        }

        override fun onPageSelected(position: Int) {
            if (slidesNumber >= 1) {
                indicatorController.selectPosition(position)
            }

            // Allow the swipe to be re-enabled if a user swipes to a previous slide. Restore
            // state of progress button depending on global progress button setting
            if (!pager.isNextPagingEnabled) {
                if (pager.currentItem != pager.lockPage) {
                    isButtonsEnabled = retainIsButtonEnabled
                    pager.isNextPagingEnabled = true
                } else {
                    isButtonsEnabled = isButtonsEnabled
                }
            } else {
                isButtonsEnabled = isButtonsEnabled
            }

            // Firing all the necessary Callbacks
            this@AppIntroBase.onPageSelected(position)
            if (slidesNumber > 0) {
                if (currentlySelectedItem == -1) {
                    dispatchSlideChangedCallbacks(null, pagerAdapter.getItem(position))
                } else {
                    dispatchSlideChangedCallbacks(
                        pagerAdapter.getItem(currentlySelectedItem),
                        pagerAdapter.getItem(pager.currentItem)
                    )
                }
            }
            currentlySelectedItem = position
        }

        override fun onPageScrollStateChanged(state: Int) {}
    }

    protected companion object {
        private val TAG = LogHelper.makeLogTag(AppIntroBase::class.java)

        private const val DEFAULT_SCROLL_DURATION_FACTOR = 1
        private const val PERMISSIONS_REQUEST_ALL_PERMISSIONS = 1
        private const val COLOR_TRANSITIONS_ENABLED = "appintro_color_transitions_enabled"
    }
}

/** Extension property to toggle visibility/invisibility of a view */
private var View.isVisible: Boolean
    get() = this.visibility == View.VISIBLE
    set(value) {
        this.visibility = if (value) View.VISIBLE else View.INVISIBLE
    }