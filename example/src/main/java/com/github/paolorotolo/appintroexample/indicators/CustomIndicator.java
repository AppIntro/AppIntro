package com.github.paolorotolo.appintroexample.indicators;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.github.paolorotolo.appintro.IndicatorController;
import com.github.paolorotolo.appintroexample.BaseIntro;
import com.github.paolorotolo.appintroexample.R;
import com.github.paolorotolo.appintroexample.SampleSlide;

import java.util.Locale;

public class CustomIndicator extends BaseIntro {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addSlide(SampleSlide.newInstance(R.layout.intro));
        addSlide(SampleSlide.newInstance(R.layout.intro2));
        addSlide(SampleSlide.newInstance(R.layout.intro3));
        addSlide(SampleSlide.newInstance(R.layout.intro4));

        setCustomIndicator(new CustomIndicatorController());
    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);

        loadMainActivity();
        Toast.makeText(getApplicationContext(), getString(R.string.skip),
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);

        loadMainActivity();
    }

    public void getStarted(View v) {
        loadMainActivity();
    }

    private class CustomIndicatorController implements IndicatorController {
        private static final int FIRST_PAGE_NUM = 0;
        private TextView mTextView;
        private int mSlideCount;

        @Override
        public View newInstance(@NonNull Context context) {
            mTextView = (TextView) View.inflate(context, R.layout.custom_indicator, null);
            return mTextView;
        }

        @Override
        public void initialize(int slideCount) {
            mSlideCount = slideCount;
            selectPosition(FIRST_PAGE_NUM);
        }

        @Override
        public void selectPosition(int index) {
            mTextView.setText(String.format(Locale.US, "%d/%d", index + 1, mSlideCount));
        }

        @Override
        public void setSelectedIndicatorColor(int color) {

        }

        @Override
        public void setUnselectedIndicatorColor(int color) {

        }
    }
}
