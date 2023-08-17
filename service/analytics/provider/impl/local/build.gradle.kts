plugins {
    id("yacsa.android.library")
    id("yacsa.android.hilt")
    id("kotlinx-serialization")
    id("kotlin-parcelize")
}

android {
    namespace = "dev.yacsa.provider.local"
}

dependencies {
    implementation(libs.kotlinx.collections.immutable)
    implementation(libs.kotlinx.serialization.json)

    implementation(project(":service:analytics"))
    implementation(project(":service:analytics:provider"))
}
