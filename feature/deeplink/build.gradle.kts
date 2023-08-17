plugins {
    id("yacsa.android.feature")
    id("yacsa.android.library.compose")
    id("kotlin-parcelize")
}

android {
    namespace = "dev.yacsa.deeplink"
}

dependencies {
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtimeCompose)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.android.lifecycle.viewmodel.compose)
    implementation(libs.hilt.navigation.compose)
    implementation(libs.accompanist.systemuicontroller)
    implementation(libs.coil.compose)

    implementation(libs.compose.material)
    implementation(libs.accompanist.pager)
    implementation(libs.accompanist.pager.indicators)

    implementation(libs.compose.material3)
     implementation(libs.compose.material3.size)

    implementation(libs.compose.ui.tooling)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.squareup.logcat)

    implementation(libs.android.room.paging)
    implementation(libs.room.paging.compose)

    implementation(libs.github.theapache64)

    implementation(project(":domain"))
    implementation(project(":core:navigation"))
    implementation(project(":core:platform"))
    implementation(project(":core:model"))
    implementation(project(":core:ui"))
    implementation(project(":core:localization"))
    implementation(project(":service:featureflag"))

    implementation(libs.arrow.core)
    implementation(libs.arrow.fx.coroutines)
    implementation(libs.arrow.optics)
    ksp(libs.arrow.optics.ksp.plugin)

    implementation(libs.airbnb.lottie)

    implementation(libs.accompanist.navigation.animation)

}
