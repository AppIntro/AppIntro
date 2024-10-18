plugins {
    alias(libs.plugins.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.detekt)
    alias(libs.plugins.ktlint)
    id("maven-publish")
}

group = "com.github.AppIntro"
version = "7.0.0-beta02"

android {
    namespace = "com.github.appintro"
    compileSdk = libs.versions.compile.sdk.version.get().toInt()

    defaultConfig {
        minSdk = libs.versions.min.sdk.version.get().toInt()

        consumerProguardFiles("consumer-proguard-rules.pro")
        vectorDrawables.useSupportLibrary = true
    }
    publishing {
        singleVariant("release") {
            withSourcesJar()
            withJavadocJar()
        }
    }
    lint {
        warningsAsErrors = true
        abortOnError = true
        lint {
            disable.addAll(
                listOf(
                    "MissingTranslation",
                    "OldTargetApi",
                    "GradleDependency",
                ),
            )
        }
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
    implementation(project(":core"))
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.annotation)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.viewpager2)
    implementation(libs.androidx.fragment)

    testImplementation(libs.junit)
    testImplementation(libs.mockito.core)
}

ktlint {
    debug.set(false)
    verbose.set(true)
    android.set(false)
    outputToConsole.set(true)
    ignoreFailures.set(false)
}

detekt {
    config.setFrom(files("./detekt-config.yml"))
}

val pomName: String by project
val pomDescription: String by project
val pomLicenseName: String by project
val pomLicenseUrl: String by project
val pomScmConnection: String by project
val pomUrl: String by project

publishing {
    publications {
        register<MavenPublication>("release") {
            afterEvaluate {
                from(components["release"])
            }
            pom {
                name.set(pomName)
                description.set(pomDescription)
                url.set(pomUrl)
                licenses {
                    license {
                        name.set(pomLicenseName)
                        url.set(pomLicenseUrl)
                    }
                }
                scm {
                    connection.set(pomScmConnection)
                    developerConnection.set(pomScmConnection)
                    url.set(pomUrl)
                }
                developers {
                    developer {
                        id.set("paolorotolo")
                        name.set("Paolo Rotolo")
                        email.set("paolo@rotolo.dev")
                    }
                    developer {
                        id.set("cortinico")
                        name.set("Nicola Corti")
                        email.set("corti.nico@gmail.com")
                    }
                }
            }
        }
    }
}
