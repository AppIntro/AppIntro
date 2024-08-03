plugins {
    alias(libs.plugins.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.detekt)
    alias(libs.plugins.ktlint)
    id("maven-publish")
    id("signing")
}

group = "dev.appintro"
version = "7.0.0-SNAPSHOT"

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
                    "GradleDependency"
                )
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

val POM_NAME: String by project
val POM_DESCRIPTION: String by project
val POM_LICENSE_NAME: String by project
val POM_LICENSE_URL: String by project
val POM_SCM_CONNECTION: String by project
val POM_URL: String by project

publishing {
    publications {
        afterEvaluate {
            register<MavenPublication>("release") {
                from(components["release"])

                pom {
                    name.set(POM_NAME)
                    description.set(POM_DESCRIPTION)
                    url.set(POM_URL)
                    licenses {
                        license {
                            name.set(POM_LICENSE_NAME)
                            url.set(POM_LICENSE_URL)
                        }
                    }
                    scm {
                        connection.set(POM_SCM_CONNECTION)
                        developerConnection.set(POM_SCM_CONNECTION)
                        url.set(POM_URL)
                    }
                    developers {
                        developer {
                            id.set("paolorotolo")
                            name.set("Paolo Rotolo")
                            email.set("rotolopao@gmail.com")
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

    repositories {
        maven {
            name = "OSSRH"
            url = uri("https://s01.oss.sonatype.org/content/repositories/snapshots/")

            credentials {
                username = project.findProperty("OSS_USERNAME") as String?
                password = project.findProperty("OSS_PASSWORD") as String?
            }
        }
    }
}

signing {
    afterEvaluate {
        sign(publishing.publications["release"])
    }
}
