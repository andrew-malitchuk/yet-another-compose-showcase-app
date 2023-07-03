plugins {
    id("yacsa.android.library")
    id("yacsa.android.hilt")
}

android {
    namespace = "dev.yacsa.repository"
}

dependencies {
    implementation(libs.squareup.moshi)
    ksp(libs.squareup.moshi.codegen)

    implementation(libs.kotlinx.coroutines.core)

    implementation(project(":data:datastore"))
    implementation(project(":data:cryptodatastore"))
    implementation(project(":data:database"))
    implementation(project(":data:network"))

    kapt(libs.mapstruct.processor)
    implementation(libs.mapstruct)
    implementation(libs.mapstruct.jdk8)

    implementation(libs.arrow.core)
    implementation(libs.arrow.fx.coroutines)
    implementation(libs.arrow.optics)
    ksp(libs.arrow.optics.ksp.plugin)
}
