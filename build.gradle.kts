buildscript {
    dependencies {
        // AGP 9 bundles KGP 2.2.10 for its built-in Kotlin support.
        // Upgrade the Kotlin Gradle plugin on the buildscript classpath to control the version.
        classpath(libs.kotlin.gradle.plugin)
    }
}

plugins {
    alias(libs.plugins.application) apply false
    alias(libs.plugins.library) apply false
    alias(libs.plugins.detekt) apply false
    alias(libs.plugins.ktlint) apply false
}
