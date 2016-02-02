package com.github.paolorotolo.appintro;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

import com.github.paolorotolo.appintro.MyViewPager.MyFragment;

class PagerAdapter extends MyPagerAdapter {
    private List<MyFragment> fragments;

    public PagerAdapter(FragmentManager fm, @NonNull List<MyFragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public MyFragment getItem(int position) {
        return this.fragments.get(position);
    }

    @Override
    public int getCount() {
        return this.fragments.size();
    }

    @NonNull
    public List<MyFragment> getFragments() {
        return fragments;
    }


}
