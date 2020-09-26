package com.github.app.javaexample.ui;

import androidx.annotation.StringRes;

public class IntroEntry {

    @StringRes
    int title;

    @StringRes
    int description;

    Class<?> activityClass;

    public IntroEntry(int title, int description, Class<?> activityClass) {
        this.title = title;
        this.description = description;
        this.activityClass = activityClass;
    }
}
