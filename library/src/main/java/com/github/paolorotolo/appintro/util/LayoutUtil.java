package com.github.paolorotolo.appintro.util;

import android.content.res.Resources;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;

import java.util.Locale;

public class LayoutUtil {

    @Deprecated
    public static boolean isRtl(Resources resources) {
        return isRtl();
    }

    public static boolean isRtl() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1
                && TextUtils.getLayoutDirectionFromLocale(Locale.getDefault()) == View.LAYOUT_DIRECTION_RTL;
    }
}
