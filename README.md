[![Android Gems](http://www.android-gems.com/badge/PaoloRotolo/AppIntro.svg?branch=master)](http://www.android-gems.com/lib/PaoloRotolo/AppIntro)

[![Maven Central](https://img.shields.io/badge/maven%20central-appintro-green.svg)](http://search.maven.org/#artifactdetails%7Ccom.github.paolorotolo%7Cappintro%7C1.0.0%7C)
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-AppIntro-green.svg?style=flat)](https://android-arsenal.com/details/1/1939)

# AppIntro
Android Library to make a **cool intro** for your app.

<img src="https://github.com/PaoloRotolo/AppIntro/blob/master/art/intro.png" width="300"> 

##How to use
Add this to your **build.gradle**:
```java
repositories {
    mavenCentral()
}

dependencies {
  compile 'com.github.paolorotolo:appintro:1.2.1'
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
        addSlide(new FirstSlide(), getApplicationContext());
        addSlide(new SecondSlide(), getApplicationContext());
        addSlide(new ThirdSlide(), getApplicationContext());
        addSlide(new FourthSlide(), getApplicationContext());
        
        // OPTIONAL METHODS
        // Override bar/separator.
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

## Example
See example code here on Github. You can also see it live downloading [this apk](https://github.com/PaoloRotolo/AppIntro/raw/master/AppIntroExample/app/app-release.apk).

## Apps using it
 * [Numix Hermes](https://play.google.com/store/apps/details?id=org.numixproject.hermes).

If you are using AppIntro in your app and would like to be listed here, please let me know via [email](mailto:paolorotolo@ubuntu.com)! 

## Real life example
Do you need inspiration? Check out Numix Hermes' intro.


<img src="https://github.com/PaoloRotolo/AppIntro/blob/master/art/Screenshot_2015-06-03-12-41-59.png" width="300">
<img src="https://github.com/PaoloRotolo/AppIntro/blob/master/art/Screenshot_2015-06-03-12-42-02.png" width="300">
<img src="https://github.com/PaoloRotolo/AppIntro/blob/master/art/Screenshot_2015-06-03-12-42-07.png" width="300">
<img src="https://github.com/PaoloRotolo/AppIntro/blob/master/art/Screenshot_2015-06-03-12-42-10.png" width="300">
