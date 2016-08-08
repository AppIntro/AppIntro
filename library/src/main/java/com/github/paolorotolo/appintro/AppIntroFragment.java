package com.github.paolorotolo.appintro;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public final class AppIntroFragment extends AppIntroBaseFragment {
    public static AppIntroFragment newInstance(CharSequence title, CharSequence description, int imageDrawable, int bgColor) {
        return newInstance(title,"" ,description,"", imageDrawable, bgColor, 0, 0);
    }

    public static AppIntroFragment newInstance(CharSequence title, CharSequence description, int imageDrawable, int bgColor, int titleColor, int descColor) {
        AppIntroFragment slide = new AppIntroFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TITLE, title.toString());
        args.putString(ARG_TITLE_TYPEFACE,"");
        args.putString(ARG_DESC, description.toString());
        args.putString(ARG_DESC_TYPEFACE,"");
        args.putInt(ARG_DRAWABLE, imageDrawable);
        args.putInt(ARG_BG_COLOR, bgColor);
        args.putInt(ARG_TITLE_COLOR, titleColor);
        args.putInt(ARG_DESC_COLOR, descColor);
        slide.setArguments(args);
        return slide;
    }

    public static AppIntroFragment newInstance(CharSequence title, String titleTypeface, CharSequence description, String descTypeface, int imageDrawable, int bgColor) {
        return newInstance(title, titleTypeface ,description, descTypeface, imageDrawable, bgColor, 0, 0);
    }

    public static AppIntroFragment newInstance(CharSequence title, String titleTypeface, CharSequence description, String descTypeface, int imageDrawable, int bgColor, int titleColor, int descColor) {
        AppIntroFragment slide = new AppIntroFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TITLE, title.toString());
        args.putString(ARG_TITLE_TYPEFACE,titleTypeface);
        args.putString(ARG_DESC, description.toString());
        args.putString(ARG_DESC_TYPEFACE,descTypeface);
        args.putInt(ARG_DRAWABLE, imageDrawable);
        args.putInt(ARG_BG_COLOR, bgColor);
        args.putInt(ARG_TITLE_COLOR, titleColor);
        args.putInt(ARG_DESC_COLOR, descColor);
        slide.setArguments(args);
        return slide;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_intro;
    }
}
