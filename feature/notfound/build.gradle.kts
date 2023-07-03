plugins {
    id("yacsa.android.feature")
    id("yacsa.android.library.compose")
    id("kotlin-parcelize")
}

android {
    namespace = "dev.yacsa.notfound"
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
    implementation(libs.accompanist.systemuicontroller)

    implementation(libs.compose.ui.tooling)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.squareup.logcat)

    implementation(libs.compose.material3)
     implementation(libs.compose.material3.size)

    implementation(project(":domain"))
    implementation(project(":feature:navigation"))
    implementation(project(":core:ui"))
    implementation(project(":core:platform"))
    implementation(project(":service:featureflag:impl"))
    implementation(project(":service:featureflag"))
    implementation(project(":data:repository"))

   implementation(libs.accompanist.navigation.animation)
}
