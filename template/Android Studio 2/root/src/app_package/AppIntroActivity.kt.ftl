package ${packageName}

import android.os.Bundle
import android.support.v4.app.Fragment
import android.graphics.Color
<#if layoutType == "type1">
import com.github.paolorotolo.appintro.${activityClassSuper1}
import com.github.paolorotolo.appintro.${appIntroFragment1}
</#if>
<#if layoutType == "type2">
import com.github.paolorotolo.appintro.${activityClassSuper2}
import com.github.paolorotolo.appintro.${appIntroFragment2}
</#if>

// Add in your root build.gradle at the end of repositories:
// maven { url 'https://jitpack.io' }
<#if layoutType == "type1">
class ${className} : AppIntro() {
</#if>
<#if layoutType == "type2">
class ${className} : AppIntro2() {
</#if>
    private val titles by lazy { resources.getStringArray(R.array.intro_title) }
    private val descriptions by lazy { resources.getStringArray(R.array.intro_description) }
    private val drawables = arrayOf(R.drawable.abc_btn_radio_material)
    private val colors = arrayOf(Color.parseColor("#3F51B5"))
    // private val permissions = arrayOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        <#if useDefaultSlide>
        for (i in 0..titles.size - 1) {
            <#if layoutType == "type1">
            addSlide(AppIntroFragment.newInstance(titles[i], descriptions[i], drawables[0], colors[0]))
            </#if>
            <#if layoutType == "type2">
            addSlide(AppIntro2Fragment.newInstance(titles[i], descriptions[i], drawables[0], colors[0]))
            </#if>
        }
        <#else>
            // Add your slide
            addSlide()
        </#if>

        showStatusBar(false)

        <#if showSkipButton>
            showSkipButton(true)
        <#else>
            showSkipButton(false)
        </#if>
        <#if showDoneButton>
            this.progressButtonEnabled = true
        <#else>
            this.progressButtonEnabled = false
        </#if>
        
        // askForPermissions(permissions, 0)
    }

    override fun onDonePressed(currentFragment: Fragment?) {
        super.onDonePressed(currentFragment)
        finish()
    }

    override fun onSkipPressed(currentFragment: Fragment?) {
        super.onSkipPressed(currentFragment)
        finish()
    }

    override fun onSlideChanged(oldFragment: Fragment?, newFragment: Fragment?) {
        super.onSlideChanged(oldFragment, newFragment)
    }

}
