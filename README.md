[![Android Gems](http://www.android-gems.com/badge/PaoloRotolo/AppIntro.svg?branch=master)](http://www.android-gems.com/lib/PaoloRotolo/AppIntro)

[![Maven Central](https://img.shields.io/badge/maven%20central-appintro-green.svg)](http://search.maven.org/#browse%7C2137414099)
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-AppIntro-green.svg?style=flat)](https://android-arsenal.com/details/1/1939)

# AppIntro
Android Library to make a **cool intro** for your app.

<img src="https://github.com/PaoloRotolo/AppIntro/blob/master/art/intro.png" width="300"> 
<img src="https://github.com/PaoloRotolo/AppIntro/blob/master/art/layout2.png" width="300"> 


##How to use
Add this to your **build.gradle**:
```java
repositories {
    mavenCentral()
}

dependencies {
  compile 'com.github.paolorotolo:appintro:1.4.0'
}
```

Create a new **Activity that extends AppIntro**:

```java
public class MyIntro extends AppIntro {

    // Please DO NOT override onCreate. Use init
    @Override
    public void init(Bundle savedInstanceState) {
    
        // Add your slide's fragments here
        // AppIntro will automatically generate the dots indicator and buttons.
        addSlide(new FirstSlide(), getApplicationContext());
        addSlide(new SecondSlide(), getApplicationContext());
        addSlide(new ThirdSlide(), getApplicationContext());
        addSlide(new FourthSlide(), getApplicationContext());
        
        // OPTIONAL METHODS
        // Override bar/separator color
        setBarColor(Color.parseColor("#3F51B5"));
        setSeparatorColor(Color.parseColor("#2196F3"));
        
        // Hide Skip button
        showSkipButton(false);
        
        // Turn vibration on and set intensity
        // NOTE: you will probably need to ask VIBRATE permesssion in Manifest
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
}
```

Please, **DO NOT override onCreate**. Just use **init** instead

### Layout 2
If you want to try new layout (as seen in Google's Photo app), just extend **AppIntro2** in your Activity. That's all :)

```java
public class MyIntro extends AppIntro2 {
    [...]
}
```

<img src="https://github.com/PaoloRotolo/AppIntro/blob/master/art/layout2.png" width="300"> 
<img src="https://github.com/PaoloRotolo/AppIntro/blob/master/art/layout2_2.png" width="300"> 

### Animations
AppIntro comes with a Fade animation, that you can activate with:
```java
// Put this method in init()
setFadeAnimation();
```

If you want to create nice parallax effect or your custom animation, create your own **PageTransformer** and call:

```java
// Put this method in init()
setCustomTransformer(transformer);
```

Click [here](https://github.com/PaoloRotolo/AppIntro/blob/90a513fda9b70a5e5df35435a7f2984832727eeb/AppIntroExample/app/src/main/java/com/github/paolorotolo/appintroexample/animations/CustomAnimation.java) to see how I did it in the example app.

## Example
See example code here on Github. You can also see it live downloading [this apk](https://github.com/PaoloRotolo/AppIntro/raw/master/AppIntroExample/app/app-release.apk).

<img src="https://github.com/PaoloRotolo/AppIntro/blob/master/art/example.png" width="300"> 

## Apps using it
 * [Numix Hermes](https://play.google.com/store/apps/details?id=org.numixproject.hermes);
 * [Audio Reminder Pro](https://play.google.com/store/apps/details?id=com.brandon.audioreminderpro);
 * [Wizr Daily Quotes](https://play.google.com/store/apps/details?id=com.wizrapp);

If you are using AppIntro in your app and would like to be listed here, please let me know via [email](mailto:paolorotolo@ubuntu.com)! 

## Real life example
Do you need inspiration? Check out Numix Hermes' intro.


<img src="https://github.com/PaoloRotolo/AppIntro/blob/master/art/Screenshot_2015-06-03-12-41-59.png" width="300">
<img src="https://github.com/PaoloRotolo/AppIntro/blob/master/art/Screenshot_2015-06-03-12-42-02.png" width="300">
<img src="https://github.com/PaoloRotolo/AppIntro/blob/master/art/Screenshot_2015-06-03-12-42-07.png" width="300">
<img src="https://github.com/PaoloRotolo/AppIntro/blob/master/art/Screenshot_2015-06-03-12-42-10.png" width="300">
