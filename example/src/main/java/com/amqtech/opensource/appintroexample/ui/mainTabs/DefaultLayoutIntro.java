package com.amqtech.opensource.appintroexample.ui.mainTabs;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.amqtech.opensource.appintroexample.ui.mainTabs.intro.DefaultIntro;
import com.github.paolorotolo.appintroexample.R;

/**
 * Created by andrew on 11/17/16.
 */

public class DefaultLayoutIntro extends Fragment {

    @SuppressWarnings("ConstantConditions")
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Button demo = getView().findViewById(R.id.intro1);
        demo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity().getBaseContext(), DefaultIntro.class));
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tab_default_intro, container, false);
    }
}
