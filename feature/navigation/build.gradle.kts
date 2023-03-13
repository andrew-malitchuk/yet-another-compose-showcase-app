plugins {
    id("yacsa.android.feature")
}

android {
    namespace = "dev.yacsa.navigation"
}

dependencies {
    implementation(libs.androidx.navigation.compose)

}
