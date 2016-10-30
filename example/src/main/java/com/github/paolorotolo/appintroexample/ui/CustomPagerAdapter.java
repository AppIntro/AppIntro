package com.github.paolorotolo.appintroexample.ui;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.paolorotolo.appintroexample.ui.CustomPagerEnum;

/**
 * Created by avluis on 10/04/2016.
 * Our Adapter for our Custom View Pager.
 */

class CustomPagerAdapter extends PagerAdapter {

    private final Context cxt;

    CustomPagerAdapter(Context context) {
        cxt = context;
    }

    @Override
    public Object instantiateItem(ViewGroup collection, int position) {
        CustomPagerEnum customPagerEnum = CustomPagerEnum.values()[position];
        LayoutInflater inflater = LayoutInflater.from(cxt);
        ViewGroup layout = (ViewGroup) inflater.inflate(
                customPagerEnum.getLayoutResId(), collection, false);
        collection.addView(layout);
        return layout;
    }

    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {
        collection.removeView((View) view);
    }

    @Override
    public int getCount() {
        return CustomPagerEnum.values().length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        CustomPagerEnum customPagerEnum = CustomPagerEnum.values()[position];
        return cxt.getString(customPagerEnum.getTitleResId());
    }
}
