package com.github.paolorotolo.appintro;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;

import java.util.Hashtable;

/**
 * Created by ameykshirsagar on 23/07/16.
 */
public class CustomFontCache {
    private static Hashtable<String, Typeface> fCache = new Hashtable<String, Typeface>();
    private static final String TAG = "AppIntro2";
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
                Log.w(TAG,e.toString());
                return null;
            }

        } else {
            return tf;
        }

    }
}
