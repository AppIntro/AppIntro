package com.github.paolorotolo.appintro.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import android.text.TextUtils;
import android.view.View;

import java.util.Locale;

public class LayoutUtil {

    public static boolean isRtl(@NonNull Context ctx) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            try {
                ApplicationInfo info = ctx.getPackageManager().getApplicationInfo(ctx.getPackageName(), PackageManager.GET_META_DATA);
                return (info.flags &= ApplicationInfo.FLAG_SUPPORTS_RTL) != 0;
            } catch (PackageManager.NameNotFoundException e) {
                return defaultIsRtlBehavior();
            }
        }

        return false;

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private static boolean defaultIsRtlBehavior() {
        return TextUtils.getLayoutDirectionFromLocale(Locale.getDefault()) == View.LAYOUT_DIRECTION_RTL;
    }
}
