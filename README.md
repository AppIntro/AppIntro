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


##How to use
Add this to your **build.gradle**:
```java
repositories {
    mavenCentral()
}

dependencies {
  compile 'com.github.paolorotolo:appintro:3.4.0'
}
```

Create a new **Activity that extends AppIntro**:

```java
public class MyIntro extends AppIntro {

    // Please DO NOT override onCreate. Use init.
    @Override
    public void init(Bundle savedInstanceState) {

        // Add your slide's fragments here.
        // AppIntro will automatically generate the dots indicator and buttons.
        addSlide(first_fragment);
        addSlide(second_fragment);
        addSlide(third_fragment);
        addSlide(fourth_fragment);
        
        // Instead of fragments, you can also use our default slide
        // Just set a title, description, background and image. AppIntro will do the rest.
        addSlide(AppIntroFragment.newInstance(title, description, image, background_colour));

        // OPTIONAL METHODS
        // Override bar/separator color.
        setBarColor(Color.parseColor("#3F51B5"));
        setSeparatorColor(Color.parseColor("#2196F3"));

        // Hide Skip/Done button.
        showSkipButton(false);
        setProgressButtonEnabled(false);

        // Turn vibration on and set intensity.
        // NOTE: you will probably need to ask VIBRATE permisssion in Manifest.
        setVibrate(true);
        setVibrateIntensity(30);
    }

    @Override
    public void onSkipPressed() {
    // Do something when users tap on Skip button.
    }

    @Override
    public void onDonePressed() {
    // Do something when users tap on Done button.
    }
    
        @Override
    public void onSlideChanged() {
    // Do something when the slide changes.
    }

    @Override
    public void onNextPressed() {
    // Do something when users tap on Next button.
    }

}
```

Please, **DO NOT override onCreate**. Just use **init** instead

Finally, declare the activity in your Manifest like so:

``` xml
<activity android:name="com.example.example.intro"
    android:label="@string/app_intro" />
```

Do not declare the intro as your main app launcher unless you want the intro to launch every time your app starts. Refer to the [wiki](https://github.com/PaoloRotolo/AppIntro/wiki/How-to-Use#show-the-intro-once) for an example of how to launch the intro once from your main activity.

### Layout 2
If you want to try new layout (as seen in Google's Photo app), just extend **AppIntro2** in your Activity. That's all :)

```java
public class MyIntro extends AppIntro2 {
    [...]
}
```

<img src="https://github.com/PaoloRotolo/AppIntro/blob/master/art/layout2.png" width="300">
<img src="https://github.com/PaoloRotolo/AppIntro/blob/master/art/layout2_2.png" width="300">
<br><br>
### Easy implementation of Slide Fragments
As you can see, things have changed in AppIntro 3.0.0. Now it's so easy to add new slides to AppIntro. <br><br>
For example:
 * Copy the class **SampleSlide** from my [example project](https://github.com/PaoloRotolo/AppIntro/blob/master/example/src/main/java/com/github/paolorotolo/appintroexample/SampleSlide.java).
 * Add a new slide with ```addSlide(SampleSlide.newInstance(R.layout.your_slide_here));```
 
There's no need to create one class for fragment anymore. :)

#### I've never used fragments...
No problem, just use this method and AppIntro will generate a new slide for you.

```java
addSlide(AppIntroFragment.newInstance(title, description, image, background_colour));
```

### Animations
AppIntro comes with some pager animations.
Choose the one you like and then active it with:

```java
// Put this method in init()
setFadeAnimation();
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
// Put this method in init()
setCustomTransformer(transformer);
```

Click [here](https://github.com/PaoloRotolo/AppIntro/blob/90a513fda9b70a5e5df35435a7f2984832727eeb/AppIntroExample/app/src/main/java/com/github/paolorotolo/appintroexample/animations/CustomAnimation.java) to see how I did it in the example app.

### Android 6.0 ready

<img src="https://github.com/PaoloRotolo/AppIntro/blob/master/art/permissions.png" width="300">

Android 6.0 introduced a new permissions model for developers. Now all your apps have to request permissions which can be a tetious thing to implement. 

However, AppIntro simplifies this down to one single line of code!

```java
// Put this in init()
askForPermissions(new String[]{Manifest.permssion.CAMERA}, 2); // OR

// This will ask for the camera permission AND the contacts permission on the same slide. 
// Ensure your slide talks about both so as not to confuse the user.
askForPermissions(new String[]{Manifest.permision.CAMERA, Manifest.permission.READ_CONTACTS}, 2); 
```

NOTE: It is advised that you only put one permission in the String array unless you want the app to ask for multiple perms on the same slide.

## Example
See example code here on Github. You can also see it live. Download [this app from Google Play.](https://play.google.com/store/apps/details?id=paolorotolo.github.com.appintroexample).

## Real life examples
Do you need inspiration? A lot of apps are using AppIntro out there:

**Planets**

<img src="https://github.com/PaoloRotolo/AppIntro/blob/master/art/planets.png">

**Hermes - Material IRC Client**

<img src="https://github.com/PaoloRotolo/AppIntro/blob/master/art/Screenshot_2015-06-03-12-41-59.png" width="300">
<img src="https://github.com/PaoloRotolo/AppIntro/blob/master/art/Screenshot_2015-06-03-12-42-02.png" width="300">
<img src="https://github.com/PaoloRotolo/AppIntro/blob/master/art/Screenshot_2015-06-03-12-42-07.png" width="300">
<img src="https://github.com/PaoloRotolo/AppIntro/blob/master/art/Screenshot_2015-06-03-12-42-10.png" width="300">

## Apps using it
If you are using AppIntro in your app and would like to be listed here, please let us know opening a [new issue](https://github.com/PaoloRotolo/AppIntro/issues/new)!

 * [Numix Hermes](https://play.google.com/store/apps/details?id=org.numixproject.hermes);
 * [Audio Reminder Pro](https://play.google.com/store/apps/details?id=com.brandon.audioreminderpro);
 * [Wizr Daily Quotes](https://play.google.com/store/apps/details?id=com.wizrapp);
 * [Planets](https://play.google.com/store/apps/details?id=com.andrewq.planets);
 * [Weather Delta](https://play.google.com/store/apps/details?id=com.felkertech.n.weatherdelta);
 * [PDF Me](https://play.google.com/store/apps/details?id=com.pdfme);
 * [Circles](https://play.google.com/store/apps/details?id=com.felipejoglar.circles);
 * [Task Master](https://play.google.com/store/apps/details?id=com.cr5315.taskmaster);
 * [Smoothie Recipes](https://play.google.com/store/apps/details?id=com.skykonig.smoothierecipes);
 * [SideBar Notes](https://play.google.com/store/apps/details?id=com.app.floating.notes);
 * [Just food](https://play.google.com/store/apps/details?id=scientist.jobless.foodmana);
 * [AlarmSMS] (https://play.google.com/store/apps/details?id=com.qhutch.alarmsms);
 * [Aware] (https://play.google.com/store/apps/details?id=com.bunemekyakilika.aware);
 * [neutriNote](https://play.google.com/store/apps/details?id=com.appmindlab.nano);
 * [Handwriting Note](https://play.google.com/store/apps/details?id=com.lyk.immersivenote&hl=en);
 * [Friends Roulette](https://play.google.com/store/apps/details?id=com.crioltech.roulette);
 * [Karting Tools](https://play.google.com/store/apps/details?id=com.fabreax.android.kartingtools.activity);
 * [ChineseDictionary (粵韻漢典離線粵語普通話發聲中文字典)](https://play.google.com/store/apps/details?id=com.jonasng.chinesedictionary);
 * [Sifter: the Insta of Timehop](https://play.google.com/store/apps/details?id=sifter.social.network.archaeologist);
 * [Ludus](https://play.google.com/store/apps/details?id=com.fallenritemonk.ludus);
 * [Snipit](https://play.google.com/store/apps/details?id=com.om.snipit);
 * [Service Notes](https://play.google.com/store/apps/details?id=notes.service.com.servicenotes);
