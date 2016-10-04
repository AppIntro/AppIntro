package com.github.paolorotolo.appintro.util;

import android.content.Context;
import android.graphics.Typeface;

import java.util.Hashtable;

/**
 * Created by ameykshirsagar on 23/07/16.
 * Custom Font Cache Implementation
 * Prevent(s) memory leaks due to Typeface objects
 */
public class CustomFontCache {
    private static final String TAG = LogHelper.makeLogTag(CustomFontCache.class);

    private static final Hashtable<String, Typeface> fCache = new Hashtable<>();

    public static Typeface get(String tfn, Context ctx) {
        Typeface tf = fCache.get(tfn);
        if (tf == null) {
            try {
                tf = Typeface.createFromAsset(ctx.getAssets(), tfn);
                if (tf != null) {
                    fCache.put(tfn, tf);
                }

                return tf;
            } catch (Exception e) {
                if (("").equals(tfn)) {
                    LogHelper.w(TAG, e, "Empty path");
                } else {
                    LogHelper.w(TAG, e, tfn);
                }

                return null;
            }
        } else {
            return tf;
        }
    }
}
