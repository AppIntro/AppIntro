package com.github.paolorotolo.appintro;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public abstract class AppIntro extends FragmentActivity {
    private PagerAdapter mPagerAdapter;
    private ViewPager pager;
    private List<Fragment> fragments = new Vector<Fragment>();
    private List<ImageView> dots;
    private int slidesNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.intro_layout);

        final TextView skip = (TextView) findViewById(R.id.skip);
        final ImageView next = (ImageView) findViewById(R.id.next);
        final TextView done = (TextView) findViewById(R.id.done);

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSkipPressed();
            }
        });

        next.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                pager.setCurrentItem(pager.getCurrentItem()+1);
            }
        });

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDonePressed();
            }
        });
        mPagerAdapter = new PagerAdapter(super.getSupportFragmentManager(), fragments);
        pager = (ViewPager) findViewById(R.id.view_pager);
        pager.setAdapter(this.mPagerAdapter);
        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                selectDot(position);
                if (position == slidesNumber-1){
                    skip.setVisibility(View.INVISIBLE);
                    next.setVisibility(View.GONE);
                    done.setVisibility(View.VISIBLE);
                } else {
                    skip.setVisibility(View.VISIBLE);
                    done.setVisibility(View.GONE);
                    next.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        init(savedInstanceState);
        loadDots();
        selectDot(0);
    }

    private void loadDots() {
        LinearLayout dotLayout = (LinearLayout) findViewById(R.id.dotLayout);
        dots = new ArrayList<>();
        slidesNumber = fragments.size();

        for (int i = 0; i < slidesNumber; i++) {
            ImageView dot = new ImageView(this);
            dot.setImageDrawable(getResources().getDrawable(R.drawable.indicator_dot_grey));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            dotLayout.addView(dot, params);

            dots.add(dot);
        }
    }

    public void selectDot(int idx) {
        Resources res = getResources();
        for (int i = 0; i < fragments.size(); i++) {
            int drawableId = (i == idx) ? (R.drawable.indicator_dot_white) : (R.drawable.indicator_dot_grey);
            Drawable drawable = res.getDrawable(drawableId);
            dots.get(i).setImageDrawable(drawable);
        }
    }

    public void addSlide(Fragment fragment, Context context) {
        fragments.add(Fragment.instantiate(context, fragment.getClass().getName()));
        mPagerAdapter.notifyDataSetChanged();
    }

    public void setBarColor(final int color){
        LinearLayout bottomBar = (LinearLayout) findViewById(R.id.bottom);
        bottomBar.setBackgroundColor(color);
    }

    public void setSeparatorColor(final int color){
        TextView separator = (TextView) findViewById(R.id.bottom_separator);
        separator.setBackgroundColor(color);
    }

    public abstract void init(Bundle savedInstanceState);
    public abstract void onSkipPressed();
    public abstract void onDonePressed();

    public class PagerAdapter extends FragmentPagerAdapter {

        private List<Fragment> fragments;

        public PagerAdapter(FragmentManager fm, List<Fragment> fragments) {
            super(fm);
            this.fragments = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return this.fragments.get(position);
        }

        @Override
        public int getCount() {
            return this.fragments.size();
        }
    }
}