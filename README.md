[![](https://jitpack.io/v/AppIntro/AppIntro.svg)](https://jitpack.io/#AppIntro/appintro) [![Build Status](https://travis-ci.org/AppIntro/AppIntro.svg?branch=master)](https://travis-ci.org/AppIntro/AppIntro) [![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-AppIntro-green.svg?style=flat)](https://android-arsenal.com/details/1/1939) [![Join the chat at https://gitter.im/AppIntro/Lobby](https://badges.gitter.im/AppIntro/Lobby.svg)](https://gitter.im/AppIntro/Lobby?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge) [![Awesome Kotlin Badge](https://kotlin.link/awesome-kotlin.svg)](https://github.com/KotlinBy/awesome-kotlin)

# AppIntro
AppIntro is an Android Library that helps you make a **cool intro** for your app, like the ones in Google apps.

<img src="https://github.com/AppIntro/AppIntro/blob/master/art/intro.png" width="300"> <img src="https://github.com/AppIntro/AppIntro/blob/master/art/layout2.png" width="300">

*Watch the [demo video](https://www.youtube.com/watch?v=-KgAAbZz248) on YouTube*

## Usage

### Basic usage

1. **Add the JitPack repository to your build file**

 Add it in your root build.gradle at the end of repositories:
```gradle
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

2. **Add the dependency**

Please note that AppIntro supports [Android X](https://developer.android.com/jetpack/androidx/). If you haven't migrated yet, you probably want to use
a previous version of the library that uses the **old Support Library** packages (or try [Jetifier Reverse mode](https://ncorti.com/blog/jetifier-reverse)).

```gradle
	dependencies {
	        // AndroidX Capable version
	        implementation 'com.github.AppIntro:AppIntro:5.1.0'
	        
	        // *** OR ***
	        
	        // Support Library compatibility version 
	        implementation 'com.github.AppIntro:AppIntro:4.2.3'
	}
```

Create a new **Activity that extends AppIntro**:

```java
public class IntroActivity extends AppIntro {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Note here that we DO NOT use setContentView();

        // Add your slide fragments here.
        // AppIntro will automatically generate the dots indicator and buttons.
        addSlide(firstFragment);
        addSlide(secondFragment);
        addSlide(thirdFragment);
        addSlide(fourthFragment);

        // Instead of fragments, you can also use our default slide.
        // Just create a `SliderPage` and provide title, description, background and image.
        // AppIntro will do the rest.
        SliderPage sliderPage = new SliderPage();
        sliderPage.setTitle(title);
        sliderPage.setDescription(description);
        sliderPage.setImageDrawable(image);
        sliderPage.setBgColor(backgroundColor);
        addSlide(AppIntroFragment.newInstance(sliderPage));

        // OPTIONAL METHODS
        // Override bar/separator color.
        setBarColor(Color.parseColor("#3F51B5"));
        setSeparatorColor(Color.parseColor("#2196F3"));

        // Hide Skip/Done button.
        showSkipButton(false);
        setButtonsEnabled(false);

        // Turn vibration on and set intensity.
        // NOTE: you will probably need to ask VIBRATE permission in Manifest.
        setVibrate(true);
        setVibrateIntensity(30);
    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        // Do something when users tap on Skip button.
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        // Do something when users tap on Done button.
    }

    @Override
    public void onSlideChanged(@Nullable Fragment oldFragment, @Nullable Fragment newFragment) {
        super.onSlideChanged(oldFragment, newFragment);
        // Do something when the slide changes.
    }
}
```

_Note above that we DID NOT use setContentView();_

Finally, declare the activity in your Manifest like so:

``` xml
<activity android:name="com.example.example.intro"
    android:label="@string/app_intro" />
```

Do not declare the intro as your main app launcher unless you want the intro to launch every time your app starts.
Refer to the [wiki](https://github.com/AppIntro/AppIntro/wiki/How-to-Use#show-the-intro-once) for an example of how to launch the intro once from your main activity.

#### Alternative layout
If you want to try an alternative layout (as seen in Google's Photo app), just extend **AppIntro2** in your Activity. That's all :)

```java
public class IntroActivity extends AppIntro2 {
    // ...
}
```

<img src="https://github.com/AppIntro/AppIntro/blob/master/art/layout2.png" width="300"> <img src="https://github.com/AppIntro/AppIntro/blob/master/art/layout2_2.png" width="300">
<br>

#### Slides

##### Basic slides

AppIntro provides two simple classes, `AppIntroFragment` and `AppIntro2Fragment` which one can use to build simple slides.

```java
@Override
protected void onCreate(@Nullable Bundle savedInstanceState) {
    // ...

    addSlide(AppIntroFragment.newInstance(title, description, image, backgroundColor));
}
```

##### Custom slides example

One may also define custom slides using the [AppIntroCustomLayoutSlide] class.
You can just add a custom slides in this way:

```java
addSlide(AppIntroCustomLayoutSlide.newInstance(R.layout.your_slide_here));
```

There's no need to create one class for fragment anymore. :)

### Extended usage

#### Animations
AppIntro comes with some pager animations.
Choose the one you like and then activate it with:

```java
@Override
protected void onCreate(@Nullable Bundle savedInstanceState) {
    // ...

    setFadeAnimation();
}
```

Available animations:
```java
setFadeAnimation()
setZoomAnimation()
setFlowAnimation()
setSlideOverAnimation()
setDepthAnimation()
```

If you want to create nice parallax effect or your own custom animation, create your own **PageTransformer** and call:

```java
@Override
protected void onCreate(@Nullable Bundle savedInstanceState) {
    // ...

    setCustomTransformer(transformer);
}
```

Click [here](https://github.com/AppIntro/AppIntro/blob/90a513fda9b70a5e5df35435a7f2984832727eeb/AppIntroExample/app/src/main/java/com/github/paolorotolo/appintroexample/animations/CustomAnimation.java) to see how I did it in the example app.

#### Background color transitions

AppIntro supports background color transitions:

<img src="art/background_color_transition.gif" style="width: 250px">

In order to setup the transitions, simply implement `ISlideBackgroundColorHolder`:
```java
public final class MySlide extends Fragment implements ISlideBackgroundColorHolder {
    @Override
    public int getDefaultBackgroundColor() {
        // Return the default background color of the slide.
        return Color.parseColor("#000000");
    }

    @Override
    public void setBackgroundColor(@ColorInt int backgroundColor) {
        // Set the background color of the view within your slide to which the transition should be applied.
        if (layoutContainer != null) {
            layoutContainer.setBackgroundColor(backgroundColor);
        }
    }
}
```

The API is quite low-level, therefore highly customizable. The interface contains two methods:

- `getDefaultBackgroundColor`: Return the default background color (i.e. the background color the slide has in non-sliding state) of the slide here.
- `setBackgroundColor(int)`: This method will be called while swiping between two slides. Update the background color of the view to which the transition should be applied.
This is normally the root view of your Fragment's layout. But one may also apply the color transition to some other view only (i.e. a Button).

#### Runtime Permissions (Android 6.0+)

<img src="https://github.com/AppIntro/AppIntro/blob/master/art/permissions.png" width="300">

Android 6.0 introduced a new permissions model for developers. Now all your apps have to request permissions which can be a tedious thing to implement.

However, AppIntro simplifies this down to one single line of code!

```java
@Override
protected void onCreate(@Nullable Bundle savedInstanceState) {
    // ...

    // Ask for CAMERA permission on the second slide
    askForPermissions(new String[]{Manifest.permission.CAMERA}, 2); // OR

    // This will ask for the camera permission AND the contacts permission on the same slide.
    // Ensure your slide talks about both so as not to confuse the user.
    askForPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_CONTACTS}, 2);
}
```

**NOTE:** It is advised that you only put one permission in the String array unless you want the app to ask for multiple permissions on the same slide.

**NOTE 2:** Requesting permissions automatically disables sliding, and users will have to result to pressing the buttons. Please do not open any issues regarding this, as they will be immmediately closed. Thanks!

#### Slide Policies

If you want to restrict navigation between your slides (i.e. the user has to toggle a checkbox in order to continue), our **Slide Policy** feature might help you.

All you have to do is implement `ISlidePolicy` in your slides:
```java
public final class MySlide extends Fragment implements ISlidePolicy {
    @Override
    public boolean isPolicyRespected() {
        return // If user should be allowed to leave this slide
    }

    @Override
    public void onUserIllegallyRequestedNextPage() {
        // User illegally requested next slide
    }
}
```
The interface contains two methods:

- `isPolicyRespected`: The return value of this method defines if the user can leave this slide, i.e. navigate to another one
- `onUserIllegallyRequestedNextPage`: This method gets called if the user tries to leave the slide although `isPolicyRespected` returned false. One may show some error message here.

## Translating

Do you want to help AppIntro becoming international 🌍? We are more than happy!
AppIntro currently supports [the following languages](appintro/src/main/res).

To add a new translation just add a pull request with a new `strings.xml` file inside a `values-xx` folder (where `xx` is a [two-letter ISO 639-1 language code](https://en.wikipedia.org/wiki/ISO_639-1)).

In order to provide the translation, your file needs to contain the following strings:

```xml
<?xml version="1.0" encoding="utf-8"?>
<resources xmlns:tools="http://schemas.android.com/tools">
    <string name="app_intro_skip_button">[Translation for SKIP]</string>
    <string name="app_intro_next_button">[Translation for NEXT]</string>
    <string name="app_intro_back_button">[Translation for BACK]</string>
    <string name="app_intro_done_button">[Translation for DONE]</string>
    <string name="app_intro_image_content_description">[Translation for "graphics"]</string>
</resources>
```

An updated version of the english version translation is [available here](appintro/src/main/res/values/strings.xml).

## Example App
A working example app is available [here](https://github.com/AppIntro/AppIntro/tree/master/example).

## Real life examples
Do you need inspiration? A lot of apps are using AppIntro out there:

**Planets**

<img src="https://github.com/AppIntro/AppIntro/blob/master/art/planets.png">

**Hermes - Material IRC Client**

<img src="https://github.com/AppIntro/AppIntro/blob/master/art/Screenshot_2015-06-03-12-41-59.png" width="300"> <img src="https://github.com/AppIntro/AppIntro/blob/master/art/Screenshot_2015-06-03-12-42-02.png" width="300">
<img src="https://github.com/AppIntro/AppIntro/blob/master/art/Screenshot_2015-06-03-12-42-07.png" width="300"> <img src="https://github.com/AppIntro/AppIntro/blob/master/art/Screenshot_2015-06-03-12-42-10.png" width="300">

## Apps using AppIntro
If you are using AppIntro in your app and would like to be listed here, please let us know by commenting in [this issue](https://github.com/AppIntro/AppIntro/issues/325)!

 * [Numix Hermes](https://play.google.com/store/apps/details?id=org.numixproject.hermes)
 * [Audio Reminder Pro](https://play.google.com/store/apps/details?id=com.brandon.audioreminderpro)
 * [Wizr Daily Quotes](https://play.google.com/store/apps/details?id=com.wizrapp)
 * [Planets](https://play.google.com/store/apps/details?id=com.andrewq.planets)
 * [Weather Delta](https://play.google.com/store/apps/details?id=com.felkertech.n.weatherdelta)
 * [PDF Me](https://play.google.com/store/apps/details?id=com.pdfme)
 * [Circles](https://play.google.com/store/apps/details?id=com.felipejoglar.circles)
 * [Task Master](https://play.google.com/store/apps/details?id=com.cr5315.taskmaster)
 * [Smoothie Recipes](https://play.google.com/store/apps/details?id=com.skykonig.smoothierecipes)
 * [SideBar Notes](https://play.google.com/store/apps/details?id=com.app.floating.notes)
 * [just food](https://play.google.com/store/apps/details?id=scientist.jobless.foodmana)
 * [AlarmSMS](https://play.google.com/store/apps/details?id=com.qhutch.alarmsms)
 * [Aware](https://play.google.com/store/apps/details?id=com.bunemekyakilika.aware)  <!-- App is region restricted - please confirm avail. region -->
 * [neutriNote](https://play.google.com/store/apps/details?id=com.appmindlab.nano)
 * [Handwriting Note](https://play.google.com/store/apps/details?id=com.lyk.immersivenote)
 * [Friends Roulette](https://play.google.com/store/apps/details?id=com.crioltech.roulette)
 * [Karting Tools](https://play.google.com/store/apps/details?id=com.fabreax.android.kartingtools.activity)
 * [ChineseDictionary (粵韻漢典離線粵語普通話發聲中文字典)](https://play.google.com/store/apps/details?id=com.jonasng.chinesedictionary)
 * [Sifter](https://play.google.com/store/apps/details?id=sifter.social.network.archaeologist)
 * [#-ludus 2.0](https://play.google.com/store/apps/details?id=com.fallenritemonk.ludus)
 * [Snipit Text Grabber](https://play.google.com/store/apps/details?id=com.om.snipit)
 * [Service Notes](https://play.google.com/store/apps/details?id=notes.service.com.servicenotes)
 * [Salary Barometer](https://play.google.com/store/apps/details?id=anaware.salarybarometer)
 * [Best Business Idea!](https://play.google.com/store/apps/details?id=anaware.bestidea)
 * [Wi-Fi password reminder](https://play.google.com/store/apps/details?id=com.rusdelphi.wifipassword)
 * [Safe Notes](https://play.google.com/store/apps/details?id=software.codeplus.safenotes)
 * [Xpaper - Moto X Wallpapers](https://play.google.com/store/apps/details?id=com.dunrite.xpaper)
 * [Find My Parked Car](https://play.google.com/store/apps/details?id=com.ofirmiron.findmycarandroidwear)
 * [BoxPlay Music Player](https://play.google.com/store/apps/details?id=de.luckyworks.boxplay)
 * [Vape Tool Pro](https://play.google.com/store/apps/details?id=com.stasbar.vapetoolpro)
 * [NebelNiek Soundboard](https://play.google.com/store/apps/details?id=de.logtainment.nebelnieksoundboard)
 * [sdiwi | Win your purchase!](https://play.google.com/store/apps/details?id=com.sdiwi.app)
 * [Helal ve Sağlıklı Yaşam](https://play.google.com/store/apps/details?id=org.yasam.hsy.helalvesaglikliyasam)
 * [HipCar - Car Rental](https://play.google.com/store/apps/details?id=com.hipcar.android)
 * [Schematiskt Skolschema](https://play.google.com/store/apps/details?id=se.zinokader.schematiskt)
 * [Third Eye](https://play.google.com/store/apps/details?id=com.miragestacks.thirdeye)
 * [Crypton - Password Manager](https://play.google.com/store/apps/details?id=mindstorm.crypton)
 * [Web Video Cast](https://play.google.com/store/apps/details?id=com.instantbits.cast.webvideo)
 * [Sask. Geo-Memorial](https://play.google.com/store/apps/details?id=com.github.dstaflund.geomemorial)
 * [SchoolBox](https://play.google.com/store/apps/details?id=com.deenysoft.schoolbox)
 * [Fitness Challenge](https://play.google.com/store/apps/details?id=com.isidroid.fitchallenge)
 * [Crunch (ICE)](https://play.google.com/store/apps/details?id=com.figsandolives.ice.free)
 * [Filmy - Your Movie Guide](https://play.google.com/store/apps/details?id=tech.salroid.filmy)
 * [HEBF Optimizer ▪ Root](https://play.google.com/store/apps/details?id=com.androidvip.hebf)
 * [Wifi Captive Login](https://play.google.com/store/apps/details?id=com.anantharam.wificaptivelogin)
 * [IIFYM](https://play.google.com/store/apps/details?id=com.javierd.iifym)
 * [Ampwifi Winamp Remote](https://play.google.com/store/apps/details?id=com.blitterhead.ampwifi)
 * [AaiKya: Leave Tracker](https://play.google.com/store/apps/details?id=com.ranveeraggarwal.letrack)
 * [Angopapo - People around you](https://play.google.com/store/apps/details?id=com.msingapro.angopapofb)
 * [Hugetwit](https://play.google.com/store/apps/details?id=com.halilibo.hugetwit)
 * [Wake Me Up (Mumbai Railway)](https://play.google.com/store/apps/details?id=com.catacomblabs.wakemeup)
 * [SelfMote - Wireless Remote app](https://play.google.com/store/apps/details?id=com.dmicse.selfmote.free)
 * [Boo Music Player](https://play.google.com/store/apps/details?id=cdn.BooPlayer)
 * [BeatPrompter](https://play.google.com/store/apps/details?id=com.stevenfrew.beatprompter)
 * [Orario Treni Trenitalia](https://play.google.com/store/apps/details?id=com.jaus.albertogiunta.justintrain_oraritreni)
 * [Tipsy for Gardaland](https://play.google.com/store/apps/details?id=com.MonkeyLab.MyGardaland&hl=it)
 * [BlueWords](https://play.google.com/store/apps/details?id=com.thesrb.bluewords&referrer=utm_source%3Dappintro%26utm_medium%3Dgithub%26utm_campaign%3Dreadme)
  * [Best Quotes & Status 2019 (99000+ Collection)](https://play.google.com/store/apps/details?id=com.swastik.quotesandstatus&hl=en_IN)
