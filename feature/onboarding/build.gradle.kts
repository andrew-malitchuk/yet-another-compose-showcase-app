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
    implementation(libs.android.lifecycle.viewmodel.compose)
    implementation(libs.hilt.navigation.compose)

    implementation(libs.compose.material)
    implementation(libs.accompanist.pager)
    implementation(libs.accompanist.pager.indicators)

    implementation(libs.compose.material3)
     implementation(libs.compose.material3.size)

    implementation(libs.compose.ui.tooling)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.squareup.logcat)


    implementation(project(":domain"))
    implementation(project(":feature:navigation"))
    implementation(project(":core:ui"))
    implementation(project(":core:platform"))
    implementation(project(":core:localization"))
    implementation(project(":service:analytics"))
    implementation(project(":service:analytics:dispatcher"))
    implementation(project(":core:model"))

    implementation(libs.arrow.core)
    implementation(libs.arrow.fx.coroutines)
     implementation(libs.arrow.optics)
    implementation(project(mapOf("path" to ":core:platform")))
    ksp(libs.arrow.optics.ksp.plugin)

    implementation(libs.accompanist.systemuicontroller)

    implementation(libs.github.theapache64)

    implementation(libs.accompanist.navigation.animation)
    implementation(libs.faker)
}
