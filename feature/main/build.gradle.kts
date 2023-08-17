plugins {
    id("yacsa.android.feature")
    id("yacsa.android.library.compose")
    id("kotlin-parcelize")
}

android {
    namespace = "dev.yacsa.main"
    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

dependencies {
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtimeCompose)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.core.splashscreen)

    implementation(libs.compose.material)
    implementation(libs.android.lifecycle.viewmodel.compose)

    implementation(project(":core:ui"))
    implementation(project(":core:platform"))
    implementation(project(":core:model"))
    implementation(project(":core:navigation"))
    implementation(project(":feature:books"))
    implementation(project(":feature:onboarding"))
    implementation(project(":feature:featureflagmanager"))
    implementation(project(":feature:notfound"))
    implementation(project(":feature:search"))
    implementation(project(":feature:settings"))
    implementation(project(":feature:analytics"))
    implementation(project(":feature:favourite"))
    implementation(project(":feature:deeplink"))
    implementation(project(":feature:info"))

    implementation(project(":data:network"))
    implementation(project(":data:datastore"))
    implementation(project(":data:cryptodatastore"))

    implementation(project(":domain"))

    implementation(libs.kotlinx.coroutines.core)

    implementation(libs.arrow.core)
    implementation(libs.arrow.fx.coroutines)
     implementation(libs.arrow.optics)
    ksp(libs.arrow.optics.ksp.plugin)

    implementation("androidx.activity:activity-ktx:1.7.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")
    implementation(libs.hilt.navigation.compose)


    androidTestImplementation("androidx.navigation:navigation-testing:2.5.3")
    testImplementation("org.junit.platform:junit-platform-runner:1.6.2")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.2")
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

    //
    // Test rules and transitive dependencies:
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.4.7")
// Needed for createAndroidComposeRule, but not createComposeRule:
    debugImplementation("androidx.compose.ui:ui-test-manifest:1.4.7")
    androidTestImplementation("androidx.compose.ui:ui-test-manifest:1.4.7")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("com.google.dagger:hilt-android-testing:2.44.2")
    kaptAndroidTest("com.google.dagger:hilt-android-compiler:2.44.2")
    //

    implementation(libs.accompanist.navigation.animation)


}
