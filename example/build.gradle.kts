plugins {
    alias(libs.plugins.application)
    kotlin("android")
}

android {
    compileSdk = libs.versions.compile.sdk.version.get().toInt()
    namespace = "com.github.appintro.example"

    defaultConfig {
        minSdk = libs.versions.min.sdk.version.get().toInt()
        targetSdk = libs.versions.target.sdk.version.get().toInt()

        vectorDrawables.useSupportLibrary = true
        applicationId = "com.github.appintro.example"
    }
    publishing {
        singleVariant("release") {
            withSourcesJar()
            withJavadocJar()
        }
    }
    lint {
        disable.addAll(
            listOf(
                "MissingTranslation",
                "OldTargetApi",
                "GradleDependency"
            )
        )
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }
}

dependencies {
    implementation(project(":appintro"))
    implementation(project(":core"))
    implementation(libs.androidx.annotation)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.cardview)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.recyclerview)
    implementation(libs.androidx.core.ktx)
}
