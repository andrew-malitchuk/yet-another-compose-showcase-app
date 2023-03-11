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

}
