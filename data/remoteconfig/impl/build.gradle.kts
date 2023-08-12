plugins {
    id("yacsa.android.library")
    id("yacsa.android.hilt")
    id("kotlinx-serialization")
}

android {
    namespace = "dev.yacsa.remoteconfig.impl"
}

dependencies {
    implementation(libs.datastore)
    implementation(libs.kotlinx.collections.immutable)
    implementation(libs.kotlinx.serialization.json)

    implementation(libs.firebase.common)
    implementation("com.google.firebase:firebase-config-ktx:21.3.0")
    implementation(libs.firebase.analytics)

    implementation(project(":data:remoteconfig"))


    implementation(libs.arrow.core)
    implementation(libs.arrow.fx.coroutines)
     implementation(libs.arrow.optics)
    ksp(libs.arrow.optics.ksp.plugin)
}
