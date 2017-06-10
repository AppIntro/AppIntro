<manifest xmlns:android="http://schemas.android.com/apk/res/android" >

    <application>
        <activity
            android:name="${relativePackage}.${mainActivityName}"
            android:theme="@style/Theme.AppCompat.NoActionBar">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
        <activity
            android:name="${relativePackage}.${className}"
            android:theme="@style/Theme.AppCompat.NoActionBar" />
    </application>
</manifest>
