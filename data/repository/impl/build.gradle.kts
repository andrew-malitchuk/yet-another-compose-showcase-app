plugins {
    id("yacsa.android.library")
    id("yacsa.android.hilt")
}

android {
    namespace = "dev.yacsa.repository.impl"
}

dependencies {
    implementation(project(":data:repository"))
    implementation(project(":data:network"))
    implementation(project(":data:database"))
    implementation(project(":data:datastore"))
    implementation(project(":data:cryptodatastore"))
    implementation(project(":data:remoteconfig"))

    kapt("org.mapstruct:mapstruct-processor:1.5.5.Final")
    implementation("org.mapstruct:mapstruct:1.5.5.Final")
    implementation("org.mapstruct:mapstruct-jdk8:1.5.5.Final")


    implementation("io.arrow-kt:arrow-core:1.2.0-RC")
    implementation("io.arrow-kt:arrow-fx-coroutines:1.2.0-RC")
    implementation("io.arrow-kt:arrow-optics:1.2.0-RC")
    ksp("io.arrow-kt:arrow-optics-ksp-plugin:1.2.0-RC")

}
