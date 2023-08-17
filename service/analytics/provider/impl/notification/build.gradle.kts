plugins {
    id("yacsa.android.library")
    id("yacsa.android.hilt")
    id("kotlinx-serialization")
    id("kotlin-parcelize")
}

android {
    namespace = "dev.yacsa.provider.notification"
}

dependencies {
    implementation(libs.kotlinx.collections.immutable)
    implementation(libs.kotlinx.serialization.json)

    implementation(libs.arrow.core)
    implementation(libs.arrow.fx.coroutines)
     implementation(libs.arrow.optics)
    ksp(libs.arrow.optics.ksp.plugin)

    implementation(project(":service:analytics"))
    implementation(project(":service:analytics:provider"))
    implementation(project(":core:ui"))
    implementation(project(":domain"))
}
