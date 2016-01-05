package com.github.paolorotolo.appintro;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AppIntroFragment extends Fragment {

    private static final String ARG_TITLE = "title";
    private static final String ARG_DESC = "desc";
    private static final String ARG_DRAWABLE = "drawable";
    private static final String ARG_BG_COLOR = "bg_color";
    private static final String ARG_TITLE_COLOR = "title_color";
    private static final String ARG_DESC_COLOR = "desc_color";

    public static AppIntroFragment newInstance(CharSequence title, CharSequence description, int imageDrawable, int bgColor) {
        return newInstance(title, description, imageDrawable, bgColor, 0, 0);
    }


    public static AppIntroFragment newInstance(CharSequence title, CharSequence description, int imageDrawable, int bgColor, int titleColor, int descColor) {
        AppIntroFragment sampleSlide = new AppIntroFragment();

        Bundle args = new Bundle();
        args.putCharSequence(ARG_TITLE, title);
        args.putCharSequence(ARG_DESC, description);
        args.putInt(ARG_DRAWABLE, imageDrawable);
        args.putInt(ARG_BG_COLOR, bgColor);
        args.putInt(ARG_TITLE_COLOR, titleColor);
        args.putInt(ARG_DESC_COLOR, descColor);
        sampleSlide.setArguments(args);

        return sampleSlide;
    }

    private int drawable, bgColor, titleColor, descColor;
    private CharSequence title, description;

    public AppIntroFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null && getArguments().size() != 0) {
            drawable = getArguments().getInt(ARG_DRAWABLE);
            title = getArguments().getCharSequence(ARG_TITLE);
            description = getArguments().getCharSequence(ARG_DESC);
            bgColor = getArguments().getInt(ARG_BG_COLOR);
            titleColor = getArguments().containsKey(ARG_TITLE_COLOR) ? getArguments().getInt(ARG_TITLE_COLOR) : 0;
            descColor = getArguments().containsKey(ARG_DESC_COLOR) ? getArguments().getInt(ARG_DESC_COLOR) : 0;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_intro, container, false);
        TextView t = (TextView) v.findViewById(R.id.title);
        TextView d = (TextView) v.findViewById(R.id.description);
        ImageView i = (ImageView) v.findViewById(R.id.image);
        LinearLayout m = (LinearLayout) v.findViewById(R.id.main);


        t.setText(title);
        if (titleColor != 0) {
            t.setTextColor(titleColor);
        }

        d.setText(description);
        if (descColor != 0) {
            d.setTextColor(descColor);
        }
        
        i.setImageDrawable(ContextCompat.getDrawable(getActivity(), drawable));
        m.setBackgroundColor(bgColor);

        return v;
    }

}
