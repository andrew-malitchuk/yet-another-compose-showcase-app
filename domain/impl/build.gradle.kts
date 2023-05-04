plugins {
    id("yacsa.android.library")
    id("yacsa.android.hilt")
}

android {
    namespace = "dev.yacsa.domain.impl"
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":data:repository"))
    implementation(project(":core:platform"))
    kapt("org.mapstruct:mapstruct-processor:1.5.5.Final")
    implementation("org.mapstruct:mapstruct:1.5.5.Final")
    implementation("org.mapstruct:mapstruct-jdk8:1.5.5.Final")
}
