plugins {
    id("yacsa.android.library")
    id("yacsa.android.hilt")
    kotlin("kapt")
}

android {
    namespace ="dev.yacsa.network.impl"
    defaultConfig {
        testInstrumentationRunner = "dev.yacsa.network.impl.MyTestRunner"
    }
}

dependencies {
/*    implementation(libs.squareup.moshi)
    implementation(libs.squareup.moshi.codegen)
    ksp(libs.squareup.moshi.convertor)
    implementation(libs.retrofit.core)

    implementation(libs.squareup.okhttp3)
    implementation(libs.squareup.okhttp3.interceptor)

    debugImplementation("com.github.chuckerteam.chucker:library:3.5.2")
    releaseImplementation("com.github.chuckerteam.chucker:library-no-op:3.5.2")

    implementation(project(":data:network"))
    implementation(project(":service:flipper"))*/

    implementation("com.squareup.moshi:moshi-kotlin:1.14.0")
    implementation("com.squareup.moshi:moshi-kotlin-codegen:1.14.0")
    implementation("com.squareup.retrofit2:converter-moshi:2.9.0")
    implementation(libs.retrofit.core)

    implementation("com.squareup.okhttp3:okhttp:5.0.0-alpha.1")
    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.1")
    implementation("androidx.test:runner:1.5.2")

    debugImplementation("com.github.chuckerteam.chucker:library:3.5.2")
    releaseImplementation("com.github.chuckerteam.chucker:library-no-op:3.5.2")


    implementation(project(":data:network"))

    androidTestImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("com.google.dagger:hilt-android-testing:2.44.2")
    kaptAndroidTest("com.google.dagger:hilt-android-compiler:2.44.2")
    androidTestImplementation("androidx.test:core-ktx:1.5.0")

    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")

}
