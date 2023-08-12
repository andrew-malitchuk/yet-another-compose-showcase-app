plugins {
    id("yacsa.android.library")
    id("yacsa.android.hilt")
    id("kotlinx-serialization")
    id("kotlin-parcelize")
}

android {
    namespace = "dev.yacsa.provider.firebase"
}

dependencies {
    implementation(libs.kotlinx.collections.immutable)
    implementation(libs.kotlinx.serialization.json)

        implementation(libs.firebase.analytics)

    implementation(project(":service:analytics"))
    implementation(project(":service:analytics:provider"))
}
