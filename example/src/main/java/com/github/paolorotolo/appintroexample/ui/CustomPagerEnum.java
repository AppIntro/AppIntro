package com.github.paolorotolo.appintroexample.ui;

import com.github.paolorotolo.appintroexample.R;

/**
 * Created by avluis on 10/04/2016.
 * Our Model for our Custom View Pager Adapter.
 */

enum CustomPagerEnum {

    MAIN(R.string.main_title, R.layout.view_pager_main),
    PERMISSIONS(R.string.permissions_title, R.layout.view_pager_permissions),
    ANIMATIONS(R.string.animations_title, R.layout.view_pager_animations),
    INDICATORS(R.string.indicators_title, R.layout.view_pager_indicators),
    CUSTOM(R.string.custom_title, R.layout.view_pager_custom);

    private final int titleResId;
    private final int layoutResId;

    CustomPagerEnum(int titleResId, int layoutResId) {
        this.titleResId = titleResId;
        this.layoutResId = layoutResId;
    }

    public int getTitleResId() {
        return titleResId;
    }

    public int getLayoutResId() {
        return layoutResId;
    }
}
