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
}
