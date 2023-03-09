package dev.yacsa.convention

@Suppress("unused")
enum class BuildType(val applicationIdSuffix: String? = null) {
    DEBUG(".debug"),
    RELEASE,
}

object VersionControl {
    const val TARGET_SDK=33
    const val APPLICATION_ID="dev.yacsa.app"

    const val VERSION_CODE=1
    const val VERSION_NAME="0.0.1"
}