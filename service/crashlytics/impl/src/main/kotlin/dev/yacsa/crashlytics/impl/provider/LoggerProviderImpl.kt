package dev.yacsa.crashlytics.impl.provider

import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.ktx.Firebase
import dev.yacsa.crashlytics.provider.LoggerProvider
import javax.inject.Inject

class LoggerProviderImpl @Inject constructor() : LoggerProvider {

    override fun setProperty(key: String, value: String) {
        Firebase.crashlytics.setCustomKey(key, value)
    }

    override fun setProperty(key: String, value: Boolean) {
        Firebase.crashlytics.setCustomKey(key, value)
    }

    override fun setProperty(key: String, value: Int) {
        Firebase.crashlytics.setCustomKey(key, value)
    }

    override fun setProperty(key: String, value: Float) {
        Firebase.crashlytics.setCustomKey(key, value)
    }

    override fun setProperty(key: String, value: Long) {
        Firebase.crashlytics.setCustomKey(key, value)
    }

    override fun setProperty(key: String, value: Double) {
        Firebase.crashlytics.setCustomKey(key, value)
    }
}
