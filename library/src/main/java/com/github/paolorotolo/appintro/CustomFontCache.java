package com.github.paolorotolo.appintro;

import android.content.Context;
import android.graphics.Typeface;
<<<<<<< HEAD
import android.util.Log;
=======

import com.github.paolorotolo.appintro.util.LogHelper;
>>>>>>> PaoloRotolo/master

import java.util.Hashtable;

/**
 * Created by ameykshirsagar on 23/07/16.
<<<<<<< HEAD
 */
public class CustomFontCache {
    private static Hashtable<String, Typeface> fCache = new Hashtable<String, Typeface>();
    private static final String TAG = "AppIntro2";
=======
 * Custom Font Cache Implementation
 * Prevent(s) memory leaks due to Typeface objects
 */
public class CustomFontCache {
    private static final String TAG = LogHelper.makeLogTag(CustomFontCache.class);

    private static final Hashtable<String, Typeface> fCache = new Hashtable<>();

>>>>>>> PaoloRotolo/master
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
<<<<<<< HEAD
                Log.w(TAG,e.toString());
                return null;
            }

=======
                LogHelper.w(TAG, e.toString());
                return null;
            }
>>>>>>> PaoloRotolo/master
        } else {
            return tf;
        }

    }
}
