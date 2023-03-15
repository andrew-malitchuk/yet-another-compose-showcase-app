plugins {
    id("yacsa.android.library")
    kotlin("kapt")
}

android {
    namespace = "dev.yacsa.network"
}

dependencies {
    implementation("com.squareup.moshi:moshi-kotlin:1.14.0")
    kapt("com.squareup.moshi:moshi-kotlin-codegen:1.14.0")


}