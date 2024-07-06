# Migrating from AppIntro 5.x to 6.x

Please refer to this page if you're **migrating from AppIntro version
`6.x` to `7.x`**.

AppIntro `7.x` is based on ViewPager2 instead of ViewPager, since the latter isn't in 
active development anymore.

As a result, of this change, some APIs are changed or not supported anymore. 
Here's summary of all the breaking changes that you potentially need to fix.

## Custom Transformers
Custom Transformers are used to animate slide transitions with custom logic.
If you were using this API, you need to specify a `ViewPager2.PageTransformer` instead of a `ViewPager.PageTransformer`.

The migration from `ViewPage.PageTransformer` to `ViewPager2.PageTransformer` is straightforward
as shown in the example below.

AppIntro `6.x.x`:
```kotlin
import androidx.viewpager.widget.ViewPager

class ExampleCustomTransformer: ViewPager.PageTransformer {
    override fun transformPage(page: View, position: Float) {
        // ...
    }
}

class DefaultIntro : AppIntro() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setCustomTransformer(ExampleCustomTransformer())
    }
}
```

AppIntro `7.x.x`:
```kotlin
import androidx.viewpager2.widget.ViewPager2

class ExampleCustomTransformer: ViewPager2.PageTransformer {
    override fun transformPage(page: View, position: Float) {
        // ...
    }
}

class DefaultIntro : AppIntro() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setCustomTransformer(ExampleCustomTransformer())
    }
}
```

## Custom Scroll Duration Factor
`setScrollDurationFactor`, which was already deprecated starting from AppIntro `6.3.0` has been removed.
This is a consequence of a [limitation of ViewModel2](https://issuetracker.google.com/issues/122656759).

## Summary
Here's a summary of the breaking changes:

| Removed                                           | Replace With                                       |
|:--------------------------------------------------|:---------------------------------------------------|
| `setCustomTransformer(ViewPager.PageTransformer)` | `setCustomTransformer(ViewPager2.PageTransformer)` |
| `setScrollDurationFactor(factor)`                 | Feature removed                                    |