plugins {
    id("yacsa.android.feature")
    id("yacsa.android.library.compose")
    id("yacsa.android.hilt")
    id("kotlinx-serialization")
}

android {
    namespace = "dev.yacsa.crashlytics.impl"
}

dependencies {
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtimeCompose)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.android.lifecycle.viewmodel.compose)
    implementation(libs.hilt.navigation.compose)
    implementation(libs.accompanist.systemuicontroller)
    implementation(libs.coil.compose)

    implementation(libs.compose.ui.tooling)
    implementation(libs.compose.ui.tooling.preview)

    implementation(libs.datastore)
    implementation(libs.kotlinx.collections.immutable)
    implementation(libs.kotlinx.serialization.json)


    implementation(libs.arrow.core)
    implementation(libs.arrow.fx.coroutines)
    implementation(libs.arrow.optics)
    ksp(libs.arrow.optics.ksp.plugin)

    implementation(project(":service:crashlytics"))

    implementation(libs.firebase.common)
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.crashlytics)
}
