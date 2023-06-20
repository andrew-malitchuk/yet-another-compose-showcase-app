plugins {
    id("yacsa.android.feature")
    id("yacsa.android.library.compose")
    id("kotlin-parcelize")
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
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1")


    implementation(project(":core:ui"))
    implementation(project(":core:platform"))
    implementation(project(":core:model"))
    implementation(project(":feature:navigation"))
    implementation(project(":feature:books"))
    implementation(project(":feature:onboarding"))
    implementation(project(":feature:featureflagmanager"))
    implementation(project(":feature:notfound"))
    implementation(project(":feature:search"))
    implementation(project(":feature:settings"))
    implementation(project(":feature:analytics"))
    implementation(project(":feature:favourite"))
    implementation(project(":feature:deeplink"))
    implementation(project(":feature:info"))

    implementation(project(":data:network"))
    implementation(project(":data:datastore"))
    implementation(project(":data:cryptodatastore"))

    implementation(project(":domain"))

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")

    implementation("io.arrow-kt:arrow-core:1.2.0-RC")
    implementation("io.arrow-kt:arrow-fx-coroutines:1.2.0-RC")
    implementation("io.arrow-kt:arrow-optics:1.2.0-RC")
    ksp("io.arrow-kt:arrow-optics-ksp-plugin:1.2.0-RC")

    implementation("androidx.activity:activity-ktx:1.7.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")


    implementation("com.google.accompanist:accompanist-navigation-animation:0.31.3-beta")


}
