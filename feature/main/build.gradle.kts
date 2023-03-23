plugins {
    id("yacsa.android.feature")
    id("yacsa.android.library.compose")
}

android {
    namespace = "dev.yacsa.main"
}

dependencies {
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtimeCompose)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.core.splashscreen)

//    implementation(libs.androidx.compose.material3)
    implementation("androidx.compose.material:material:1.3.1")

    implementation(project(":core:ui"))
    implementation(project(":feature:navigation"))
    implementation(project(":feature:books"))
    implementation(project(":feature:format"))
    implementation(project(":feature:onboarding"))

    implementation(project(":data:network"))
    implementation(project(":data:datastore"))
    implementation(project(":data:cryptodatastore"))

    implementation(project(":domain"))

}
