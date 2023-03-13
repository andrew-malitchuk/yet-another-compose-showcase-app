plugins {
    id("yacsa.android.feature")
    id("yacsa.android.library.compose")
}

android {
    namespace = "dev.yacsa.onboarding"
    buildFeatures{
        compose = true
    }
}

dependencies {
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtimeCompose)
    implementation(libs.androidx.navigation.compose)
    implementation("androidx.compose.material:material:1.3.1")

    implementation("androidx.compose.ui:ui-tooling:1.3.3")
    implementation("androidx.compose.ui:ui-tooling-preview:1.3.3")

    implementation(project(":feature:navigation"))
    implementation(project(":core:ui"))

}
