plugins {
    id("yacsa.android.library")
    id("yacsa.android.hilt")
}

android {
    namespace = "dev.yacsa.domain.impl"
}

dependencies {
    implementation(project(":domain"))
}
