package com.github.paolorotolo.appintroexample.ui.mainTabs;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.github.paolorotolo.appintroexample.R;
import com.github.paolorotolo.appintroexample.ui.mainTabs.intro.DefaultIntro2;

/**
 * Created by andrew on 11/17/16.
 */

public class DefaultLayout2Intro extends Fragment {

    @SuppressWarnings("ConstantConditions")
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Button demo = (Button) getView().findViewById(R.id.intro2);
        demo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity().getBaseContext(), DefaultIntro2.class));
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tab_default_intro2, container, false);
    }
}
