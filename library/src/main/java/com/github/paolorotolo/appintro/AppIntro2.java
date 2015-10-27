package com.github.paolorotolo.appintro;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;

import java.util.List;
import java.util.Vector;

public abstract class AppIntro2 extends AppCompatActivity {
    public final static int DEFAULT_COLOR = 1;

    private PagerAdapter mPagerAdapter;
    private AppIntroViewPager pager;
    private List<Fragment> fragments = new Vector<>();
    private List<ImageView> dots;
    private int slidesNumber;
    private Vibrator mVibrator;
    private IndicatorController mController;
    private boolean isVibrateOn = false;
    private int vibrateIntensity = 20;
    private boolean progressButtonEnabled = true;
    private int selectedIndicatorColor = DEFAULT_COLOR;
    private int unselectedIndicatorColor = DEFAULT_COLOR;
    private ImageView nextButton;
    private ImageView doneButton;

    static enum TransformType {
        FLOW,
        DEPTH,
        ZOOM,
        SLIDE_OVER,
        FADE
    }

    @Override
    final protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.intro_layout2);

        nextButton = (ImageView) findViewById(R.id.next);
        doneButton = (ImageView) findViewById(R.id.done);
        mVibrator = (Vibrator) this.getSystemService(VIBRATOR_SERVICE);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(@NonNull View v) {
                if (isVibrateOn) {
                    mVibrator.vibrate(vibrateIntensity);
                }
                pager.setCurrentItem(pager.getCurrentItem() + 1);
            }
        });

        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(@NonNull View v) {
                if (isVibrateOn) {
                    mVibrator.vibrate(vibrateIntensity);
                }
                onDonePressed();
            }
        });

        mPagerAdapter = new PagerAdapter(super.getSupportFragmentManager(), fragments);
        pager = (AppIntroViewPager) findViewById(R.id.view_pager);
        pager.setAdapter(this.mPagerAdapter);

        /**
         *  ViewPager.setOnPageChangeListener is now deprecated. Use addOnPageChangeListener() instead of it.
         */
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                if (slidesNumber > 1)
                    mController.selectPosition(position);

                // Whenever a new slide is swiped to, the button visibility state is reset
                // This allows the button to be redisplayed if a user swipes to a previous slide.
                setProgressButtonEnabled(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        init(savedInstanceState);
        slidesNumber = fragments.size();

        if (slidesNumber == 1) {
            setProgressButtonEnabled(progressButtonEnabled);
        } else {
            initController();
        }
    }

    public AppIntroViewPager getPager() {
        return pager;
    }

    private void initController() {
        if (mController == null)
            mController = new DefaultIndicatorController();

        FrameLayout indicatorContainer = (FrameLayout) findViewById(R.id.indicator_container);
        indicatorContainer.addView(mController.newInstance(this));

        mController.initialize(slidesNumber);
        if (selectedIndicatorColor != DEFAULT_COLOR)
            mController.setSelectedIndicatorColor(selectedIndicatorColor);
        if (unselectedIndicatorColor != DEFAULT_COLOR)
            mController.setUnselectedIndicatorColor(unselectedIndicatorColor);
    }

    public void selectDot(int index) {
        Resources res = getResources();
        for (int i = 0; i < fragments.size(); i++) {
            int drawableId = (i == index) ? (R.drawable.indicator_dot_white) : (R.drawable.indicator_dot_grey);
            Drawable drawable = res.getDrawable(drawableId);
            dots.get(i).setImageDrawable(drawable);
        }
        onDotSelected(index);
    }

    public void addSlide(@NonNull Fragment fragment) {
        fragments.add(fragment);
        mPagerAdapter.notifyDataSetChanged();
    }

    @NonNull
    public List<Fragment> getSlides() {
        return mPagerAdapter.getFragments();
    }

    /**
     * Setting to to display or hide the Next or Done button. This is a one-shot setting and
     * should be set on each slide, and will be reset to true if the user leaves a slide by swiping.
     * Recommend using {@link #onDotSelected(int)} (when working) to set visibility upon access to page.
     *
     * @param progressButtonEnabled Set true to display Next/Done. False to hide.
     */
    public void setProgressButtonEnabled(boolean progressButtonEnabled) {
        this.progressButtonEnabled = progressButtonEnabled;
        if (progressButtonEnabled) {
            if (pager.getCurrentItem() == slidesNumber - 1) {
                setButtonState(nextButton, false);
                setButtonState(doneButton, true);
            } else {
                setButtonState(nextButton, true);
                setButtonState(doneButton, false);
            }
        } else {
            setButtonState(nextButton, false);
            setButtonState(doneButton, false);
        }
    }

    public boolean isProgressButtonEnabled() {
        return progressButtonEnabled;
    }

    private void setButtonState(ImageView button, boolean show) {
        if (show) {
            button.setVisibility(View.VISIBLE);
        } else {
            button.setVisibility(View.INVISIBLE);
        }
    }

    public void setVibrate(boolean vibrate) {
        this.isVibrateOn = vibrate;
    }

    public void setVibrateIntensity(int intensity) {
        this.vibrateIntensity = intensity;
    }

    public void setFadeAnimation() {
        pager.setPageTransformer(true, new ViewPageTransformer(ViewPageTransformer.TransformType.FADE));
    }

    public void setZoomAnimation() {
        pager.setPageTransformer(true, new ViewPageTransformer(ViewPageTransformer.TransformType.ZOOM));
    }

    public void setFlowAnimation() {
        pager.setPageTransformer(true, new ViewPageTransformer(ViewPageTransformer.TransformType.FLOW));
    }

    public void setSlideOverAnimation() {
        pager.setPageTransformer(true, new ViewPageTransformer(ViewPageTransformer.TransformType.SLIDE_OVER));
    }

    public void setDepthAnimation() {
        pager.setPageTransformer(true, new ViewPageTransformer(ViewPageTransformer.TransformType.DEPTH));
    }

    public void setCustomTransformer(@Nullable ViewPager.PageTransformer transformer) {
        pager.setPageTransformer(true, transformer);
    }

    public void setOffScreenPageLimit(int limit) {
        pager.setOffscreenPageLimit(limit);
    }

    /**
     * Set a progress indicator instead of dots. This is recommended for a large amount of slides. In this case there
     * could not be enough space to display all dots on smaller device screens.
     */
    public void setProgressIndicator() {
        mController = new ProgressIndicatorController();
    }

    /**
     * Set a custom {@link IndicatorController} to use a custom indicator view for the {@link AppIntro2} instead of the
     * default one.
     *
     * @param controller The controller to use
     */
    public void setCustomIndicator(@NonNull IndicatorController controller) {
        mController = controller;
    }

    public abstract void init(@Nullable Bundle savedInstanceState);

    public abstract void onDonePressed();

    public void onDotSelected(int index) {
    }

    @Override
    public boolean onKeyDown(int code, KeyEvent kevent) {
        if (code == KeyEvent.KEYCODE_ENTER || code == KeyEvent.KEYCODE_BUTTON_A || code == KeyEvent.KEYCODE_DPAD_CENTER) {
            ViewPager vp = (ViewPager) this.findViewById(R.id.view_pager);
            if (vp.getCurrentItem() == vp.getAdapter().getCount() - 1) {
                onDonePressed();
            } else {
                vp.setCurrentItem(vp.getCurrentItem() + 1);
            }
            return false;
        }
        return super.onKeyDown(code, kevent);
    }

    /**
     * Set DEFAULT_COLOR for color value if you don't want to change it
     */
    public void setIndicatorColor(int selectedIndicatorColor, int unselectedIndicatorColor) {
        this.selectedIndicatorColor = selectedIndicatorColor;
        this.unselectedIndicatorColor = unselectedIndicatorColor;

        if (mController != null) {
            if (selectedIndicatorColor != DEFAULT_COLOR)
                mController.setSelectedIndicatorColor(selectedIndicatorColor);
            if (unselectedIndicatorColor != DEFAULT_COLOR)
                mController.setUnselectedIndicatorColor(unselectedIndicatorColor);
        }
    }

    /**
     * Setting to disable forward swiping right on current page and allow swiping left. If a swipe
     * left occurs, the lock state is reset and swiping is re-enabled. (one shot disable) This also
     * hides/shows the Next and Done buttons accordingly.
     *
     * @param pagingState Set true to disable forward swiping. False to enable.
     */
    public void setNextPageSwipeLock(boolean pagingState) {
        pager.setNextPagingEnabled(pagingState);
        setProgressButtonEnabled(pagingState);
    }

    /**
     * Setting to disable swiping left and right on current page. This also
     * hides/shows the Next and Done buttons accordingly.
     *
     * @param pagingState Set true to disable forward swiping. False to enable.
     */
    public void setSwipeLock(boolean pagingState) {
        pager.setPagingEnabled(pagingState);
        setProgressButtonEnabled(pagingState);
    }
}
