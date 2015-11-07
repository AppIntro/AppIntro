package com.github.paolorotolo.appintroexample;

import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.github.paolorotolo.appintroexample.util.ViewPagerIdlingResource;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.action.ViewActions.swipeRight;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.Visibility;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.github.paolorotolo.appintroexample.util.OrientationChangeAction.orientationLandscape;
import static com.github.paolorotolo.appintroexample.util.OrientationChangeAction.orientationPortrait;
import static org.hamcrest.CoreMatchers.allOf;

@RunWith(AndroidJUnit4.class)
public class SwipeLockTest {

    private ViewPagerIdlingResource viewPagerIdlingResource;
    private int viewPagerResId;
    private int btnSkipResId;
    private int btnNextResId;
    private int btnDoneResId;

    @Rule
    public ActivityTestRule<DisableSwipeIntro1> mActivityRule = new ActivityTestRule(DisableSwipeIntro1.class);

    @Before
    public void registerIntentServiceIdlingResource() {
        viewPagerResId = R.id.view_pager;
        btnSkipResId = R.id.skip;
        btnNextResId = R.id.next;
        btnDoneResId = R.id.done;

        View testRootView = mActivityRule.getActivity().findViewById(android.R.id.content);
        ViewPager testViewPager = (ViewPager) testRootView.findViewById(viewPagerResId);

        viewPagerIdlingResource = new ViewPagerIdlingResource(testViewPager, "ViewPager");
        Espresso.registerIdlingResources(viewPagerIdlingResource);
    }

    @After
    public void unregisterIntentServiceIdlingResource() {
        if (viewPagerIdlingResource != null) {
            Espresso.unregisterIdlingResources(viewPagerIdlingResource);
        }
    }

    @Test
    public void skipButtonHiding() {
        // hide button
        onView(allOf(withId(btnSkipResId), isDisplayed())).check(matches(withEffectiveVisibility(Visibility.VISIBLE)));
        onView(allOf(withId(R.id.button_disable_skip), isDisplayed())).perform(click());
        checkButtonVisibility(btnSkipResId, Visibility.INVISIBLE);

        checkButtonVisibilityOnPageSwipe(viewPagerResId, btnSkipResId, Visibility.INVISIBLE);
        checkButtonVisibilityOnRotation(btnSkipResId, Visibility.INVISIBLE);

        // show button
        onView(allOf(withId(R.id.button_disable_skip), isDisplayed())).perform(click());
        checkButtonVisibilityOnPageSwipe(viewPagerResId, btnSkipResId, Visibility.VISIBLE);
    }

    @Test
    public void nextButtonHiding() {
        // hide button
        onView(allOf(withId(btnNextResId), isDisplayed())).check(matches(withEffectiveVisibility(Visibility.VISIBLE)));
        onView(allOf(withId(R.id.button_disable_progress), isDisplayed())).perform(click());
        checkButtonVisibility(btnNextResId, Visibility.INVISIBLE);

        checkButtonVisibilityOnPageSwipe(viewPagerResId, btnNextResId, Visibility.INVISIBLE);
        checkButtonVisibilityOnRotation(btnNextResId, Visibility.INVISIBLE);

        // check that prior progress button visibility state is maintained when toggling swipe locking
        onView(allOf(withId(R.id.button_disable_swipe), isDisplayed())).perform(click());
        onView(allOf(withId(R.id.button_disable_swipe), isDisplayed())).perform(click());
        checkButtonVisibility(btnNextResId, Visibility.INVISIBLE);
        onView(allOf(withId(R.id.button_disable_next_swipe), isDisplayed())).perform(click());
        onView(allOf(withId(R.id.button_disable_next_swipe), isDisplayed())).perform(click());
        checkButtonVisibility(btnNextResId, Visibility.INVISIBLE);

        // show button
        onView(allOf(withId(R.id.button_disable_progress), isDisplayed())).perform(click());
        checkButtonVisibilityOnPageSwipe(viewPagerResId, btnNextResId, Visibility.VISIBLE);
    }

    @Test
    public void doneButtonHiding() {
        onView(withId(viewPagerResId)).perform(swipeLeft());
        onView(withId(viewPagerResId)).perform(swipeLeft());

        // hide button
        onView(allOf(withId(btnDoneResId), isDisplayed())).check(matches(withEffectiveVisibility(Visibility.VISIBLE)));
        onView(allOf(withId(R.id.button_disable_progress), isDisplayed())).perform(click());
        checkButtonVisibility(btnDoneResId, Visibility.INVISIBLE);

        checkButtonVisibilityOnPageSwipe(viewPagerResId, btnDoneResId, Visibility.INVISIBLE);
        checkButtonVisibilityOnRotation(btnDoneResId, Visibility.INVISIBLE);

        // show button
        onView(allOf(withId(R.id.button_disable_progress), isDisplayed())).perform(click());
        onView(allOf(withId(btnDoneResId), isDisplayed())).check(matches(withEffectiveVisibility(Visibility.VISIBLE)));
    }

    @Test
    public void swipeLock() {
        onView(withId(viewPagerResId)).perform(swipeLeft());

        // lock, test button hidden
        onView(allOf(withId(btnNextResId), isDisplayed())).check(matches(withEffectiveVisibility(Visibility.VISIBLE)));
        onView(allOf(withId(R.id.button_disable_swipe), isDisplayed())).perform(click());
        oneShotSwipeLock();

        onView(withId(viewPagerResId)).perform(swipeRight());
        onView(allOf(withId(R.id.button_disable_swipe), isDisplayed())).perform(click());
        onView(isRoot()).perform(orientationLandscape());
        oneShotSwipeLock();
    }

    private void oneShotSwipeLock() {
        checkButtonVisibility(btnNextResId, Visibility.INVISIBLE);

        // test swiping is locked and buttons are hidden
        checkButtonVisibilityOnPageSwipeLeft(viewPagerResId, btnNextResId, Visibility.INVISIBLE);
        onView(withText("Slide 2 title")).check(matches(isDisplayed()));
        checkButtonVisibilityOnPageSwipeRight(viewPagerResId, btnNextResId, Visibility.INVISIBLE);
        onView(withText("Slide 2 title")).check(matches(isDisplayed()));
        checkButtonVisibility(btnNextResId, Visibility.INVISIBLE);

        // test swiping is unlocked and buttons are shown
        onView(allOf(withId(R.id.button_disable_swipe), isDisplayed())).perform(click());
        checkButtonVisibility(btnNextResId, Visibility.VISIBLE);
        checkButtonVisibilityOnPageSwipeRight(viewPagerResId, btnNextResId, Visibility.VISIBLE);
        onView(withText("Slide 1 title")).check(matches(isDisplayed()));
        checkButtonVisibilityOnPageSwipeLeft(viewPagerResId, btnNextResId, Visibility.VISIBLE);
        checkButtonVisibilityOnPageSwipeLeft(viewPagerResId, btnDoneResId, Visibility.VISIBLE);
        onView(withText("Slide 3 title")).check(matches(isDisplayed()));
    }

    @Test
    public void nextPageSwipeLock() {
        onView(withId(viewPagerResId)).perform(swipeLeft());

        // lock, test button hidden
        onView(allOf(withId(btnNextResId), isDisplayed())).check(matches(withEffectiveVisibility(Visibility.VISIBLE)));
        onView(allOf(withId(R.id.button_disable_next_swipe), isDisplayed())).perform(click());
        oneShotNextPageSwipeLock();

        onView(allOf(withId(R.id.button_disable_next_swipe), isDisplayed())).perform(click());
        onView(isRoot()).perform(orientationLandscape());
        oneShotNextPageSwipeLock();
    }

    private void oneShotNextPageSwipeLock() {
        checkButtonVisibility(btnNextResId, Visibility.INVISIBLE);

        // test swiping left is locked and buttons are hidden, restored on swipe right
        checkButtonVisibilityOnPageSwipeLeft(viewPagerResId, btnNextResId, Visibility.INVISIBLE);
        onView(withText("Slide 2 title")).check(matches(isDisplayed()));
        checkButtonVisibilityOnPageSwipeRight(viewPagerResId, btnNextResId, Visibility.VISIBLE);
        checkButtonVisibilityOnPageSwipeLeft(viewPagerResId, btnNextResId, Visibility.VISIBLE);
        onView(withText("Slide 2 title")).check(matches(isDisplayed()));
    }

    private void checkButtonVisibilityOnPageSwipe(int viewPagerResId, int btnResId, Visibility btnVisibility) {
        // check visibility state maintained between pages, assumes > 1 pages
        if (mActivityRule.getActivity().getPager().getCurrentItem() == mActivityRule.getActivity().getPager().getChildCount()) {
            checkButtonVisibilityOnPageSwipeRight(viewPagerResId, btnResId, btnVisibility);
            checkButtonVisibilityOnPageSwipeLeft(viewPagerResId, btnResId, btnVisibility);
        } else {
            checkButtonVisibilityOnPageSwipeLeft(viewPagerResId, btnResId, btnVisibility);
            checkButtonVisibilityOnPageSwipeRight(viewPagerResId, btnResId, btnVisibility);
        }
    }

    private void checkButtonVisibilityOnPageSwipeLeft(int viewPagerResId, int btnResId, Visibility btnVisibility) {
        onView(withId(viewPagerResId)).perform(swipeLeft());
        checkButtonVisibility(btnResId, btnVisibility);
    }

    private void checkButtonVisibilityOnPageSwipeRight(int viewPagerResId, int btnResId, Visibility btnVisibility) {
        onView(withId(viewPagerResId)).perform(swipeRight());
        checkButtonVisibility(btnResId, btnVisibility);
    }

    private void checkButtonVisibilityOnRotation(int btnResId, Visibility btnVisibility) {
        checkButtonVisibility(btnResId, btnVisibility);

        // check visibility state maintained between rotation
        onView(isRoot()).perform(orientationLandscape());
        checkButtonVisibility(btnResId, btnVisibility);
        onView(isRoot()).perform(orientationPortrait());
        checkButtonVisibility(btnResId, btnVisibility);
    }

    private void checkButtonVisibility(int btnResId, Visibility btnVisibility) {
        onView(withId(btnResId)).check(matches(withEffectiveVisibility(btnVisibility)));
    }
}