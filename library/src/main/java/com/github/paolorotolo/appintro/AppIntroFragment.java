package com.github.paolorotolo.appintro;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
    private static final String ARG_COLOUR = "colour";

    public static AppIntroFragment newInstance(String title, String description, int imageDrawable, int colour) {
        AppIntroFragment sampleSlide = new AppIntroFragment();

        Bundle args = new Bundle();
        args.putString(ARG_TITLE, title);
        args.putString(ARG_DESC, description);
        args.putInt(ARG_DRAWABLE, imageDrawable);
        args.putInt(ARG_COLOUR, colour);
        sampleSlide.setArguments(args);

        return sampleSlide;
    }

    private int drawable, colour;
    private String title, description;

    public AppIntroFragment() {}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getArguments() != null && getArguments().size() != 0){
            drawable = getArguments().getInt(ARG_DRAWABLE);
            title = getArguments().getString(ARG_TITLE);
            description = getArguments().getString(ARG_DESC);
            colour = getArguments().getInt(ARG_COLOUR);
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
        d.setText(description);
        i.setImageDrawable(ResourceUtils.getDrawable(getActivity(), drawable));
        m.setBackgroundColor(colour);
        return v;
    }

}