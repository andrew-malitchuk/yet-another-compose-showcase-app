plugins {
    id("yacsa.android.library")
    id("yacsa.android.library.compose")
}

android {
    namespace = "dev.yacsa.ui"
}

dependencies {
    implementation(libs.compose.material)
    implementation(libs.accompanist.systemuicontroller)
    implementation(libs.accompanist.pager)

    implementation(libs.compose.ui.tooling)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.squareup.logcat)

    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtimeCompose)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.compose.material3)
    implementation("androidx.core:core-splashscreen:1.0.0")
    implementation(project(":feature:navigation"))
    implementation(project(":core:model"))
    implementation(project(":core:localization"))
    implementation(project(":core:platform"))
    implementation(project(":service:update"))

    implementation(libs.airbnb.lottie)
    implementation(libs.faker)
}
