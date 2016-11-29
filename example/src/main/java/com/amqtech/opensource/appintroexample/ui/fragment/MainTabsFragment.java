package com.amqtech.opensource.appintroexample.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
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
    CharSequence Titles[] = {getResources().getString(R.string.tg1_tab_1),
            getResources().getString(R.string.tg1_tab_2),
            getResources().getString(R.string.tg1_tab_3),
            getResources().getString(R.string.tg1_tab_4)};
    int Numboftabs = 4;

    public MainTabsFragment() {
        //required empty constructor
    }

    @SuppressWarnings({"ConstantConditions", "deprecation"})
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        adapter = new MainPagerAdapter(getActivity().getFragmentManager(), Titles, Numboftabs);

        pager = (ViewPager) getView().findViewById(R.id.pager);
        pager.setAdapter(adapter);

        tabs = (TabLayout) getView().findViewById(R.id.tabs);
        tabs.setBackgroundColor(getResources().getColor(R.color.blue));
        tabs.setDistributeEvenly(false);
        tabs.setCustomTabColorizer(new TabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.blue);
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
