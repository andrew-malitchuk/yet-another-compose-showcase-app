package dev.yacsa.crashlytics.provider

interface CrashlyticsProvider {
    fun log(throwable: Throwable)
    fun log(message: String)
    fun log(value: Any)
}
