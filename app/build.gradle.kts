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
    implementation(project(":feature:main"))
    implementation(project(":feature:navigation"))
    implementation(project(":feature:featureflagmanager"))
    implementation(project(":feature:notfound"))
    implementation(project(":feature:search"))
    implementation(project(":feature:settings"))
    implementation(project(":feature:analytics"))
    implementation(project(":feature:favourite"))
    implementation(project(":feature:deeplink"))
    implementation(project(":feature:info"))

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

    implementation(project(":service:update"))
    implementation(project(":service:update:impl"))

    implementation(project(":service:crashlytics"))
    implementation(project(":service:crashlytics:impl"))

    implementation(project(":service:analytics"))
    implementation(project(":service:analytics:dispatcher"))
    implementation(project(":service:analytics:dispatcher:impl"))
    implementation(project(":service:analytics:provider"))
    implementation(project(":service:analytics:provider:impl"))
    implementation(project(":service:analytics:provider:impl:local"))
    implementation(project(":service:analytics:provider:impl:notification"))
    implementation(project(":service:analytics:provider:impl:firebase"))
    implementation(project(":service:flipper"))

    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtimeCompose)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.compose.material3)

    implementation(libs.android.lifecycle.viewmodel.compose)

    implementation(libs.firebase.common)
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.crashlytics)
    implementation(libs.android.lifecycle.process)

    debugImplementation("com.facebook.flipper:flipper:0.190.0")
    debugImplementation("com.facebook.soloader:soloader:0.10.5")
    releaseImplementation("com.facebook.flipper:flipper-noop:0.190.0")
    implementation("com.facebook.flipper:flipper-network-plugin:0.190.0")

    debugImplementation(libs.squareup.leakcanary)
    implementation(libs.squareup.logcat)

    implementation(libs.kevlar.antipiracy)
    implementation(libs.kevlar.rooting)
    implementation(libs.kevlar.integrity)
}