
package com.github.paolorotolo.appintroexample.slides.policyDemo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.github.paolorotolo.appintro.ISlideBackgroundColorHolder;
import com.github.paolorotolo.appintro.ISlidePolicy;
import com.github.paolorotolo.appintroexample.R;

public class PolicyDemoSlide1 extends Fragment implements ISlidePolicy, ISlideBackgroundColorHolder
{

    private LinearLayout layoutContainer;
    private CheckBox checkBox;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.slide_policy_demo, container, false);

        layoutContainer = (LinearLayout) view.findViewById(R.id.slide_policy_demo_container);
        checkBox = (CheckBox) view.findViewById(R.id.slide_policy_demo_checkbox);

        return view;
    }

    @Override
    public boolean isPolicyRespected() {
        if(checkBox != null) {
            return checkBox.isChecked();
        }
        return true;
    }

    @Override
    public void onUserIllegallyRequestedNextPage() {
        Toast.makeText(getContext(), R.string.slide_policy_demo_error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public int getDefaultBackgroundColor() {
        return Color.parseColor("#F44336");
    }

    @Override
    public void setBackgroundColor(@ColorInt int backgroundColor) {
        if(layoutContainer != null) {
            layoutContainer.setBackgroundColor(backgroundColor);
        }
    }
}
