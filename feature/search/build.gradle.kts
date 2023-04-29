plugins {
    id("yacsa.android.feature")
    id("yacsa.android.library.compose")
    id("kotlin-parcelize")
}

android {
    namespace = "dev.yacsa.search"
}

dependencies {
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtimeCompose)
    implementation(libs.androidx.navigation.compose)
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.28.0")
    implementation("io.coil-kt:coil-compose:2.2.2")

    implementation("androidx.compose.material:material:1.4.0")
    implementation("com.google.accompanist:accompanist-pager:0.28.0")
    implementation("com.google.accompanist:accompanist-pager-indicators:0.28.0")

    implementation("androidx.compose.ui:ui-tooling:1.4.0")
    implementation("androidx.compose.ui:ui-tooling-preview:1.4.0")
    implementation("com.squareup.logcat:logcat:0.1")

    implementation("androidx.room:room-paging:2.5.0")
    implementation("androidx.paging:paging-compose:1.0.0-alpha18")

    implementation("com.github.theapache64:rebugger:1.0.0-alpha02")

    implementation(project(":domain"))
    implementation(project(":feature:navigation"))
    implementation(project(":core:ui"))
    implementation(project(":core:platform"))
    implementation(project(mapOf("path" to ":core:model")))

}
