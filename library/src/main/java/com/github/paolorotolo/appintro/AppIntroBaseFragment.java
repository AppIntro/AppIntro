package com.github.paolorotolo.appintro;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.paolorotolo.appintro.util.LogHelper;
import com.github.paolorotolo.appintro.util.TypefaceWorker;

import androidx.annotation.ColorInt;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public abstract class AppIntroBaseFragment extends Fragment implements ISlideSelectionListener,
        ISlideBackgroundColorHolder {
    protected static final String ARG_TITLE = "title";
    protected static final String ARG_TITLE_TYPEFACE = "title_typeface";
    protected static final String ARG_DESC = "desc";
    protected static final String ARG_DESC_TYPEFACE = "desc_typeface";
    protected static final String ARG_DRAWABLE = "drawable";
    protected static final String ARG_BG_COLOR = "bg_color";
    protected static final String ARG_TITLE_COLOR = "title_color";
    protected static final String ARG_DESC_COLOR = "desc_color";

    private static final String TAG = LogHelper.makeLogTag(AppIntroBaseFragment.class);

    private int drawable, bgColor, titleColor, descColor, layoutId;
    private String title, description;
    private TypefaceWorker titleTypeface, descTypeface;

    private LinearLayout mainLayout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);

        if (getArguments() != null && getArguments().size() != 0) {
            drawable = getArguments().getInt(ARG_DRAWABLE);
            title = getArguments().getString(ARG_TITLE);
            titleTypeface = getArguments().containsKey(ARG_TITLE_TYPEFACE) ?
                    new TypefaceWorker(getArguments().get(ARG_TITLE_TYPEFACE)) : new TypefaceWorker();
            description = getArguments().getString(ARG_DESC);
            descTypeface = getArguments().containsKey(ARG_DESC_TYPEFACE) ?
                    new TypefaceWorker(getArguments().get(ARG_DESC_TYPEFACE)) : new TypefaceWorker();
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
            titleTypeface = savedInstanceState.get(ARG_TITLE_TYPEFACE) != null ?
                    new TypefaceWorker(savedInstanceState.get(ARG_TITLE_TYPEFACE)) : new TypefaceWorker();
            description = savedInstanceState.getString(ARG_DESC);
            descTypeface = savedInstanceState.get(ARG_DESC_TYPEFACE) != null ?
                    new TypefaceWorker(savedInstanceState.get(ARG_DESC_TYPEFACE)) : new TypefaceWorker();
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
        titleTypeface.setTextTypeface(titleText, getContext());
        descTypeface.setTextTypeface(descriptionText, getContext());
        descriptionText.setText(description);
        if (descColor != 0) {
            descriptionText.setTextColor(descColor);
        }
        slideImage.setImageResource(drawable);
        mainLayout.setBackgroundColor(bgColor);

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
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
        if (titleTypeface.isAnyTypefaceProvided()) {
            if (titleTypeface.isFontResource())
                outState.putInt(ARG_TITLE_TYPEFACE, (int) titleTypeface.getTypeface());
            else
                outState.putString(ARG_TITLE_TYPEFACE, (String) titleTypeface.getTypeface());
        }
        if (descTypeface.isAnyTypefaceProvided()) {
            if (descTypeface.isFontResource())
                outState.putInt(ARG_DESC_TYPEFACE, (int) descTypeface.getTypeface());
            else
                outState.putString(ARG_DESC_TYPEFACE, (String) descTypeface.getTypeface());
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
