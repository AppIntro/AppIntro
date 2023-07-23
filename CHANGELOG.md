# Change Log

## Version 6.3.0 *(2023-07-23)*
This is a new minor release of AppIntro. This library comes with several new features (see below) and bugfixes.

### Summary of Changes
* We deprecated `setScrollDurationFactor` since customizing scroll duration will not be supported anymore in upcoming releases of AppIntro based on ViewPager2
* Target SDK is now 33

### Enhancements 🎁
* [#1030] AppIntro now internally uses Gradle KTS and Version Catalog
* [#1080] Add ability to change done button background color
* [#1049] Handle onBackPressed deprecation
* [#1051] Register callback on onBackPressedDispatcher

### Bugfixes 🐛
* [#1002] Fix RTL bug on wrongly retained currentItem
* [#1108] Fix RTL detection
* [#1109] Fix unexpected crash when using custom layouts with wrong ids with Parallax effect

### Dependency updates 📦
* Kotlin to 1.9.0
* AGP to 8.0.2
* AppCompat to 1.6.1
* ConstraintLayout to 2.1.4

## Version 6.2.0 *(2022-01-17)*

This is a new minor release of AppIntro. This library comes with several new features (see below) and bugfixes.

### Summary of Changes

* We deprecated `AppIntroFragment.newInstance` in favor of `AppIntroFragment.createInstance`. This was needed in order
to support passing color resources instead of color int, to tackle scenarios such as configuration changes and dark mode [#979].
* Target SDK is now 31
* We exposed a couple of properties/methods on the public API that were requested by the community ([#960] and [#959])
* We added some Java examples for our Java users [#953]

### Enhancements 🎁

* [#959] Add @JvmOverloads on goToNextSlide
* [#960] Expose a protected property for slidesNumber
* [#979] Fix #926: Add color resource parameters
* [#993] Make description scrollable

### Bugfixes 🐛

* [#934] Fix ProgressIndicatorController in RTL

### Dependency updates 📦

* Java version to 11
* Kotlin to 1.6.10
* AGP to 7.0.3
* Androidx Appcompat to 1.4.0
* ConstraintLayout to 2.1.2

## Version 6.1.0 *(2021-02-03)*

This is a new minor release of AppIntro. This library comes with several new features (see below) and several bugfixes.

### Summary of Changes

* Target SDK is now 30.
* Text visualization has been improved with Autosizing TextViews and URL autolinking.
* AppIntro now offers better support for tablets (`sw600dp`).
* Slide indicator has been improved with better color blending and it won't be shown if you have only one slide.
* The AppIntro sample app has been completely rewritten with more examples (for Java, SlidePolicy and more).
* 14 translations have been added or improved.

### Enhancements 🎁

* [#922] Add support for Autosizing TextViews
* [#887] Added start/stop animation for `Animatable` images
* [#883] Changing Visibility of Progress Bar when count of slide is one
* [#878] Add sw600dp support
* [#876] Single slide indicator condition
* [#870] added URL autolinking in appintro_fragment_intro.xml
* [#869] Allow to change skip and next arrow button color
* [#857] Function for setting text appearance of Done and Skip buttons
* [#825] Added ability to color back arrow and done button
* [#796] Extend visibility of goToPreviousSlide to protected

### Bugfixes 🐛

* [#903] Deprecate the setNextPageSwipeLock method
* [#896] Fix for crash on PagerAdapter when using SlidePolicy
* [#891] Do not show back arrow on wizard mode on first slide
* [#856] Fix blending of Indicator Colors
* [#821] Fix bug with SetSwipeLock being reset to false
* [#817] enabling backward sliding when slide policy not met
* [#808] Fix isButtonsEnabled being reset to true after onCreate
* [#802] Fix goToNextSlide default param for RTL
* [#792] Remove reference to mainView in AppIntroBaseFragment
* [#791] Clearing main layout in onDestroyView

### Translations 🌍

* [#919] Romanian (RO) translation
* [#908] Added Bulgarian translation
* [#895] Azerbaijan language support
* [#880] add Ukrainian translation
* [#877] Tamil translation added
* [#874] added translation for bengali
* [#872] added translation for yoruba
* [#868] Translation for Gujarati language
* [#862] Add Japanese translation
* [#839] Complete the Chinese (Simplified) translation
* [#834] Russian localization
* [#831] Finish the French translation
* [#829] Update Persian localization
* [#812] Update strings.xml Spanish

### Dependency updates 📦

* Kotlin to 1.4.21
* AGP to 4.1.1
* Androidx Appcompat to 1.2.0
* Constraintlayout to 2.0.4

### Credits

This release was possible thanks to the contribution of:

[@CristianCardosoA](https://github.com/CristianCardosoA) [@Debanshu777](https://github.com/Debanshu777) [@Debanshu777](https://github.com/Debanshu777) [@Hoossayn](https://github.com/Hoossayn) [@Hoossayn](https://github.com/Hoossayn) [@JekRock](https://github.com/JekRock) [@LinX64](https://github.com/LinX64) [@NikolaGanchev](https://github.com/NikolaGanchev) [@RobertPal95](https://github.com/RobertPal95) [@WWCheng02](https://github.com/WWCheng02) [@ZakCodes](https://github.com/ZakCodes) [@beefsausage](https://github.com/beefsausage) [@ch22843](https://github.com/ch22843) [@cortinico](https://github.com/cortinico) [@ghost](https://github.com/ghost) [@idish](https://github.com/idish) [@kunal-ch](https://github.com/kunal-ch) [@manimaran96](https://github.com/manimaran96) [@paolorotolo](https://github.com/paolorotolo) [@siper](https://github.com/siper) [@sr01](https://github.com/sr01) [@tsumuchan](https://github.com/tsumuchan)

## Version 6.0.0 *(2020-05-04)*

This is a new major release of AppIntro. Please note that this release contains multiple new features (see below), several bugfixes, as well as multiple breaking changes.
To get a deeper overview of the breaking changes, please read the [migration document](/docs/migrating-from-5.0.md).

### Summary of Changes

* The library is now 100% in Kotlin! 🎉.
* Target SDK is now 29.
* The UI was completely revamped and refactored.
* You can now request permissions on AppIntro without having to lock the slide.
* The library has now 14 more translations.

### Enhancements 🎁

* [#748] Refactor library package name
* [#738] Fix button state not being retained on configuration change
* [#735] Added a parallax animation setting
* [#733] Move SampleSlide to library
* [#730] New Approach to Permission & Cleanup
* [#700] Add methods to allow to change done/skip text by passing String Res ID
* [#678] Support requesting permissions without locking the Swipe
* [#666] Minor improvements on downloadable fonts support
* [#647] Complete UI Overhaul
* [#642] Replace the Layout2 background color with the proper resource
* [#626] Fixing missing Content Description (#624)

### Bugfixes 🐛

* [#773] Fix bug on swipe with Permission slide
* [#770] Add missing flags for `setStatusBarColor`
* [#767] Fix setIndicatorColor crashing onCreate
* [#742] Fix Crash on orientation changes due to UninitiatedPropertyAccessException
* [#734] Move strings-vi to correct location
* [#689] AppIntroViewPager: Fix slide policy handling when sliding the view pager
* [#666] Minor improvements on downloadable fonts support
* [#653] Fix Fade Animation
* [#641] Fix overlap of the ViewPager on the bottom AppIntro bar

### Translations 🌍

* [#723] Add Norwegian translation
* [#715] Add Korean translation
* [#714] Added Dutch translations
* [#712] add Vietnamese
* [#696] Add slovak translation
* [#694] Added Serbian translation
* [#693] Adding Greek Translation
* [#687] added Polish translation
* [#671] Best Pactise Builder Pattern along with Missing Arabic Word Translations.
* [#639] Update Skip Icon and add Hindi translation.
* [#637] Added PT and changed PT-BR
* [#635] Add missing German Translations
* [#629] Create indonesian translation
* [#620] added Czech (cs) translation

### Library Internals ⚙️

* [#774] Move from Travis to GitHub Actions
* [#768] Refactor Transformers to use a sealed class
* [#766] Add missing @Res annotations
* [#765] Remove `I` prefix from interface names
* [#764] Remove extra LinearLayout qualifier in DotIndicatorController
* [#763] Remove dependency on kotlin-reflect
* [#762] Updating several dependencies
* [#747] Rewrite example in Kotlin and simplify code
* [#739] Fix Visibility leakage before releasing 6.0.0
* [#744] Refactor example package name, update gradle
* [#729] Update setButtonsEnabled Deprecation note
* [#674] Fix typo in OnPageChangeListener
* [#670] Convert AppIntroBase to Kotlin
* [#634] Kotlinize the AppIntroViewPager
* [#613] Kotlinize all the Abstract Base Classes
* [#612] Kotlinize fragments
* [#611] Kotlinize the ViewPager
* [#605] Kotlinize the ScrollerCustomDuration
* [#604] Convert all the interfaces to Kotlin
* [#602] Kotlinize the 'indicator' package
* [#601] Kotlinize the PermissionWrapper
* [#600] Kotlinize the 'util' package
* [#574] Add prefix for resources (Closes: #573)

### Infrastructure 🏗

* [#728] Gradle to 6.1
* [#726] Update Dependencies
* [#724] Fix Travis failure due to Detekt
* [#698] Update Dependencies to latest versions
* [#691] Make Travis run all the Gradle tasks
* [#684] Introduce KtLint and Detekt
* [#683] MaterialDrawer to 6.1.2
* [#681] Gradle to 5.4.1
* [#680] Cleanup all the Sonatype/MavenCentral publishing files
* [#677] Update dependencies
* [#633] Updating Gradle to 5.1.1
* [#631] Updating AndroidX to the latest version
* [#625] Updating Kotlin to 1.3.11
* [#606] Bumping Kotlin to 1.3

### Credits

This release was possible thanks to the contribution of:

[@AnuthaDev](https://github.com/AnuthaDev) [@bartekpacia](https://github.com/bartekpacia) [@chihung93](https://github.com/chihung93) [@cortinico](https://github.com/cortinico) [@dragstor](https://github.com/dragstor) [@elegktara37](https://github.com/elegktara37) [@fchauveau](https://github.com/fchauveau) [@Goopher](https://github.com/Goopher) [@GuilhE](https://github.com/GuilhE) [@ivaniskandar](https://github.com/ivaniskandar) [@ivaniskandar](https://github.com/ivaniskandar) [@Kimjio](https://github.com/Kimjio) [@maxee](https://github.com/maxee) [@moxspoy](https://github.com/moxspoy) [@MTRNord](https://github.com/MTRNord) [@paolorotolo](https://github.com/paolorotolo) [@perchrh](https://github.com/perchrh) [@vzahradnik](https://github.com/vzahradnik) [@Younes-Charfaoui](https://github.com/Younes-Charfaoui) [@zpapez](https://github.com/zpapez)

## Version 5.1.0 *(2018-10-23)*

* Fixed issue that caused a build failure on Kotlin projects [#597];
* Added support for Android 8.0 custom Fonts API [#590];
* Updated Translations;
* Miscellaneous bug fixes and performance improvements;

## Version 5.0.1 *(2018-10-16)*

* Fixed incorrect behaviour when android:supportsRtl="true" was present in app's Manifest;
* Fixed RTL support;
* Update Translations;
* Miscellaneous bug fixes and performance improvements;

## Version 5.0.0 *(2018-10-7)*

* Migrate to AndroidX;
* Target SDK 28;
* Update Translations;
* Miscellaneous bug fixes and performance improvements;

Previous release notes can be found here: [releases]

[#574]: https://github.com/AppIntro/AppIntro/pull/574
[#590]: https://github.com/AppIntro/AppIntro/pull/590
[#597]: https://github.com/AppIntro/AppIntro/pull/597
[#600]: https://github.com/AppIntro/AppIntro/pull/600
[#601]: https://github.com/AppIntro/AppIntro/pull/601
[#602]: https://github.com/AppIntro/AppIntro/pull/602
[#604]: https://github.com/AppIntro/AppIntro/pull/604
[#605]: https://github.com/AppIntro/AppIntro/pull/605
[#606]: https://github.com/AppIntro/AppIntro/pull/606
[#611]: https://github.com/AppIntro/AppIntro/pull/611
[#612]: https://github.com/AppIntro/AppIntro/pull/612
[#613]: https://github.com/AppIntro/AppIntro/pull/613
[#620]: https://github.com/AppIntro/AppIntro/pull/620
[#625]: https://github.com/AppIntro/AppIntro/pull/625
[#626]: https://github.com/AppIntro/AppIntro/pull/626
[#629]: https://github.com/AppIntro/AppIntro/pull/629
[#631]: https://github.com/AppIntro/AppIntro/pull/631
[#633]: https://github.com/AppIntro/AppIntro/pull/633
[#634]: https://github.com/AppIntro/AppIntro/pull/634
[#635]: https://github.com/AppIntro/AppIntro/pull/635
[#637]: https://github.com/AppIntro/AppIntro/pull/637
[#639]: https://github.com/AppIntro/AppIntro/pull/639
[#641]: https://github.com/AppIntro/AppIntro/pull/641
[#642]: https://github.com/AppIntro/AppIntro/pull/642
[#647]: https://github.com/AppIntro/AppIntro/pull/647
[#653]: https://github.com/AppIntro/AppIntro/pull/653
[#666]: https://github.com/AppIntro/AppIntro/pull/666
[#666]: https://github.com/AppIntro/AppIntro/pull/666
[#670]: https://github.com/AppIntro/AppIntro/pull/670
[#671]: https://github.com/AppIntro/AppIntro/pull/671
[#674]: https://github.com/AppIntro/AppIntro/pull/674
[#677]: https://github.com/AppIntro/AppIntro/pull/677
[#678]: https://github.com/AppIntro/AppIntro/pull/678
[#680]: https://github.com/AppIntro/AppIntro/pull/680
[#681]: https://github.com/AppIntro/AppIntro/pull/681
[#683]: https://github.com/AppIntro/AppIntro/pull/683
[#684]: https://github.com/AppIntro/AppIntro/pull/684
[#687]: https://github.com/AppIntro/AppIntro/pull/687
[#689]: https://github.com/AppIntro/AppIntro/pull/689
[#691]: https://github.com/AppIntro/AppIntro/pull/691
[#693]: https://github.com/AppIntro/AppIntro/pull/693
[#694]: https://github.com/AppIntro/AppIntro/pull/694
[#696]: https://github.com/AppIntro/AppIntro/pull/696
[#698]: https://github.com/AppIntro/AppIntro/pull/698
[#700]: https://github.com/AppIntro/AppIntro/pull/700
[#712]: https://github.com/AppIntro/AppIntro/pull/712
[#714]: https://github.com/AppIntro/AppIntro/pull/714
[#715]: https://github.com/AppIntro/AppIntro/pull/715
[#723]: https://github.com/AppIntro/AppIntro/pull/723
[#724]: https://github.com/AppIntro/AppIntro/pull/724
[#726]: https://github.com/AppIntro/AppIntro/pull/726
[#728]: https://github.com/AppIntro/AppIntro/pull/728
[#729]: https://github.com/AppIntro/AppIntro/pull/729
[#730]: https://github.com/AppIntro/AppIntro/pull/730
[#733]: https://github.com/AppIntro/AppIntro/pull/733
[#734]: https://github.com/AppIntro/AppIntro/pull/734
[#735]: https://github.com/AppIntro/AppIntro/pull/735
[#738]: https://github.com/AppIntro/AppIntro/pull/738
[#739]: https://github.com/AppIntro/AppIntro/pull/739
[#742]: https://github.com/AppIntro/AppIntro/pull/742
[#744]: https://github.com/AppIntro/AppIntro/pull/744
[#747]: https://github.com/AppIntro/AppIntro/pull/747
[#748]: https://github.com/AppIntro/AppIntro/pull/748
[#762]: https://github.com/AppIntro/AppIntro/pull/762
[#763]: https://github.com/AppIntro/AppIntro/pull/763
[#764]: https://github.com/AppIntro/AppIntro/pull/764
[#765]: https://github.com/AppIntro/AppIntro/pull/765
[#766]: https://github.com/AppIntro/AppIntro/pull/766
[#767]: https://github.com/AppIntro/AppIntro/pull/767
[#768]: https://github.com/AppIntro/AppIntro/pull/768
[#770]: https://github.com/AppIntro/AppIntro/pull/770
[#773]: https://github.com/AppIntro/AppIntro/pull/773
[#774]: https://github.com/AppIntro/AppIntro/pull/774
[#791]: https://github.com/AppIntro/AppIntro/pull/791
[#792]: https://github.com/AppIntro/AppIntro/pull/792
[#796]: https://github.com/AppIntro/AppIntro/pull/796
[#802]: https://github.com/AppIntro/AppIntro/pull/802
[#808]: https://github.com/AppIntro/AppIntro/pull/808
[#812]: https://github.com/AppIntro/AppIntro/pull/812
[#817]: https://github.com/AppIntro/AppIntro/pull/817
[#821]: https://github.com/AppIntro/AppIntro/pull/821
[#825]: https://github.com/AppIntro/AppIntro/pull/825
[#829]: https://github.com/AppIntro/AppIntro/pull/829
[#831]: https://github.com/AppIntro/AppIntro/pull/831
[#834]: https://github.com/AppIntro/AppIntro/pull/834
[#839]: https://github.com/AppIntro/AppIntro/pull/839
[#856]: https://github.com/AppIntro/AppIntro/pull/856
[#857]: https://github.com/AppIntro/AppIntro/pull/857
[#862]: https://github.com/AppIntro/AppIntro/pull/862
[#868]: https://github.com/AppIntro/AppIntro/pull/868
[#869]: https://github.com/AppIntro/AppIntro/pull/869
[#870]: https://github.com/AppIntro/AppIntro/pull/870
[#872]: https://github.com/AppIntro/AppIntro/pull/872
[#874]: https://github.com/AppIntro/AppIntro/pull/874
[#876]: https://github.com/AppIntro/AppIntro/pull/876
[#877]: https://github.com/AppIntro/AppIntro/pull/877
[#878]: https://github.com/AppIntro/AppIntro/pull/878
[#880]: https://github.com/AppIntro/AppIntro/pull/880
[#883]: https://github.com/AppIntro/AppIntro/pull/883
[#887]: https://github.com/AppIntro/AppIntro/pull/887
[#891]: https://github.com/AppIntro/AppIntro/pull/891
[#895]: https://github.com/AppIntro/AppIntro/pull/895
[#896]: https://github.com/AppIntro/AppIntro/pull/896
[#903]: https://github.com/AppIntro/AppIntro/pull/903
[#908]: https://github.com/AppIntro/AppIntro/pull/908
[#919]: https://github.com/AppIntro/AppIntro/pull/919
[#922]: https://github.com/AppIntro/AppIntro/pull/922
[#934]: https://github.com/AppIntro/AppIntro/pull/934
[#953]: https://github.com/AppIntro/AppIntro/pull/953
[#959]: https://github.com/AppIntro/AppIntro/pull/959
[#960]: https://github.com/AppIntro/AppIntro/pull/960
[#979]: https://github.com/AppIntro/AppIntro/pull/979
[#993]: https://github.com/AppIntro/AppIntro/pull/993
[releases]: https://github.com/AppIntro/AppIntro/releases?after=v5.0.0

