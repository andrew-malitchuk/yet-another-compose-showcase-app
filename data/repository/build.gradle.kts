plugins {
    id("yacsa.android.library")
    id("yacsa.android.hilt")
}

android {
    namespace = "dev.yacsa.repository"
}

dependencies {
    implementation("com.squareup.moshi:moshi-kotlin:1.14.0")
    ksp("com.squareup.moshi:moshi-kotlin-codegen:1.14.0")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")

    implementation(project(":data:datastore"))
    implementation(project(":data:cryptodatastore"))
    implementation(project(":data:database"))
    implementation(project(":data:network"))

    kapt("org.mapstruct:mapstruct-processor:1.5.5.Final")
    implementation("org.mapstruct:mapstruct:1.5.5.Final")
    implementation("org.mapstruct:mapstruct-jdk8:1.5.5.Final")

    implementation("io.arrow-kt:arrow-core:1.2.0-RC")
    implementation("io.arrow-kt:arrow-fx-coroutines:1.2.0-RC")
    implementation("io.arrow-kt:arrow-optics:1.2.0-RC")
    ksp("io.arrow-kt:arrow-optics-ksp-plugin:1.2.0-RC")

}
