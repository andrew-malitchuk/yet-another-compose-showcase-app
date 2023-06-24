plugins {
    id("yacsa.android.library")
    id("yacsa.android.library.compose")
}

android {
    namespace = "dev.yacsa.ui"
}

dependencies {
//    implementation(libs.androidx.compose.material3)
    implementation("androidx.compose.material:material:1.3.1")
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.28.0")
    implementation("com.google.accompanist:accompanist-pager:0.28.0")


    implementation("androidx.compose.ui:ui-tooling:1.4.0")
    implementation("androidx.compose.ui:ui-tooling-preview:1.4.0")
    implementation("com.squareup.logcat:logcat:0.1")

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

    implementation("com.airbnb.android:lottie-compose:6.0.0")
    implementation("io.github.serpro69:kotlin-faker:1.14.0")
}
