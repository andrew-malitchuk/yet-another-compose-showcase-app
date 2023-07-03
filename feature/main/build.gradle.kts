plugins {
    id("yacsa.android.feature")
    id("yacsa.android.library.compose")
    id("kotlin-parcelize")
}

android {
    namespace = "dev.yacsa.main"
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
    implementation(project(":feature:navigation"))
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


    implementation(libs.accompanist.navigation.animation)


}
