package com.github.paolorotolo.appintro;

import java.util.ArrayList;

import com.github.paolorotolo.appintro.MyViewPager.MyFragment;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
/**
 * @author sumeet.kumar
 * */
public abstract class MyPagerAdapter extends PagerAdapter {

private final FragmentManager fManager;
	private FragmentTransaction fTransaction = null;

	private ArrayList<Fragment.SavedState> State = new ArrayList<Fragment.SavedState>();
	private ArrayList<MyFragment> fragmentList = new ArrayList<MyFragment>();
	private ArrayList<Integer> colors = new ArrayList<Integer>();

	private Fragment currentFragment = null;

	public MyPagerAdapter(FragmentManager fm) {
		fManager = fm;
	}

	public MyPagerAdapter(FragmentManager fm, ArrayList<Integer> colors) {
		fManager = fm;
		this.colors = colors;
	}

	public abstract MyFragment getItem(int position);

	MyFragment getFragment(int position, int childPosition) {
		if (fragmentList.size() > position + childPosition) {
			return fragmentList.get(position + childPosition);
		}
		return null;
	}

	int getColor(int position) {
		if (colors.size() > position) {
			return colors.get(position);
		}
		return 0;
	}

	@Override
	public void startUpdate(ViewGroup container) {

	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		if (fragmentList.size() > position) {
			Fragment f = fragmentList.get(position);
			if (f != null) {
				return f;
			}
		}

		if (fTransaction == null) {
			fTransaction = fManager.beginTransaction();
		}

		MyFragment fragment = getItem(position);
		Log.v("MyPagerAdapter", "Adding item #" + position + ": f=" + fragment);
		if (State.size() > position) {
			Fragment.SavedState fss = State.get(position);
			if (fss != null) {
				fragment.setInitialSavedState(fss);
			}
		}
		while (fragmentList.size() <= position) {
			fragmentList.add(null);
		}
		fragment.setMenuVisibility(false);
		fragment.setUserVisibleHint(false);
		fragmentList.set(position, fragment);
		fTransaction.add(container.getId(), fragment);

		return fragment;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		Fragment fragment = (Fragment) object;

		if (fTransaction == null) {
			fTransaction = fManager.beginTransaction();
		}
		Log.v("MyPagerAdapter", "Removing item #" + position + ": f=" + object + " v=" + ((Fragment) object).getView());
		while (State.size() <= position) {
			State.add(null);
		}
		State.set(position, fManager.saveFragmentInstanceState(fragment));
		fragmentList.set(position, null);

		fTransaction.remove(fragment);
	}

	@Override
	public void setPrimaryItem(ViewGroup container, int position, Object object) {
		Fragment fragment = (Fragment) object;
		if (fragment != currentFragment) {
			if (currentFragment != null) {
				currentFragment.setMenuVisibility(false);
				currentFragment.setUserVisibleHint(false);
			}
			if (fragment != null) {
				fragment.setMenuVisibility(true);
				fragment.setUserVisibleHint(true);
			}
			currentFragment = fragment;
		}
	}

	@Override
	public void finishUpdate(ViewGroup container) {
		if (fTransaction != null) {
			fTransaction.commitAllowingStateLoss();
			fTransaction = null;
			fManager.executePendingTransactions();
		}
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		return ((Fragment) object).getView() == view;
	}

	@Override
	public Parcelable saveState() {
		Bundle state = null;
		if (State.size() > 0) {
			state = new Bundle();
			Fragment.SavedState[] fss = new Fragment.SavedState[State.size()];
			State.toArray(fss);
			state.putParcelableArray("states", fss);
		}
		for (int i = 0; i < fragmentList.size(); i++) {
			Fragment f = fragmentList.get(i);
			if (f != null) {
				if (state == null) {
					state = new Bundle();
				}
				String key = "f" + i;
				fManager.putFragment(state, key, f);
			}
		}
		return state;
	}

	@Override
	public void restoreState(Parcelable state, ClassLoader loader) {
		if (state != null) {
			Bundle bundle = (Bundle) state;
			bundle.setClassLoader(loader);
			Parcelable[] fss = bundle.getParcelableArray("states");
			State.clear();
			fragmentList.clear();
			if (fss != null) {
				for (int i = 0; i < fss.length; i++) {
					State.add((Fragment.SavedState) fss[i]);
				}
			}
			Iterable<String> keys = bundle.keySet();
			for (String key : keys) {
				if (key.startsWith("f")) {
					int index = Integer.parseInt(key.substring(1));
					MyFragment f = (MyFragment) fManager.getFragment(bundle, key);
					if (f != null) {
						while (fragmentList.size() <= index) {
							fragmentList.add(null);
						}
						f.setMenuVisibility(false);
						fragmentList.set(index, f);
					} else {
						Log.w("Adapter", "Bad fragment at key " + key);
					}
				}
			}
		}
	}
}
