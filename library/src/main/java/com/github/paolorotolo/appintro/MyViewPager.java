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

ArgbEvaluator evaluator = new ArgbEvaluator();

	public MyViewPager(Context context) {
		super(context);
		if (evaluator == null)
			evaluator = new ArgbEvaluator();
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
				// int color = adapter.getFragment(position + 1, i).getColor();
				if (fragment != null) {
					Log.d("MyViewPager", "Color " + adapter.getColor(position) + ", Color2 "
							+ adapter.getColor(position + 1) + ", p-i " + position + "-" + i);
					fragment.transformPage(child, i == 0 ? offset : 1 - offset, offsetPixels);
					if (child != null) {
						child.setBackgroundColor((int) evaluator.evaluate(offset, adapter.getColor(position),
								adapter.getColor(position + 1)));
					}
				}
			}
		}
	}

	public static abstract class MyFragment extends Fragment {
		static int color;

		// public abstract void transformFragment(View view, float offset);
		public abstract void transformPage(View view, float offset, int pixels);
		// public abstract void setColor(int color);
		// public abstract int getColor();

		public static void setColor(int color) {
			MyFragment.color = color;
		}

		public int getColor() {
			return color;
		}
	}

	// abstract class MyPageTransformenr implements ViewPager.PageTransformer {
	//
	// public abstract void transformPage(View view, float offset);
	//
	// }

}
