package com.github.paolorotolo.appintro;

import android.util.SparseArray;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;
    // HashMap<Integer, Fragment> is better substitutable by a SparseArray which has better performance
    private SparseArray<Fragment> retainedFragments;

    public PagerAdapter(FragmentManager fm, @NonNull List<Fragment> fragments) {
        super(fm);

        this.fragments = fragments;
        this.retainedFragments = new SparseArray<>();
    }

    @Override
    public Fragment getItem(int position) {
        if (!fragments.isEmpty()) {
            // Check if the fragment at this position has been retained by the PagerAdapter
            if (retainedFragments.get(position) != null)
                return retainedFragments.get(position);

            return fragments.get(position);
        }

        return null;
    }

    @Override
    public int getCount() {
        return this.fragments.size();
    }

    @NonNull
    public List<Fragment> getFragments() {
        return fragments;
    }

    @NonNull
    public Collection<Fragment> getRetainedFragments() {
        // An ArrayList is a Collection type also
        ArrayList<Fragment> retainedValues = new ArrayList<>(retainedFragments.size());
        for (int i = 0; i < retainedFragments.size(); ++i) {
            retainedValues.add(retainedFragments.get(i));
        }
        return retainedValues;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment = (Fragment) super.instantiateItem(container, position);
        retainedFragments.put(position, fragment);

        return fragment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        if (retainedFragments.get(position) != null) {
            retainedFragments.remove(position);
        }
        super.destroyItem(container, position, object);
    }
}
