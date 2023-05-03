plugins {
    id("yacsa.android.library")
    id("yacsa.android.hilt")
    id("kotlin-parcelize")
}

android {
    namespace = "dev.yacsa.model"
}

dependencies {
    implementation(project(":domain"))
// https://mvnrepository.com/artifact/org.mapstruct/mapstruct-processor
    kapt("org.mapstruct:mapstruct-processor:1.5.5.Final")
    // https://mvnrepository.com/artifact/org.mapstruct/mapstruct
    implementation("org.mapstruct:mapstruct:1.5.5.Final")
// https://mvnrepository.com/artifact/org.mapstruct/mapstruct-jdk8
    implementation("org.mapstruct:mapstruct-jdk8:1.5.5.Final")

}
