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


    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtimeCompose)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.compose.material3)
    implementation("androidx.core:core-splashscreen:1.0.0")
}
