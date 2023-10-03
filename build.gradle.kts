// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        mavenCentral()
        google()
    }
    dependencies {
        classpath ("com.android.tools.build:gradle:7.4.2")
        classpath ("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.0")
        classpath ("com.google.dagger:hilt-android-gradle-plugin:2.45")
        classpath ("org.jetbrains.kotlin:kotlin-serialization:1.8.10")


        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}
plugins {
    id ("com.android.application") version "8.0.2" apply false
    id ("com.android.library") version "8.0.2" apply false
    id ("org.jetbrains.kotlin.android") version "1.7.20" apply false
    id ("com.google.devtools.ksp") version "1.8.10-1.0.9" apply false
}