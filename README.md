[![Maven Central](https://img.shields.io/badge/maven%20central-appintro-green.svg)](http://search.maven.org/#browse%7C2137414099)
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-AppIntro-green.svg?style=flat)](https://android-arsenal.com/details/1/1939)
[![Android Gems](http://www.android-gems.com/badge/PaoloRotolo/AppIntro.svg?branch=master)](http://www.android-gems.com/lib/PaoloRotolo/AppIntro)

<p>Sample App:</p>
<a href="https://play.google.com/store/apps/details?id=paolorotolo.github.com.appintroexample&utm_source=global_co&utm_medium=prtnr&utm_content=Mar2515&utm_campaign=PartBadge&pcampaignid=MKT-AC-global-none-all-co-pr-py-PartBadges-Oct1515-1"><img alt="Get it on Google Play" src="https://play.google.com/intl/en_us/badges/images/apps/en-play-badge-border.png" width="300" /></a>

# AppIntro
AppIntro is an Android Library that helps you make a **cool intro** for your app, like the ones in Google apps.

*Watch YouTube video [here](https://www.youtube.com/watch?v=OlAugnH3jFY&feature=youtu.be).*

<img src="https://github.com/PaoloRotolo/AppIntro/blob/master/art/intro.png" width="300">
<img src="https://github.com/PaoloRotolo/AppIntro/blob/master/art/layout2.png" width="300">

## Usage

### Basic usage

Add this to your **build.gradle**:

```java
repositories {
    mavenCentral()
}

dependencies {
    compile 'com.github.paolorotolo:appintro:4.1.0'
}
```

Create a new **Activity that extends AppIntro**:

```java
public class IntroActivity extends AppIntro {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Add your slide fragments here.
        // AppIntro will automatically generate the dots indicator and buttons.
        addSlide(firstFragment);
        addSlide(secondFragment);
        addSlide(thirdFragment);
        addSlide(fourthFragment);

        // Instead of fragments, you can also use our default slide
        // Just set a title, description, background and image. AppIntro will do the rest.
        addSlide(AppIntroFragment.newInstance(title, description, image, backgroundColor));

        // OPTIONAL METHODS
        // Override bar/separator color.
        setBarColor(Color.parseColor("#3F51B5"));
        setSeparatorColor(Color.parseColor("#2196F3"));

        // Hide Skip/Done button.
        showSkipButton(false);
        setProgressButtonEnabled(false);

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

Finally, declare the activity in your Manifest like so:

``` xml
<activity android:name="com.example.example.intro"
    android:label="@string/app_intro" />
```

Do not declare the intro as your main app launcher unless you want the intro to launch every time your app starts.
Refer to the [wiki](https://github.com/PaoloRotolo/AppIntro/wiki/How-to-Use#show-the-intro-once) for an example of how to launch the intro once from your main activity.

#### Alternative layout
If you want to try an alternative layout (as seen in Google's Photo app), just extend **AppIntro2** in your Activity. That's all :)

```java
public class IntroActivity extends AppIntro2 {
    // ...
}
```

<img src="https://github.com/PaoloRotolo/AppIntro/blob/master/art/layout2.png" width="300">
<img src="https://github.com/PaoloRotolo/AppIntro/blob/master/art/layout2_2.png" width="300">
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

One may also define custom slides as seen in the example project:
 * Copy the class **SampleSlide** from my [example project](https://github.com/PaoloRotolo/AppIntro/blob/master/example/src/main/java/com/github/paolorotolo/appintroexample/SampleSlide.java).
 * Add a new slide with `addSlide(SampleSlide.newInstance(R.layout.your_slide_here));`

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

Click [here](https://github.com/PaoloRotolo/AppIntro/blob/90a513fda9b70a5e5df35435a7f2984832727eeb/AppIntroExample/app/src/main/java/com/github/paolorotolo/appintroexample/animations/CustomAnimation.java) to see how I did it in the example app.

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

<img src="https://github.com/PaoloRotolo/AppIntro/blob/master/art/permissions.png" width="300">

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

## Example App
See example code [here](https://github.com/PaoloRotolo/AppIntro/tree/master/example) on GitHub. You can also see it live by downloading our example on [Google Play](https://play.google.com/store/apps/details?id=paolorotolo.github.com.appintroexample).

## Real life examples
Do you need inspiration? A lot of apps are using AppIntro out there:

**Planets**

<img src="https://github.com/PaoloRotolo/AppIntro/blob/master/art/planets.png">

**Hermes - Material IRC Client**

<img src="https://github.com/PaoloRotolo/AppIntro/blob/master/art/Screenshot_2015-06-03-12-41-59.png" width="300">
<img src="https://github.com/PaoloRotolo/AppIntro/blob/master/art/Screenshot_2015-06-03-12-42-02.png" width="300">
<img src="https://github.com/PaoloRotolo/AppIntro/blob/master/art/Screenshot_2015-06-03-12-42-07.png" width="300">
<img src="https://github.com/PaoloRotolo/AppIntro/blob/master/art/Screenshot_2015-06-03-12-42-10.png" width="300">

## Apps using AppIntro
If you are using AppIntro in your app and would like to be listed here, please let us know by opening a [new issue](https://github.com/PaoloRotolo/AppIntro/issues/new)!

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
 * [Just food](https://play.google.com/store/apps/details?id=scientist.jobless.foodmana)
 * [AlarmSMS](https://play.google.com/store/apps/details?id=com.qhutch.alarmsms)
 * [Aware](https://play.google.com/store/apps/details?id=com.bunemekyakilika.aware)
 * [neutriNote](https://play.google.com/store/apps/details?id=com.appmindlab.nano)
 * [Handwriting Note](https://play.google.com/store/apps/details?id=com.lyk.immersivenote&hl=en)
 * [Friends Roulette](https://play.google.com/store/apps/details?id=com.crioltech.roulette)
 * [Karting Tools](https://play.google.com/store/apps/details?id=com.fabreax.android.kartingtools.activity)
 * [ChineseDictionary (粵韻漢典離線粵語普通話發聲中文字典)](https://play.google.com/store/apps/details?id=com.jonasng.chinesedictionary)
 * [Sifter: the Insta of Timehop](https://play.google.com/store/apps/details?id=sifter.social.network.archaeologist)
 * [Ludus](https://play.google.com/store/apps/details?id=com.fallenritemonk.ludus)
 * [Snipit](https://play.google.com/store/apps/details?id=com.om.snipit)
 * [Service Notes](https://play.google.com/store/apps/details?id=notes.service.com.servicenotes)
 * [Salary Barometer](https://play.google.com/store/apps/details?id=anaware.salarybarometer)
 * [Best Business Idea](https://play.google.com/store/apps/details?id=anaware.bestidea)
 * [Wi-Fi Password Reminder](https://play.google.com/store/apps/details?id=com.rusdelphi.wifipassword)
 * [Safe Notes](https://play.google.com/store/apps/details?id=software.codeplus.safenotes)
 * [Xpaper](https://play.google.com/store/apps/details?id=com.dunrite.xpaper&hl=en)
 * [Find My Parked Car](https://play.google.com/store/apps/details?id=com.ofirmiron.findmycarandroidwear)
 * [BoxPlay Music Player](https://play.google.com/store/apps/details?id=de.luckyworks.boxplay)
 * [Vape Tool Pro](https://play.google.com/store/apps/details?id=com.stasbar.vapetoolpro)
 * [NebelNiek Soundboard](https://play.google.com/store/apps/details?id=de.logtainment.nebelnieksoundboard)
 * [sdiwi](https://play.google.com/store/apps/details?id=com.sdiwi.app)
 * [Helal ve Sağlıklı Yaşam](https://play.google.com/store/apps/details?id=org.yasam.hsy.helalvesaglikliyasam)
 * [HipCar](https://play.google.com/store/apps/details?id=com.hipcar.android)
 * [Schematiskt](https://play.google.com/store/apps/details?id=se.zinokader.schematiskt)
 * [Third Eye](https://play.google.com/store/apps/details?id=com.miragestacks.thirdeye)
 * [Crypton](https://play.google.com/store/apps/details?id=mindstorm.crypton)
 * [Web Video Caster](https://play.google.com/store/apps/details?id=com.instantbits.cast.webvideo)
 * [Sask. Geo-Memorial](https://play.google.com/store/apps/details?id=com.github.dstaflund.geomemorial)
 * [SchoolBox](https://play.google.com/store/apps/details?id=com.deenysoft.schoolbox)
 * [Fitness Challenge](https://play.google.com/store/apps/details?id=com.isidroid.fitchallenge)
 * [ICE: Emergency Button](https://play.google.com/store/apps/details?id=com.figsandolives.ice.free)
 * [Filmy - Your Movie Guide](https://play.google.com/store/apps/details?id=tech.salroid.filmy)
 * [HEBF Optimizer ▪ Root](https://play.google.com/store/apps/details?id=com.androidvip.hebf)
 * [Wifi Captive Login](https://play.google.com/store/apps/details?id=com.anantharam.wificaptivelogin)
 * [IIFYM](https://play.google.com/store/apps/details?id=com.javierd.iifym)
