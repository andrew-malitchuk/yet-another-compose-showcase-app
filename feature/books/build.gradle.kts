plugins {
    id("yacsa.android.feature")
    id("yacsa.android.library.compose")
    id("kotlin-parcelize")
}

android {
    namespace = "dev.yacsa.books"
}

dependencies {
   /* implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtimeCompose)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.android.lifecycle.viewmodel.compose)
    implementation(libs.hilt.navigation.compose)
    implementation(libs.accompanist.systemuicontroller)
    implementation(libs.coil.compose)

    implementation(libs.compose.material)
    implementation(libs.accompanist.pager)
    implementation(libs.accompanist.pager.indicators)

    implementation(libs.compose.material3)
    implementation(libs.compose.material3.size)

    implementation(libs.compose.ui.tooling)
    implementation(libs.compose.ui.tooling.preview)

    implementation(libs.squareup.logcat)

    implementation(libs.android.room.paging)
    implementation(libs.room.paging.compose)

    implementation(libs.github.theapache64)

    implementation(project(":domain"))
    implementation(project(":feature:navigation"))
    implementation(project(":core:platform"))
    implementation(project(":core:model"))
    implementation(project(":core:localization"))
    implementation(project(":core:platform"))
    implementation(project(":core:ui"))
    implementation(project(":service:featureflag"))
    implementation(project(":service:crashlytics"))

    implementation(libs.arrow.core)
    implementation(libs.arrow.fx.coroutines)
    implementation(libs.arrow.optics)
    ksp(libs.arrow.optics.ksp.plugin)

    implementation(libs.airbnb.lottie)

    implementation(libs.androidx.constraintlayout)

    implementation(libs.konfetti)


    implementation(libs.accompanist.navigation.animation)
    implementation("androidx.paging:paging-compose:1.0.0-alpha18")*/

    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtimeCompose)
    implementation(libs.androidx.navigation.compose)
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.28.0")
    implementation("io.coil-kt:coil-compose:2.2.2")

    implementation("androidx.compose.material:material:1.4.0")
    implementation("com.google.accompanist:accompanist-pager:0.28.0")
    implementation("com.google.accompanist:accompanist-pager-indicators:0.28.0")

    implementation("androidx.compose.material3:material3:1.1.0-rc01")
    implementation("androidx.compose.material3:material3-window-size-class:1.1.0-rc01")

    implementation("androidx.compose.ui:ui-tooling:1.4.0")
    implementation("androidx.compose.ui:ui-tooling-preview:1.4.0")
    implementation("com.squareup.logcat:logcat:0.1")

    implementation("androidx.room:room-paging:2.5.0")
    implementation("androidx.paging:paging-compose:1.0.0-alpha18")

    implementation("com.github.theapache64:rebugger:1.0.0-alpha02")

    implementation(project(":domain"))
    implementation(project(":feature:navigation"))
    implementation(project(":core:platform"))
    implementation(project(":core:model"))
    implementation(project(":core:localization"))
    implementation(project(":core:platform"))
    implementation(project(":core:ui"))
    implementation(project(":service:featureflag"))

    implementation("io.arrow-kt:arrow-core:1.2.0-RC")
    implementation("io.arrow-kt:arrow-fx-coroutines:1.2.0-RC")
    implementation("io.arrow-kt:arrow-optics:1.2.0-RC")
    ksp("io.arrow-kt:arrow-optics-ksp-plugin:1.2.0-RC")

    implementation("com.airbnb.android:lottie-compose:6.0.0")

    implementation("androidx.constraintlayout:constraintlayout-compose:1.1.0-alpha09")

    implementation("me.saket.telephoto:zoomable:0.3.0")
    implementation("nl.dionsegijn:konfetti-compose:2.0.2")

    implementation(project(":service:crashlytics"))

    implementation("com.google.accompanist:accompanist-navigation-animation:0.31.3-beta")

}
