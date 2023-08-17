plugins {
    id("yacsa.android.library")
}

android {
    namespace = "dev.yacsa.network"
}

dependencies {
    implementation(libs.squareup.moshi)
    ksp(libs.squareup.moshi.codegen)
    implementation(project(":service:flipper"))
}