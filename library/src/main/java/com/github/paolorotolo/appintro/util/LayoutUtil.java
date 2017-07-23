package com.github.paolorotolo.appintro.util;

import android.content.res.Resources;
import android.os.Build;
import android.view.View;

public class LayoutUtil {

    public static boolean isRtl(Resources resources) {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1
                && resources.getConfiguration().getLayoutDirection() == View.LAYOUT_DIRECTION_RTL;
    }
}
