plugins {
    id("yacsa.android.library")
    id("yacsa.android.hilt")
}

android {
    namespace = "dev.yacsa.database.impl"

    defaultConfig {
        javaCompileOptions {
            annotationProcessorOptions {
                arguments(mapOf("room.schemaLocation" to "$projectDir/schemas"))
            }
        }
    }
}

dependencies {
    implementation("androidx.room:room-runtime:2.5.0")
    ksp("androidx.room:room-compiler:2.5.0")
    implementation("androidx.room:room-ktx:2.5.0")
     implementation(libs.android.room.paging)

    implementation(libs.squareup.moshi)
    implementation("com.squareup.moshi:moshi-kotlin-codegen:1.14.0")
    ksp(libs.squareup.moshi.convertor)

    implementation(project(":data:database"))
}
