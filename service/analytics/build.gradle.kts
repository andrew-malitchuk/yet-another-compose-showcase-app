plugins {
    id("yacsa.android.library")
    id("yacsa.android.hilt")
    id("kotlinx-serialization")
    id("kotlin-parcelize")
}

android {
    namespace = "dev.yacsa.analytics"
}

dependencies {
    implementation(libs.kotlinx.collections.immutable)
    implementation(libs.kotlinx.serialization.json)
}
