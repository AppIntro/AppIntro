package ${packageName};

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.graphics.Color;
import android.support.annotation.Nullable;
<#if layoutType == "type1">
import com.github.paolorotolo.appintro.${activityClassSuper1};
import com.github.paolorotolo.appintro.${appIntroFragment1};
</#if>
<#if layoutType == "type2">
import com.github.paolorotolo.appintro.${activityClassSuper2};
import com.github.paolorotolo.appintro.${appIntroFragment2};
</#if>

// Add in your root build.gradle at the end of repositories:
// maven { url 'https://jitpack.io' }
<#if layoutType == "type1">
class ${className} extends AppIntro {
</#if>
<#if layoutType == "type2">
public class ${className} extends AppIntro2 {
</#if>
    private String[] titles;
    private String[] descriptions;
    private int[] drawables = {
            R.drawable.ic_done_white
    };
    private int[] colors = {
        Color.parseColor("#3F51B5")
    };
    // private String[] permissions = {};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.titles = getResources().getStringArray(R.array.intro_title);
        this.descriptions = getResources().getStringArray(R.array.intro_description);

        <#if useDefaultSlide>
        for (int i = 0; i < this.titles.length; i++) {
            <#if layoutType == "type1">
            addSlide(AppIntroFragment.newInstance(this.titles[i], this.descriptions[i], this.drawables[0], this.colors[0]));
            </#if>
            <#if layoutType == "type2">
            addSlide(AppIntro2Fragment.newInstance(this.titles[i], this.descriptions[i], this.drawables[0], this.colors[0]));
            </#if>
        }
        <#else>
            // Add your slide
            addSlide()
        </#if>

        showStatusBar(false);
        <#if showSkipButton>
            showSkipButton(true);
        <#else>
            showSkipButton(false);
        </#if>
        <#if showDoneButton>

        <#else>
            setProgressButtonEnabled(true);
        </#if>

        // askForPermissions(permissions, 1);

    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        finish();
    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        finish();
    }

    @Override
    public void onSlideChanged(@Nullable Fragment oldFragment, @Nullable Fragment newFragment) {
        super.onSlideChanged(oldFragment, newFragment);
    }

}