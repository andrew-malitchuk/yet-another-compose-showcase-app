plugins {
    id("yacsa.android.feature")
    id("yacsa.android.library.compose")
    id("kotlin-parcelize")
}

android {
    namespace = "dev.yacsa.onboarding"
}

dependencies {
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtimeCompose)
    implementation(libs.androidx.navigation.compose)
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")

    implementation("androidx.compose.material:material:1.3.1")
    implementation("com.google.accompanist:accompanist-pager:0.28.0")
    implementation("com.google.accompanist:accompanist-pager-indicators:0.28.0")


    implementation("androidx.compose.material3:material3:1.1.0-rc01")
    implementation("androidx.compose.material3:material3-window-size-class:1.1.0-rc01")

    implementation("androidx.compose.ui:ui-tooling:1.4.0")
    implementation("androidx.compose.ui:ui-tooling-preview:1.4.0")
    implementation("com.squareup.logcat:logcat:0.1")


    implementation(project(":domain"))
    implementation(project(":feature:navigation"))
    implementation(project(":core:ui"))
    implementation(project(":service:analytics"))
    implementation(project(":service:analytics:dispatcher"))
    implementation(project(":core:model"))

    implementation("io.arrow-kt:arrow-core:1.2.0-RC")
    implementation("io.arrow-kt:arrow-fx-coroutines:1.2.0-RC")
    implementation("io.arrow-kt:arrow-optics:1.2.0-RC")
    implementation(project(mapOf("path" to ":core:platform")))
    ksp("io.arrow-kt:arrow-optics-ksp-plugin:1.2.0-RC")

    implementation("com.google.accompanist:accompanist-systemuicontroller:0.28.0")

    implementation("com.github.theapache64:rebugger:1.0.0-alpha02")

    implementation("com.google.accompanist:accompanist-navigation-animation:0.31.3-beta")
}
