plugins {
    id("yacsa.android.library")
    id("yacsa.android.hilt")
    kotlin("kapt")
}

android {
    namespace = "dev.yacsa.network.impl"
}

dependencies {
    implementation("com.squareup.moshi:moshi-kotlin:1.14.0")
    implementation("com.squareup.moshi:moshi-kotlin-codegen:1.14.0")

    implementation(project(":data:network"))
}
