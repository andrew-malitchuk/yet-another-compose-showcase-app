plugins {
    id("yacsa.android.application")
    id("yacsa.android.application.compose")
    id("yacsa.android.hilt")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
}

android {
    defaultConfig {
        namespace = "dev.yacsa.app"
    }
    buildTypes {
        val debug by getting {
            applicationIdSuffix = dev.yacsa.convention.BuildType.DEBUG.applicationIdSuffix
        }
        val release by getting {
            isMinifyEnabled = true
            applicationIdSuffix = dev.yacsa.convention.BuildType.RELEASE.applicationIdSuffix
        }
    }
}

dependencies {
    implementation(project(":feature:books"))
    implementation(project(":feature:format"))
    implementation(project(":feature:main"))
    implementation(project(":feature:person"))
    implementation(project(":feature:navigation"))
    implementation(project(":feature:featureflagmanager"))
    implementation(project(":feature:notfound"))
    implementation(project(":feature:search"))
    implementation(project(":feature:settings"))

    implementation(project(":core:common"))
    implementation(project(":core:localization"))
    implementation(project(":core:model"))
    implementation(project(":core:ui"))

    implementation(project(":data:database"))
    implementation(project(":data:database:impl"))
    implementation(project(":data:datastore"))
    implementation(project(":data:datastore:impl"))
    implementation(project(":data:cryptodatastore"))
    implementation(project(":data:cryptodatastore:impl"))
    implementation(project(":data:network"))
    implementation(project(":data:network:impl"))
    implementation(project(":data:remoteconfig"))
    implementation(project(":data:remoteconfig:impl"))
    implementation(project(":data:repository"))
    implementation(project(":data:repository:impl"))

    implementation(project(":domain"))
    implementation(project(":domain:impl"))

    implementation(project(":service:analytics"))

    implementation(project(":service:featureflag"))
    implementation(project(":service:featureflag:impl"))

    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtimeCompose)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.compose.material3)

    implementation("com.squareup.logcat:logcat:0.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1")

    implementation("com.google.firebase:firebase-common-ktx:20.3.2")
    implementation("com.google.firebase:firebase-analytics-ktx:21.2.1")
    implementation("com.google.firebase:firebase-crashlytics-ktx:18.3.6")
    implementation("androidx.lifecycle:lifecycle-process:2.6.1")

//    implementation("androidx.compose.ui:ui-tooling-preview:1.3.3")
//    implementation("androidx.compose.ui:ui-tooling:1.3.3")
}