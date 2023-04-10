plugins {
    id("yacsa.android.library")
    id("yacsa.android.hilt")
    id("kotlinx-serialization")
}

android {
    namespace = "dev.yacsa.remoteconfig.impl"
}

dependencies {
    implementation("androidx.datastore:datastore:1.0.0")
    implementation("org.jetbrains.kotlinx:kotlinx-collections-immutable:0.3.5")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.0")

    implementation("com.google.firebase:firebase-common-ktx:20.3.2")
    implementation("com.google.firebase:firebase-config-ktx:21.3.0")
    implementation("com.google.firebase:firebase-analytics-ktx:21.2.1")

    implementation(project(":data:remoteconfig"))
}
