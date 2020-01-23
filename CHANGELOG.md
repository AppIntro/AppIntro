# Change Log

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

[#748] Refactor library package name 
[#738] Fix button state not being retained on configuration change
[#735] Added a parallax animation setting 
[#733] Move SampleSlide to library
[#730] New Approach to Permission & Cleanup 
[#700] Add methods to allow to change done/skip text by passing String Res ID
[#678] Support requesting permissions without locking the Swipe
[#666] Minor improvements on downloadable fonts support
[#647] Complete UI Overhaul
[#642] Replace the Layout2 background color with the proper resource
[#626] Fixing missing Content Description (#624)

### Bugfixes 🐛

[#773] Fix bug on swipe with Permission slide 
[#770] Add missing flags for `setStatusBarColor`
[#767] Fix setIndicatorColor crashing onCreate 
[#742] Fix Crash on orientation changes due to UninitiatedPropertyAccessException
[#734] Move strings-vi to correct location 
[#689] AppIntroViewPager: Fix slide policy handling when sliding the view pager
[#666] Minor improvements on downloadable fonts support
[#653] Fix Fade Animation
[#641] Fix overlap of the ViewPager on the bottom AppIntro bar

### Translations 🌍

[#723] Add Norwegian translation
[#715] Add Korean translation
[#714] Added Dutch translations
[#712] add Vietnamese
[#696] Add slovak translation
[#694] Added Serbian translation
[#693] Adding Greek Translation
[#687] added Polish translation
[#671] Best Pactise Builder Pattern along with Missing Arabic Word Translations.
[#639] Update Skip Icon and add Hindi translation.
[#637] Added PT and changed PT-BR
[#635] Add missing German Translations
[#629] Create indonesian translation
[#620] added Czech (cs) translation

### Library Internals ⚙️

[#774] Move from Travis to Github Actions
[#768] Refactor Transformers to use a sealed class
[#766] Add missing @Res annotations
[#765] Remove `I` prefix from interface names
[#764] Remove extra LinearLayout qualifier in DotIndicatorController
[#763] Remove dependency on kotlin-reflect
[#762] Updating several dependencies
[#747] Rewrite example in Kotlin and simplify code
[#739] Fix Visibility leakage before releasing 6.0.0
[#744] Refactor example package name, update gradle
[#729] Update setButtonsEnabled Deprecation note
[#674] Fix typo in OnPageChangeListener
[#670] Convert AppIntroBase to Kotlin
[#634] Kotlinize the AppIntroViewPager
[#613] Kotlinize all the Abstract Base Classes
[#612] Kotlinize fragments
[#611] Kotlinize the ViewPager
[#605] Kotlinize the ScrollerCustomDuration
[#604] Convert all the interfaces to Kotlin
[#602] Kotlinize the 'indicator' package
[#601] Kotlinize the PermissionWrapper
[#600] Kotlinize the 'util' package
[#574] Add prefix for resources (Closes: #573)

### Infrastructure 🏗

[#728] Gradle to 6.1
[#726] Update Dependencies
[#724] Fix Travis failure due to Detekt
[#698] Update Dependencies to latest versions
[#691] Make Travis run all the Gradle tasks
[#684] Introduce KtLint and Detekt
[#683] MaterialDrawer to 6.1.2
[#681] Gradle to 5.4.1
[#680] Cleanup all the Sonatype/MavenCentral publishing files
[#677] Update dependencies
[#633] Updating Gradle to 5.1.1
[#631] Updating AndroidX to the latest version
[#625] Updating Kotlin to 1.3.11
[#606] Bumping Kotlin to 1.3

### Credits

This release was possible thanks to the contribution of:

@AnuthaDev @bartekpacia @chihung93 @cortinico @dragstor @elegktara37 @fchauveau @Goopher @GuilhE @ivaniskandar @ivaniskandar @Kimjio @maxee @moxspoy @MTRNord @paolorotolo @perchrh @vzahradnik @Younes-Charfaoui @zpapez

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

[releases]: https://github.com/AppIntro/AppIntro/releases?after=v5.0.0
[#597]: https://github.com/AppIntro/AppIntro/pull/597
[#590]: https://github.com/AppIntro/AppIntro/pull/590
[#574]: https://github.com/AppIntro/AppIntro/pull/574
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

