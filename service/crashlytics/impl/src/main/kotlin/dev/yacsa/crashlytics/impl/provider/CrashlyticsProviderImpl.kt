package dev.yacsa.crashlytics.impl.provider

import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.ktx.Firebase
import dev.yacsa.crashlytics.provider.CrashlyticsProvider
import javax.inject.Inject

class CrashlyticsProviderImpl @Inject constructor() : CrashlyticsProvider {
    override fun log(throwable: Throwable) {
        Firebase.crashlytics.recordException(throwable)
    }

    override fun log(message: String) {
        Firebase.crashlytics.log(message)
    }

    override fun log(value: Any) {
        Firebase.crashlytics.log(value.toString())
    }
}
