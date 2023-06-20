buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies{
        classpath("org.jetbrains.kotlin:kotlin-serialization:1.8.10")
        classpath("com.google.gms:google-services:4.3.15")
        classpath("com.google.firebase:firebase-crashlytics-gradle:2.9.4")
        classpath("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:1.18.1")
    }
}

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.ksp) apply false
    id("org.jmailen.kotlinter") version "3.14.0" apply false
    id("org.jetbrains.kotlinx.kover") version "0.6.1"
}