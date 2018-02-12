package com.amqtech.opensource.appintroexample.ui.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amqtech.opensource.appintroexample.ui.permsTabs.PermsPagerAdapter;
import com.amqtech.opensource.appintroexample.util.TabLayout;
import com.github.paolorotolo.appintroexample.R;

/**
 * Created by andrew on 11/17/16.
 */

public class PermissionTabsFragment extends Fragment {

    ViewPager pager;
    PermsPagerAdapter adapter;
    TabLayout tabs;
    CharSequence Titles[] = {"Layout 1", "Layout 2"};
    int Numboftabs = 2;

    public PermissionTabsFragment() {
        //required empty constructor
    }

    @SuppressWarnings({"ConstantConditions", "deprecation"})
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        adapter = new PermsPagerAdapter(getChildFragmentManager(), Titles, Numboftabs);

        pager = getView().findViewById(R.id.permPager);
        pager.setAdapter(adapter);

        tabs = getView().findViewById(R.id.permTabs);
        tabs.setBackgroundColor(Color.parseColor("#1976D2"));
        tabs.setDistributeEvenly(true);
        tabs.setCustomTabColorizer(new TabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return Color.parseColor("#1976D2");
            }
        });
        tabs.setViewPager(pager);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_permission_tabs, container, false);
    }
}
