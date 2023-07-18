plugins {
    id("yacsa.android.library")
    id("yacsa.android.hilt")
    id("kotlinx-serialization")
}

android {
    namespace = "dev.yacsa.datastore.impl"
    defaultConfig {
        testInstrumentationRunner = "dev.yacsa.network.impl.HiltTestRunner"
    }
}

dependencies {
    implementation(libs.datastore)
    implementation(libs.kotlinx.collections.immutable)
    implementation(libs.kotlinx.serialization.json)

    implementation(project(":data:datastore"))

    //
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
    implementation("androidx.test:runner:1.5.2")
    testImplementation("io.mockk:mockk:1.13.5")
    implementation(libs.faker)
    testImplementation("org.mockito.kotlin:mockito-kotlin:3.2.0")
    //
}
