plugins {
    id("yacsa.android.library")
}

android {
    namespace = "dev.yacsa.database"
}

dependencies {
    implementation("androidx.room:room-runtime:2.5.0")
    ksp("androidx.room:room-compiler:2.5.0")
    implementation("androidx.room:room-ktx:2.5.0")
     implementation(libs.android.room.paging)

    implementation(libs.squareup.moshi)
    implementation("com.squareup.moshi:moshi-kotlin-codegen:1.14.0")
    ksp(libs.squareup.moshi.convertor)
}
