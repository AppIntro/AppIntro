package com.github.app.javaexample.ui.custom.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.github.app.javaexample.R;
import com.github.appintro.SlidePolicy;


public class CustomSlidePolicyFragment extends Fragment implements SlidePolicy {

    private CheckBox checkBox;
    private static CustomSlidePolicyFragment instance;

    public static CustomSlidePolicyFragment newIntance(){
        if (instance == null) {
            return new CustomSlidePolicyFragment();
        }
      return instance;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.intro_slide_policy,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        checkBox = view.findViewById(R.id.check_box);
    }

    @Override
    public boolean isPolicyRespected() {
        return checkBox.isChecked();
    }

    @Override
    public void onUserIllegallyRequestedNextPage() {
        Toast.makeText(
                requireContext(),
                R.string.please_select_the_checkbox_before_proceeding,
                Toast.LENGTH_SHORT
        ).show();
    }
}
