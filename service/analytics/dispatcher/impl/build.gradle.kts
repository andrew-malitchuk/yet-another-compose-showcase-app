plugins {
    id("yacsa.android.library")
    id("yacsa.android.hilt")
    id("kotlinx-serialization")
    id("kotlin-parcelize")
}

android {
    namespace = "dev.yacsa.dispatcher.impl"
}

dependencies {
    implementation(libs.kotlinx.collections.immutable)
    implementation(libs.kotlinx.serialization.json)

    implementation(project(":service:analytics"))
    implementation(project(":service:analytics:dispatcher"))
    implementation(project(":service:analytics:provider"))
    implementation(project(":service:analytics:provider:impl"))

}
