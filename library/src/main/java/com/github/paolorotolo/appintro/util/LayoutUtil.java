package com.github.paolorotolo.appintro.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Build;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;

import java.util.Locale;

import static android.content.pm.ApplicationInfo.FLAG_SUPPORTS_RTL;

public class LayoutUtil {

    public static boolean isRtl(@NonNull Context ctx) {
        try {
            ApplicationInfo info = ctx.getPackageManager().getApplicationInfo(ctx.getPackageName(), PackageManager.GET_META_DATA);

            return defaultIsRtlBehavior();
        } catch (PackageManager.NameNotFoundException e) {
            return defaultIsRtlBehavior();
        }

    }

    private static boolean defaultIsRtlBehavior() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1
                && TextUtils.getLayoutDirectionFromLocale(Locale.getDefault()) == View.LAYOUT_DIRECTION_RTL;
    }
}
