package com.github.paolorotolo.appintro;

import android.content.Context;
import android.graphics.PorterDuff;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ProgressBar;

public class ProgressIndicatorController implements IndicatorController {
    public final static int DEFAULT_COLOR = 1;
    private static final int FIRST_PAGE_NUM = 0;
    int selectedDotColor = DEFAULT_COLOR;
    int unselectedDotColor = DEFAULT_COLOR;
    private ProgressBar mProgressBar;

    @Override
    public View newInstance(@NonNull Context context) {
        mProgressBar = (ProgressBar) View.inflate(context, R.layout.progress_indicator, null);
        if (selectedDotColor != DEFAULT_COLOR)
            mProgressBar.getProgressDrawable().setColorFilter(selectedDotColor,
                    PorterDuff.Mode.SRC_IN);
        if (unselectedDotColor != DEFAULT_COLOR)
            mProgressBar.getIndeterminateDrawable().setColorFilter(unselectedDotColor,
                    PorterDuff.Mode.SRC_IN);

        return mProgressBar;
    }

    @Override
    public void initialize(int slideCount) {
        mProgressBar.setMax(slideCount);
        selectPosition(FIRST_PAGE_NUM);
    }

    @Override
    public void selectPosition(int index) {
        mProgressBar.setProgress(index + 1);
    }

    @Override
    public void setSelectedIndicatorColor(int color) {
        this.selectedDotColor = color;
        if (mProgressBar != null)
            mProgressBar.getProgressDrawable().setColorFilter(color, PorterDuff.Mode.SRC_IN);
    }

    @Override
    public void setUnselectedIndicatorColor(int color) {
        this.unselectedDotColor = color;
        if (mProgressBar != null)
            mProgressBar.getIndeterminateDrawable().setColorFilter(color, PorterDuff.Mode.SRC_IN);
    }
}
