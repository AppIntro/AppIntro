package com.amqtech.opensource.appintroexample.ui.permsTabs;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

/**
 * Created by andrew on 11/17/16.
 */

public class PermsPagerAdapter extends FragmentStatePagerAdapter {

    private CharSequence Titles[]; // This will Store the Titles of the Tabs which are Going to be passed when PermsPagerAdapter is created
    private int NumbOfTabs; // Store the number of tabs, this will also be passed when the PermsPagerAdapter is created


    // Build a Constructor and assign the passed Values to appropriate values in the class
    public PermsPagerAdapter(FragmentManager fm, CharSequence mTitles[], int mNumbOfTabsumb) {
        super(fm);

        this.Titles = mTitles;
        this.NumbOfTabs = mNumbOfTabsumb;
    }

    //This method return the fragment for the every position in the View Pager
    @Override
    public Fragment getItem(int position) {

        if (position == 0) { // if the position is 0 we are returning the First tab
            return new PermissionsLayout1Intro();
        } else {
            return new PermissionsLayout2Intro();
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return Titles[position];
    }

    @Override
    public int getCount() {
        return NumbOfTabs;
    }
}
