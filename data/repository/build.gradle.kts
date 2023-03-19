plugins {
    id("yacsa.android.library")
}

android {
    namespace = "dev.yacsa.repository"
}

dependencies {
    implementation("com.squareup.moshi:moshi-kotlin:1.14.0")
    ksp("com.squareup.moshi:moshi-kotlin-codegen:1.14.0")
}
