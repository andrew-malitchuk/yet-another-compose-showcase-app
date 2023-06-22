package dev.yacsa.crashlytics.provider

interface LoggerProvider {
    fun setProperty(key: String, value: String)
    fun setProperty(key: String, value: Boolean)
    fun setProperty(key: String, value: Int)
    fun setProperty(key: String, value: Float)
    fun setProperty(key: String, value: Long)
    fun setProperty(key: String, value: Double)
}
