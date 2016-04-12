
package com.github.paolorotolo.appintro;

import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

abstract class AppIntroBaseFragment extends Fragment implements ISlideSelectionListener, ISlideBackgroundColorHolder {
    private static final String TAG = "AppIntroBaseFragment";

    protected static final String ARG_TITLE = "title";
    protected static final String ARG_DESC = "desc";
    protected static final String ARG_DRAWABLE = "drawable";
    protected static final String ARG_BG_COLOR = "bg_color";
    protected static final String ARG_TITLE_COLOR = "title_color";
    protected static final String ARG_DESC_COLOR = "desc_color";


    private int drawable, bgColor, titleColor, descColor, layoutId;
    private String title, description;

    private LinearLayout mainLayout;

    public AppIntroBaseFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);

        if (getArguments() != null && getArguments().size() != 0) {
            drawable = getArguments().getInt(ARG_DRAWABLE);
            title = getArguments().getString(ARG_TITLE);
            description = getArguments().getString(ARG_DESC);
            bgColor = getArguments().getInt(ARG_BG_COLOR);
            titleColor = getArguments().containsKey(ARG_TITLE_COLOR) ? getArguments().getInt(ARG_TITLE_COLOR) : 0;
            descColor = getArguments().containsKey(ARG_DESC_COLOR) ? getArguments().getInt(ARG_DESC_COLOR) : 0;
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if(savedInstanceState != null) {
            drawable = savedInstanceState.getInt(ARG_DRAWABLE);
            title = savedInstanceState.getString(ARG_TITLE);
            description = savedInstanceState.getString(ARG_DESC);

            bgColor = savedInstanceState.getInt(ARG_BG_COLOR);
            titleColor = savedInstanceState.getInt(ARG_TITLE_COLOR);
            descColor = savedInstanceState.getInt(ARG_DESC_COLOR);
        }
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(getLayoutId(), container, false);
        TextView t = (TextView) v.findViewById(R.id.title);
        TextView d = (TextView) v.findViewById(R.id.description);
        ImageView i = (ImageView) v.findViewById(R.id.image);
        mainLayout = (LinearLayout) v.findViewById(R.id.main);


        t.setText(title);
        if (titleColor != 0) {
            t.setTextColor(titleColor);
        }

        d.setText(description);
        if (descColor != 0) {
            d.setTextColor(descColor);
        }

        i.setImageDrawable(ContextCompat.getDrawable(getActivity(), drawable));
        mainLayout.setBackgroundColor(bgColor);

        return v;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(ARG_DRAWABLE, drawable);

        outState.putString(ARG_TITLE, title);
        outState.putString(ARG_DESC, description);

        outState.putInt(ARG_BG_COLOR, bgColor);
        outState.putInt(ARG_TITLE_COLOR, titleColor);
        outState.putInt(ARG_DESC_COLOR, descColor);
    }

    @Override
    public void onSlideDeselected() {
        Log.d(TAG, String.format("Slide %s has been deselected.", title));
    }

    @Override
    public void onSlideSelected() {
        Log.d(TAG, String.format("Slide %s has been selected.", title));
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
