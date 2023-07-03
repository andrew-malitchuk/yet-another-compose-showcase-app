plugins {
    id("yacsa.android.feature")
    id("yacsa.android.library.compose")
    id("kotlin-parcelize")
}

android {
    namespace = "dev.yacsa.platform"
}

dependencies {
    implementation(libs.android.lifecycle.viewmodel.compose)
    implementation(libs.android.lifecycle.runtime)
    implementation(libs.arrow.core)
    implementation(libs.arrow.fx.coroutines)
    implementation(libs.arrow.optics)
    ksp(libs.arrow.optics.ksp.plugin)

    implementation(project(":domain"))
    implementation(project(":core:model"))

}
