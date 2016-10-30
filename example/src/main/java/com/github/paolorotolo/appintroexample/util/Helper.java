package com.github.paolorotolo.appintroexample.util;

import android.annotation.SuppressLint;
import android.os.Build;
import android.text.Html;
import android.text.Spanned;

/**
 * Created by avluis on 10/04/2016.
 * Utility class.
 */

public final class Helper {
    /**
     * @param apiLevel minimum API level version that has to support the device
     * @return true when the caller API version is at least apiLevel
     */
    public static boolean isAtLeastAPI(int apiLevel) {
        return Build.VERSION.SDK_INT >= apiLevel;
    }

//    @SuppressLint("NewApi")
    public static Spanned fromHtml(String source) {
//        if (isAtLeastAPI(Build.VERSION_CODES.N)) {
//            return Html.fromHtml(source, Html.FROM_HTML_MODE_LEGACY, null, null);
//        } else {
            //noinspection deprecation
            return Html.fromHtml(source);
//        }
    }
}
