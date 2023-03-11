plugins {
    id("yacsa.android.application")
    id("yacsa.android.application.compose")
    id("yacsa.android.hilt")
}

android {
    defaultConfig {
        namespace="dev.yacsa.app"
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

    implementation(project(":core:common"))
    implementation(project(":core:localization"))
    implementation(project(":core:model"))
    implementation(project(":core:ui"))

    implementation(project(":data:database"))
    implementation(project(":data:database:impl"))
    implementation(project(":data:datastore"))
    implementation(project(":data:datastore:impl"))
    implementation(project(":data:network"))
    implementation(project(":data:network:impl"))

    implementation(project(":domain"))
    implementation(project(":domain:impl"))

    implementation(project(":service:analytics"))

    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtimeCompose)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.compose.material3)

}