// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.2" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
    id("com.google.dagger.hilt.android") version "2.48" apply false
}

buildscript {
    val navVersion by extra("2.8.2")
    val timberVersion by extra("5.0.1")
    val retrofitVersion by extra("2.11.0")
    val moshiVersion by extra("1.15.1")
    val coroutinesVersion by extra("1.7.3")
    val fragmentVersion by extra("1.8.4")

    repositories {
        google()
    }
    dependencies {
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:$navVersion")
    }

}