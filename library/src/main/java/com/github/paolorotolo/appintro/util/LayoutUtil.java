package com.github.paolorotolo.appintro.util;

import android.content.res.Resources;
import android.os.Build;
import android.view.View;

/**
 * Created by tatianasolonets on 3/19/17.
 */

public class LayoutUtil {

    public static boolean isRtl(Resources resources) {
        if ( Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            return resources.getConfiguration().getLayoutDirection() == View.LAYOUT_DIRECTION_RTL;
        }
        return false;
    }
}
