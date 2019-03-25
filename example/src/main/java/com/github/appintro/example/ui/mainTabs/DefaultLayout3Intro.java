package com.github.appintro.example.ui.mainTabs;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.github.appintro.example.R;
import com.github.appintro.example.ui.mainTabs.intro.DefaultIntro3;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * Created by Anurag on 3/19/19.
 */

public class DefaultLayout3Intro extends Fragment {

    @SuppressWarnings("ConstantConditions")
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Button demo = getView().findViewById(R.id.intro3);
        demo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity().getBaseContext(), DefaultIntro3.class));
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tab_default_intro3, container, false);
    }
}
