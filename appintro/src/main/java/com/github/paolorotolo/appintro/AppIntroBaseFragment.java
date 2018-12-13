package com.github.paolorotolo.appintro;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.paolorotolo.appintro.internal.LogHelper;
import com.github.paolorotolo.appintro.internal.TypefaceContainer;

import androidx.annotation.ColorInt;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public abstract class AppIntroBaseFragment extends Fragment implements ISlideSelectionListener,
        ISlideBackgroundColorHolder {
    protected static final String ARG_TITLE = "title";
    protected static final String ARG_TITLE_TYPEFACE = "title_typeface";
    protected static final String ARG_TITLE_TYPEFACE_RES = "title_typeface_res";
    protected static final String ARG_DESC = "desc";
    protected static final String ARG_DESC_TYPEFACE = "desc_typeface";
    protected static final String ARG_DESC_TYPEFACE_RES = "desc_typeface_res";
    protected static final String ARG_DRAWABLE = "drawable";
    protected static final String ARG_BG_COLOR = "bg_color";
    protected static final String ARG_TITLE_COLOR = "title_color";
    protected static final String ARG_DESC_COLOR = "desc_color";

    private static final String TAG = LogHelper.makeLogTag(AppIntroBaseFragment.class);

    private int drawable, bgColor, titleColor, descColor, layoutId;
    private String title, description;
    private TypefaceContainer titleTypeface, descTypeface;

    private LinearLayout mainLayout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

        if (getArguments() != null && getArguments().size() != 0) {
            String argsTitleTypeface = getArguments().getString(ARG_TITLE_TYPEFACE);
            String argsDescTypeface = getArguments().getString(ARG_DESC_TYPEFACE);

            int argsTitleTypefaceRes = getArguments().getInt(ARG_TITLE_TYPEFACE_RES);
            int argsDescTypefaceRes = getArguments().getInt(ARG_DESC_TYPEFACE_RES);

            drawable = getArguments().getInt(ARG_DRAWABLE);

            title = getArguments().getString(ARG_TITLE);
            description = getArguments().getString(ARG_DESC);
            titleTypeface = new TypefaceContainer(argsTitleTypeface, argsTitleTypefaceRes);
            descTypeface = new TypefaceContainer(argsDescTypeface, argsDescTypefaceRes);

            bgColor = getArguments().getInt(ARG_BG_COLOR);
            titleColor = getArguments().containsKey(ARG_TITLE_COLOR) ?
                    getArguments().getInt(ARG_TITLE_COLOR) : 0;
            descColor = getArguments().containsKey(ARG_DESC_COLOR) ?
                    getArguments().getInt(ARG_DESC_COLOR) : 0;
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (savedInstanceState != null) {
            drawable = savedInstanceState.getInt(ARG_DRAWABLE);
            title = savedInstanceState.getString(ARG_TITLE);
            description = savedInstanceState.getString(ARG_DESC);

            titleTypeface = new TypefaceContainer(
                    savedInstanceState.getString(ARG_TITLE_TYPEFACE),
                    savedInstanceState.getInt(ARG_TITLE_TYPEFACE_RES, 0));
            descTypeface = new TypefaceContainer(
                    savedInstanceState.getString(ARG_DESC_TYPEFACE),
                    savedInstanceState.getInt(ARG_DESC_TYPEFACE_RES, 0));

            bgColor = savedInstanceState.getInt(ARG_BG_COLOR);
            titleColor = savedInstanceState.getInt(ARG_TITLE_COLOR);
            descColor = savedInstanceState.getInt(ARG_DESC_COLOR);
        }
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        TextView titleText = view.findViewById(R.id.title);
        TextView descriptionText = view.findViewById(R.id.description);
        ImageView slideImage = view.findViewById(R.id.image);
        mainLayout = view.findViewById(R.id.main);

        titleText.setText(title);
        if (titleColor != 0) {
            titleText.setTextColor(titleColor);
        }
        titleTypeface.applyTo(titleText);
        titleTypeface.applyTo(descriptionText);
        descriptionText.setText(description);
        if (descColor != 0) {
            descriptionText.setTextColor(descColor);
        }
        slideImage.setImageResource(drawable);
        mainLayout.setBackgroundColor(bgColor);

        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt(ARG_DRAWABLE, drawable);
        outState.putString(ARG_TITLE, title);
        outState.putString(ARG_DESC, description);
        outState.putInt(ARG_BG_COLOR, bgColor);
        outState.putInt(ARG_TITLE_COLOR, titleColor);
        outState.putInt(ARG_DESC_COLOR, descColor);
        saveTypefacesInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    private void saveTypefacesInstanceState(Bundle outState) {
        if (titleTypeface != null){
            outState.putString(ARG_TITLE_TYPEFACE, titleTypeface.getTypeFaceUrl());
            outState.putInt(ARG_TITLE_TYPEFACE_RES, titleTypeface.getTypeFaceResource());
        }
        if (descTypeface != null){
            outState.putString(ARG_DESC_TYPEFACE, descTypeface.getTypeFaceUrl());
            outState.putInt(ARG_DESC_TYPEFACE_RES, descTypeface.getTypeFaceResource());
        }
    }

    @Override
    public void onSlideDeselected() {
        LogHelper.d(TAG, String.format("Slide %s has been deselected.", title));
    }

    @Override
    public void onSlideSelected() {
        LogHelper.d(TAG, String.format("Slide %s has been selected.", title));
    }

    @Override
    public int getDefaultBackgroundColor() {
        return bgColor;
    }

    @Override
    public void setBackgroundColor(@ColorInt int backgroundColor) {
        mainLayout.setBackgroundColor(backgroundColor);
    }

    @LayoutRes
    protected abstract int getLayoutId();
}
