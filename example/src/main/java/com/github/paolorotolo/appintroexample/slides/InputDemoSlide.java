package com.github.paolorotolo.appintroexample.slides;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatEditText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.paolorotolo.appintroexample.R;

public final class InputDemoSlide extends Fragment {

    private static final String DATA_TEXT = "com.github.paolorotolo.appintroexample.slides.InputDemoSlide_text";
    private static final String DATA_CHECKBOX = "com.github.paolorotolo.appintroexample.slides.InputDemoSlide_checkbox";

    private AppCompatEditText editText;
    private AppCompatCheckBox checkBox;

    private String text;
    private boolean isCheckboxChecked = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if(savedInstanceState != null) {
            text = savedInstanceState.getString(DATA_TEXT);
            isCheckboxChecked = savedInstanceState.getBoolean(DATA_CHECKBOX);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(DATA_TEXT, editText.getText().toString());
        outState.putBoolean(DATA_CHECKBOX, checkBox.isChecked());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.slide_input, container, false);

        editText = (AppCompatEditText)view.findViewById(R.id.slide_input_edittext);
        checkBox = (AppCompatCheckBox) view.findViewById(R.id.slide_input_checkbox);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        editText.setText(text);
        checkBox.setChecked(isCheckboxChecked);
    }
}
