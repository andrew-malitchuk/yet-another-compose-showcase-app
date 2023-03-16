plugins {
    id("yacsa.android.library")
    id("yacsa.android.hilt")
}

android {
    namespace = "dev.yacsa.database.impl"
}

dependencies {
    implementation("androidx.room:room-runtime:2.5.0")
    ksp("androidx.room:room-compiler:2.5.0")
    implementation("androidx.room:room-ktx:2.5.0")
    implementation("androidx.room:room-paging:2.5.0")

    implementation(project(":data:database"))
}
