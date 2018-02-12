package com.github.paolorotolo.appintro;

import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.paolorotolo.appintro.util.CustomFontCache;
import com.github.paolorotolo.appintro.util.LogHelper;

public abstract class AppIntro extends AppIntroBase {
    private static final String TAG = LogHelper.makeLogTag(AppIntro.class);

    @Override
    protected int getLayoutId() {
        return R.layout.intro_layout;
    }

    /**
     * Override viewpager bar color
     *
     * @param color your color resource
     */
    public void setBarColor(@ColorInt final int color) {
        LinearLayout bottomBar = findViewById(R.id.bottom);
        bottomBar.setBackgroundColor(color);
    }

    /**
     * Override next button arrow color
     *
     * @param color your color
     */
    public void setNextArrowColor(@ColorInt final int color) {
        ImageButton nextButton = findViewById(R.id.next);
        nextButton.setColorFilter(color);
    }

    /**
     * Override separator color
     *
     * @param color your color resource
     */
    public void setSeparatorColor(@ColorInt final int color) {
        TextView separator = findViewById(R.id.bottom_separator);
        separator.setBackgroundColor(color);
    }

    /**
     * Override skip text
     *
     * @param text your text
     */
    public void setSkipText(@Nullable final CharSequence text) {
        TextView skipText = findViewById(R.id.skip);
        skipText.setText(text);
    }

    /**
     * Override skip text typeface
     *
     * @param typeURL URL of font file located in Assets folder
     */
    public void setSkipTextTypeface(@Nullable final String typeURL) {
        TextView skipText = findViewById(R.id.skip);
        if (CustomFontCache.get(typeURL, this) != null) {
            skipText.setTypeface(CustomFontCache.get(typeURL, this));
        }
    }

    /**
     * Override done text
     *
     * @param text your text
     */
    public void setDoneText(@Nullable final CharSequence text) {
        TextView doneText = findViewById(R.id.done);
        doneText.setText(text);
    }

    /**
     * Override done text typeface
     *
     * @param typeURL your text
     */
    public void setDoneTextTypeface(@Nullable final String typeURL) {
        TextView doneText = findViewById(R.id.done);
        if (CustomFontCache.get(typeURL, this) != null) {
            doneText.setTypeface(CustomFontCache.get(typeURL, this));
        }
    }

    /**
     * Override done button text color
     *
     * @param colorDoneText your color resource
     */
    public void setColorDoneText(@ColorInt final int colorDoneText) {
        TextView doneText = findViewById(R.id.done);
        doneText.setTextColor(colorDoneText);
    }

    /**
     * Override skip button color
     *
     * @param colorSkipButton your color resource
     */
    public void setColorSkipButton(@ColorInt final int colorSkipButton) {
        TextView skip = findViewById(R.id.skip);
        skip.setTextColor(colorSkipButton);
    }

    /**
     * Override Next button
     *
     * @param imageNextButton your drawable resource
     */
    public void setImageNextButton(final Drawable imageNextButton) {
        final ImageView nextButton = findViewById(R.id.next);
        nextButton.setImageDrawable(imageNextButton);
    }

    /**
     * Shows or hides Done button, replaced with setProgressButtonEnabled
     *
     * @deprecated use {@link #setProgressButtonEnabled(boolean)} instead.
     */
    @Deprecated
    public void showDoneButton(boolean showDone) {
        setProgressButtonEnabled(showDone);
    }

    /**
     * Show or hide the Separator line.
     * This is a static setting and Separator state is maintained across slides
     * until explicitly changed.
     *
     * @param showSeparator Set : true to display. false to hide.
     */
    public void showSeparator(boolean showSeparator) {
        TextView bottomSeparator = findViewById(R.id.bottom_separator);
        if(showSeparator) {
            bottomSeparator.setVisibility(View.VISIBLE);
        } else {
            bottomSeparator.setVisibility(View.INVISIBLE);
        }
    }
}
