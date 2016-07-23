package com.github.paolorotolo.appintro;

import android.content.Context;
import android.graphics.Typeface;

import java.util.Hashtable;

/**
 * Created by ameykshirsagar on 23/07/16.
 */
public class CustomFontCache {
    private static Hashtable<String, Typeface> fCache = new Hashtable<String, Typeface>();

    public static Typeface get(String tfn, Context ctx) {
        Typeface tf = fCache.get(tfn);
        if (tf == null) {
            //Log.i("font add","Adding new font -> "+tfn);
            try {
                tf = Typeface.createFromAsset(ctx.getAssets(), tfn);
                if (tf != null) {
                    fCache.put(tfn, tf);
                }

                return tf;
            } catch (Exception e) {
                return null;
            }

        } else {
            //Log.i("font exist","Getting cached font -> "+tfn);
            return tf;
        }

    }
}
