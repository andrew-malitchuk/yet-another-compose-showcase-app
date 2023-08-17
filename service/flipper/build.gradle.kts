plugins {
    id("yacsa.android.library")
    id("yacsa.android.hilt")
}

android {
    namespace = "dev.yacsa.flipper"
}

dependencies {
    implementation(libs.kotlinx.collections.immutable)
    implementation(libs.kotlinx.serialization.json)

    debugImplementation("com.facebook.flipper:flipper:0.190.0")
    debugImplementation("com.facebook.soloader:soloader:0.10.5")
    releaseImplementation("com.facebook.flipper:flipper-noop:0.190.0")
    implementation("com.facebook.flipper:flipper-network-plugin:0.190.0")
    implementation(libs.squareup.okhttp3)
    implementation(libs.squareup.okhttp3.interceptor)

}
