// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:8.0.1")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.5.0")
    }
}

// Lists all plugins used throughout the project without applying them.
plugins {
//    id("com.android.application").version("8.0.1").apply(false)
//    id("com.android.library").version("8.0.1").apply(false)
    id("org.jetbrains.kotlin.android").version("1.8.20").apply(false)
}