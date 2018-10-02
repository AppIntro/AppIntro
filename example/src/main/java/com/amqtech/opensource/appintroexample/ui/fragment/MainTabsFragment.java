package com.amqtech.opensource.appintroexample.ui.fragment;

import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amqtech.opensource.appintroexample.ui.mainTabs.MainPagerAdapter;
import com.amqtech.opensource.appintroexample.util.TabLayout;
import com.github.paolorotolo.appintroexample.R;

/**
 * Created by andrew on 11/17/16.
 */

public class MainTabsFragment extends Fragment {

    ViewPager pager;
    MainPagerAdapter adapter;
    TabLayout tabs;
    CharSequence Titles[] = {"Layout 1", "Layout 2", "Custom Layout", "Custom Background"};
    int Numboftabs = 4;

    public MainTabsFragment() {
        //required empty constructor
    }

    @SuppressWarnings({"ConstantConditions", "deprecation"})
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        adapter = new MainPagerAdapter(getChildFragmentManager(), Titles, Numboftabs);

        pager = getView().findViewById(R.id.mainPager);
        pager.setAdapter(adapter);

        tabs = getView().findViewById(R.id.mainTabs);
        tabs.setBackgroundColor(Color.parseColor("#1976D2"));
        tabs.setDistributeEvenly(false);
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
        return inflater.inflate(R.layout.fragment_main_tabs, container, false);
    }
}
