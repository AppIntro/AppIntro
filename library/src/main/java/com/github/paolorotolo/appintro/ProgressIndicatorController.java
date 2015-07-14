package com.github.paolorotolo.appintro;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ProgressBar;

public class ProgressIndicatorController implements IndicatorController {
    private ProgressBar mProgressBar;

    private static final int FIRST_PAGE_NUM = 0;

    @Override
    public View newInstance(@NonNull Context context) {
        mProgressBar = (ProgressBar) View.inflate(context, R.layout.progress_indicator, null);
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
}
