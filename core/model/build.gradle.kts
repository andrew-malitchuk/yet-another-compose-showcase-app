plugins {
    id("yacsa.android.library")
    id("yacsa.android.hilt")
    id("kotlin-parcelize")
}

android {
    namespace = "dev.yacsa.model"
}

dependencies {
    implementation(project(":domain"))
}
