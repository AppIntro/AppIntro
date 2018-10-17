package com.github.paolorotolo.appintro.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;

import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

public class LayoutUtil {

    public static boolean isRtl(@NonNull Context ctx) {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1
                && ctx.getResources().getConfiguration().getLayoutDirection() == View.LAYOUT_DIRECTION_RTL;

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private static boolean defaultIsRtlBehavior() {
        return TextUtils.getLayoutDirectionFromLocale(Locale.getDefault()) == View.LAYOUT_DIRECTION_RTL;
    }
}
