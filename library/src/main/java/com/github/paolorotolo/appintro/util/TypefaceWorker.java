package com.github.paolorotolo.appintro.util;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;

import androidx.annotation.FontRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;

/**
 * Created by Javinator9889 (https://github.com/Javinator9889) on 13/10/2018 @ https://github.com/Javinator9889/AppIntro
 * Class for containing the typeface object that must be used and also work directly with it
 */
public class TypefaceWorker {
    private Object mTypefaceAttribute;
    private boolean mIsFontResource;

    /**
     * Default constructor
     */
    public TypefaceWorker() {
        mTypefaceAttribute = null;
        mIsFontResource = false;
    }

    /**
     * Direct constructor by giving the typeface
     *
     * @param typeface <p>a <b>String</b>, which is an URL to font found at Assets folder
     */
    public TypefaceWorker(String typeface) {
        setTypeface(typeface);
    }

    /**
     * Direct constructor by giving the typeface
     *
     * @param typeface <p>an <b>Integer</b> which is a @FontRes
     */
    public TypefaceWorker(@FontRes int typeface) {
        setTypeface(typeface);
    }

    /**
     * Once the class is created sets typeface attribute
     *
     * @param typeface <p>a <b>String</b>, which is an URL to font found at Assets folder
     */
    public void setTypeface(String typeface) {
        mTypefaceAttribute = typeface;
        mIsFontResource = false;
    }

    /**
     * Once the class is created sets typeface attribute
     *
     * @param typeface <p>an <b>Integer</b> which is a @FontRes
     */
    public void setTypeface(@FontRes int typeface) {
        mTypefaceAttribute = typeface;
        mIsFontResource = true;
    }

    /**
     * Whether if a typeface has been stored or not
     *
     * @return <p><b>boolean</b>:<b>true</b> if typeface stored, else <b>false</b></p>
     */
    public boolean isAnyTypefaceProvided() {
        return mTypefaceAttribute != null;
    }

    /**
     * Applies typeface to a given TextView object. If there is no typeface provided, exits
     *
     * @param textToApplyTypeface <p>the TextView where the <b>Typeface</b> will be applied - cannot be null</p>
     * @param context             <p>the <b>Context</b> where the <b>TextView</b> is located - cannot be null</p>
     * @throws NullPointerException <p>whether <b>TextView</b> or <b>Context</b> are null</p>
     */
    public void setTextTypeface(TextView textToApplyTypeface, Context context) {
        if (textToApplyTypeface == null || context == null)
            throw new NullPointerException(String
                    .format("Cannot apply setTypeface(TextView textToApplyTypeface, Context context) on a null %s",
                            (context == null ? "Context" : "TextView")));
        if (!isAnyTypefaceProvided())
            return;
        Typeface textTypeface;
        if (mIsFontResource)
            textTypeface = ResourcesCompat.getFont(context, (Integer) mTypefaceAttribute);
        else
            textTypeface = CustomFontCache.get((String) mTypefaceAttribute, context);
        if (textTypeface != null)
            textToApplyTypeface.setTypeface(textTypeface);
    }

    /**
     * Gets the stored typeface attribute
     *
     * @return <p>typeface attribute that can be a String or Integer</p>
     */
    public Object getTypeface() {
        return mTypefaceAttribute;
    }

    /**
     * Checks if there is a FontRes stored
     *
     * @return <p>true if is an Integer (FontRes), else false</p>
     */
    public boolean isFontResource() {
        return mIsFontResource;
    }
}
