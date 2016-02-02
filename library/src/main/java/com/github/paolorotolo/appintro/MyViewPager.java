package com.github.paolorotolo.appintro;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
/**
 * @author sumeet.kumar
 * */
public class MyViewPager extends ViewPager {

	public MyViewPager(Context context) {
		super(context);
	}

	public MyViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void onPageScrolled(int position, float offset, int offsetPixels) {
		super.onPageScrolled(position, offset, offsetPixels);
		final int childCount = getChildCount();
		for (int i = 0; i < childCount; i++) {
			final View child = getChildAt(i);

			final LayoutParams lp = (LayoutParams) child.getLayoutParams();
			if (lp.isDecor)
				continue;

			MyPagerAdapter adapter = (MyPagerAdapter) getAdapter();
			if (adapter != null) {
				final MyFragment fragment = adapter.getFragment(position, i);
				if (fragment != null)
					fragment.transformPage(child, i == 0 ? offset : 1 - offset);
				else fragment.transformPage(null, offsetPixels);
			}
		}
	}

	public static abstract class MyFragment extends Fragment {

		// public abstract void transformFragment(View view, float offset);
		public abstract void transformPage(View view, float offset);
	}

	// abstract class MyPageTransformenr implements ViewPager.PageTransformer {
	//
	// public abstract void transformPage(View view, float offset);
	//
	// }

}
