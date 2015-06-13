package com.github.paolorotolo.appintroexample.slides2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.paolorotolo.appintroexample.R;

public class ThirdSlide2 extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.intro3_2, container, false);
        return v;
    }
}
