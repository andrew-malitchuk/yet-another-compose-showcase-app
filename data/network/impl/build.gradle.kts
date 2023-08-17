plugins {
    id("yacsa.android.library")
    id("yacsa.android.hilt")
    kotlin("kapt")
}

android {
    namespace ="dev.yacsa.network.impl"
    defaultConfig {
        testInstrumentationRunner = "dev.yacsa.network.impl.HiltTestRunner"
    }
//    testOptions {
//        unitTests {
//            includeAndroidResources = true
//        }
//    }
}

dependencies {

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



//    androidTestImplementation("junit:junit:4.13.2")
//    androidTestImplementation("androidx.test.ext:junit:1.1.5")
//    androidTestImplementation("com.google.dagger:hilt-android-testing:2.44.2")
//    kaptAndroidTest("com.google.dagger:hilt-android-compiler:2.44.2")
//    androidTestImplementation("androidx.test:core-ktx:1.5.0")
//
//    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")
//
//    androidTestImplementation("com.squareup.okhttp3:mockwebserver:4.11.0")
//
//    androidTestImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.2")
//    androidTestImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.2")
//    androidTestImplementation("org.jetbrains.kotlin:kotlin-test-junit:1.7.20")
//    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.2")
//    androidTestImplementation("org.robolectric:robolectric:4.9")



    testImplementation("junit:junit:4.13.2")
    testImplementation("androidx.test.ext:junit:1.1.5")
    testImplementation("com.google.dagger:hilt-android-testing:2.44.2")
    kaptTest("com.google.dagger:hilt-android-compiler:2.44.2")
    testImplementation("androidx.test:core-ktx:1.5.0")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.2")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.2")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:1.7.20")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.2")
    testImplementation("org.robolectric:robolectric:4.9")


    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")

    testImplementation("com.squareup.okhttp3:mockwebserver:4.11.0")

}
