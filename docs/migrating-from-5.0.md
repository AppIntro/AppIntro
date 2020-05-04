# Migrating from AppIntro 5.x to 6.x

Please refer to this page if you're **migrating from AppIntro version
`5.x` to `6.x`**.

AppIntro `6.x` is coming with a major refactoring and cleanup of the
API, as well as Kotlin transition. In this page you will find the
summary of all the breaking changes that you potentially need to fix.

If you were already using a
[beta version of AppIntro](https://github.com/AppIntro/AppIntro/releases/tag/6.0.0-alpha3),
this document might not completely cover your scenario.

## Package Change

With the release of AppIntro 6.x we decided to updated the **project
package** to reflect the coordinates of the Github Repo.

The package has been updated from:

`com.github.paolorotolo.appintro`

to

`com.github.appintro`

This is obviously a breaking change, and you need to updated your
AppIntro usage to reflect this.

## Page Transformer

We removed several methods from the `AppIntroBase` used to set the `PageTransformers`.
Those methods have been replaced with a single methods and a `sealed class` hierarchy.

Please refer to this table to find the relevant replacement for you:

| Old | New |
| --- | --- |
| `setFadeAnimation()` | `setTransformer(AppIntroPageTransformerType.Fade)` |
| `setZoomAnimation()` | `setTransformer(AppIntroPageTransformerType.Zoom)` |
| `setFlowAnimation()` | `setTransformer(AppIntroPageTransformerType.Flow)` |
| `setDepthAnimation()` | `setTransformer(AppIntroPageTransformerType.Depth)` |
| `setSlideOverAnimation()` | `setTransformer(AppIntroPageTransformerType.SlideOver)` |
| `setParallaxAnimation(Double, Double Double)` | `setTransformer(AppIntroPageTransformerType.Parallax(Double, Double Double))` |

## Interface Name Change

The following interfaces have been renamed to do not contain the `I` prefix anymore:

| Old name | New Name |
| -------- | -------- |
| `ISlidePolicy | `SlidePolicy |
| `ISlideBackgroundColorHolder | `SlideBackgroundColorHolder |
| `ISlideSelectionListener | `SlideSelectionListener |

## Several Classes/Methods are now final

During the Kotlin migration, we decided to intentionally restrict the
extensibility of the `AppIntroBase` and other classes. We marked as
`final` a lot of functions that are not intended to be extended in order
to prevent side effects.

Methods that can be extended are marked as `open` in the
source code.

## `AppIntroBase`

Multiple methods have been removed or have a restricted visibility set
to `private`:

```
dispatchTouchEvent(android.view.MotionEvent)
getBackButtonVisibilityWithDone()
getPager()
getSlides()
init(android.os.Bundle)
onWindowFocusChanged(boolean)
getBackButtonVisibilityWithDone()
setBackButtonVisibilityWithDone(boolean)
setOffScreenPageLimit()
```

For those methods, please find an equivalent replacement in this table:

| Removed                                                                   | Replace With                                                                            |
|:--------------------------------------------------------------------------|:----------------------------------------------------------------------------------------|
| `setGoBackLock(boolean)`                                                  | `setSystemBackButtonLocked(boolean)`                                                    |
| `getWizardMode()`                                                         | `isWizardMode()`                                                                        |
| `setVibrateIntensity(int)`                                                | `setVibrateDuration(long)`                                                              |
| `setNavBarColor(java.lang.String)`                                        | `setNavBarColor(int)` or `setNavBarColorRes(int)`                                       |
| `setCustomIndicator(com.github.paolorotolo.appintro.IndicatorController)` | `setIndicatorController(com.github.paolorotolo.appintro.indicator.IndicatorController)` |
| `setImmersiveMode(boolean)`                                               | `setImmersiveMode()`                                                                    |
| `setImmersiveMode(boolean, boolean)`                                      | `setImmersiveMode()`                                                                    |
| `setProgressButtonEnabled(boolean)`                                       | `setButtonsEnabled(boolean)`                                                            |
| `setButtonState(android.view.View, boolean)`                              | `setButtonsEnabled(boolean)`                                                            |
| `isPagerIndicatorEnabled()`                                               | `isIndicatorEnabled()`                                                                  |
| `showPagerIndicator()`                                                    | `setIndicatorEnabled(boolean)`                                                          |

## `AppIntro2`

Some methods have been removed in the AppIntro2 class:

| Removed                    | Replace with                                                          | Note                                                          |
|:---------------------------|:----------------------------------------------------------------------|:--------------------------------------------------------------|
| `setBackgroundView(View) ` | `setBackgroundResource(Integer)` or `setBackgroundDrawable(Drawable)` | The use of View as a background in `AppIntro2` is discouraged |

The `showDoneButton(boolean)` methods have also been removed as `@Deprecated`,
see [Removal of deprecated methods](#removal-of-deprecated-methods) below.

## `AppIntroFragment`

Please note that all the `newInstance` method inside the
`AppIntroFragment` class have been merged into a single one:

```kotlin
@JvmOverload
fun newInstance(
    title: CharSequence? = null,
    description: CharSequence? = null,
    @DrawableRes imageDrawable: Int = 0,
    @ColorInt backgroundColor: Int = 0,
    @ColorInt titleColor: Int = 0,
    @ColorInt descriptionColor: Int = 0,
    @FontRes titleTypefaceFontRes: Int = 0,
    @FontRes descriptionTypefaceFontRes: Int = 0,
    @DrawableRes backgroundDrawable: Int = 0
)
```

If you were using one of the following method:

* `newInstance(java.lang.CharSequence,java.lang.String,java.lang.CharSequence,java.lang.String,int,int)`
* `newInstance(java.lang.CharSequence,int,java.lang.CharSequence,int,int,int)`
* `newInstance(java.lang.CharSequence,java.lang.String,java.lang.CharSequence,java.lang.String,int,int,int,int)`
* `newInstance(java.lang.CharSequence,int,java.lang.CharSequence,int,int,int,int,int)`

your code won't compile, and you need to migrate the the aforementioned
new method (or one of its overload).

Please also note that several parameter names got renamed. This might impact you
if you were using named parameters in Kotlin:

| Old parameter | New parameter |
|:------------- |:------------- |
|`bgColor`      |`backgroundColor`|
|`bgDrawable`      |`backgroundDrawable`|
|`descColor`      |`descriptionColor`|
|`descTypeface`      |`descriptionTypeface`|
|`descTypefaceFontRes`      |`descriptionTypefaceFontRes`|

## `SliderPage` and `SliderPageBuilder`

Similarly to `AppIntroFragment`, several parameters got renamed.
Please refer to the table above for the changes affecting `SliderPage` and `SliderPageBuilder`.

## Indicators

The whole Indicators' hierarchy has been refactored inside its own
`.indicators` package:

| Old                                                      | New                                                    |
|:---------------------------------------------------------|:-------------------------------------------------------|
| `.appintro.IndicatorController`                          | `.appintro.indicator.IndicatorController`              |
| `.appintro.DefaultIndicatorController` (package private) | `.appintro.indicator.IndicatorController` (now public) |
| `.appintro.ProgressIndicatorController`                  | `.appintro.indicator.ProgressIndicatorController`      |

## Removal of Deprecated Methods

The following `@Deprecated` methods have been removed from the codebase:

| Deprecated                                                                                     | Replace with                                                                  |
|:-----------------------------------------------------------------------------------------------|:------------------------------------------------------------------------------|
| `AppIntro.showDoneButton(boolean)`                                                             | `AppIntro.setButtonsEnabled(boolean)` or `.isButtonsEnabled` Kotlin Property  |
| `AppIntro2.showDoneButton(boolean)`                                                            | `AppIntro2.setButtonsEnabled(boolean)` or `.isButtonsEnabled` Kotlin Property |
| `AppIntroBase.init(Bundle)`                                                                    | Use the `onCreate()` of your activity                                         |
| `AppIntroBase.onNextPressed()`                                                                 | `AppIntroBase.onNextPressed(Fragment)`                                        |
| `AppIntroBase.onDonePressed()`                                                                 | `AppIntroBase.onDonePressed(Fragment)`                                        |
| `AppIntroBase.onSkipPressed()`                                                                 | `AppIntroBase.onSkipPressed(Fragment)`                                        |
| `AppIntroBase.onSlideChanged()`                                                                | `AppIntroBase.onSlideChanged(Fragment, Fragment)`                             |
| `AppIntroFragment.newInstance(CharSequence,CharSequence, int, int)`                            | See the [AppIntroFragment](#appintrofragment) section above                   |
| `AppIntroFragment.newInstance(CharSequence, String, CharSequence, String, int, int)`           | See the [AppIntroFragment](#appintrofragment) section above                   |
| `AppIntroFragment.newInstance(CharSequence, int, CharSequence, int, int, int)`                 | See the [AppIntroFragment](#appintrofragment) section above                   |
| `AppIntroFragment.newInstance(CharSequence, String, CharSequence, String, int, int, int, int)` | See the [AppIntroFragment](#appintrofragment) section above                   |

## Removal of `AppIntro2Fragment`

The class `AppIntro2Fragment` has been refactored and all of its feature
are now inside the `AppIntroFragment` class/layout.

## Classes in the `.internal` packages

Classes that are inside the `.internal.` package are not part of the
public API. Use them at your own risk. Specifically in 6.x some Util
classes that were `public` have been moved inside the `.internal.`
package:

```
.appintro.AppIntroViewPager
.appintro.PagerAdapter
.appintro.PermissionObject
.appintro.ProgressIndicatorController
.appintro.ScrollerCustomDuration
.appintro.util.CustomFontCache
.appintro.util.LayoutUtil
.appintro.util.LogHelper
.appintro.util.TypefaceContainer
```

If you were using those classes before, your code will break. Please
open an [issue](https://github.com/AppIntro/AppIntro/issues) reporting
why you were using one of those classes, so we can address this scenario
in one of the upcoming release.

## Classes/Methods with `internal` visibility

Several classes in the AppIntro library have the `internal` Kotlin
visibility. Please note that those classes/methods are compiled as
`public` methods in the bytecode with a generated name. They can be
accessed via Java, but we don't recommend this as those methods/classes
can be changed/removed without a major version bump.

Please note that all the methods/classes marked as `internal` (even if
they are outside the `.internal.` package) are not part of the library
public API.

## Other moved/renamed classes

Please find here a list of other classes that has been moved or renamed:

| Old                                                               | New                                           |
|:------------------------------------------------------------------|:----------------------------------------------|
| `.appintro.AppIntroViewPager$OnNextPageRequestedListener` (inner) | `.appintro.AppIntroViewPagerListener`         |
| `.appintro.AppIntroBase$PagerOnPageChangeListener`                | `.appintro.AppIntroBase$OnPageChangeListener` |

## Removal of the Android Studio Template

We removed the Android Studio template from the `/template` as was unmaintained and not currently developed.