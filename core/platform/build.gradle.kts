plugins {
    id("yacsa.android.feature")
    id("yacsa.android.library.compose")
    id("kotlin-parcelize")
}

android {
    namespace = "dev.yacsa.platform"
}

dependencies {
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")

    implementation("io.arrow-kt:arrow-core:1.2.0-RC")
    implementation("io.arrow-kt:arrow-fx-coroutines:1.2.0-RC")
    implementation("io.arrow-kt:arrow-optics:1.2.0-RC")
    implementation("androidx.core:core-splashscreen:1.0.1")
    ksp("io.arrow-kt:arrow-optics-ksp-plugin:1.2.0-RC")

    implementation(project(":domain"))
    implementation(project(":core:model"))

}
