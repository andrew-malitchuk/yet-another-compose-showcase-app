package dev.yacsa.provider.firebase

import android.os.Bundle
import androidx.core.os.bundleOf
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import dev.yacsa.analytics.event.ContentViewAnalyticModel
import dev.yacsa.analytics.event.CustomAnalyticModel
import dev.yacsa.analytics.event.UserPropertyAnalyticModel
import dev.yacsa.analytics.event.base.EventAnalyticModel
import dev.yacsa.provider.AnalyticProvider
import javax.inject.Inject

class FirebaseAnalyticProvider @Inject constructor() : AnalyticProvider {

    private val firebaseAnalytics by lazy {
        Firebase.analytics
    }

    override fun log(event: EventAnalyticModel) {
        when (event) {
            is ContentViewAnalyticModel -> {
                firebaseAnalytics.logEvent(
                    FirebaseAnalytics.Event.SCREEN_VIEW,
                    bundleOf(
                        FirebaseAnalytics.Param.SCREEN_NAME to event.viewName
                    )
                )
            }

            is CustomAnalyticModel -> {
                firebaseAnalytics.logEvent(
                    event.eventName,
                    event.getParameters().toBundle()
                )
            }
        }
    }

    override fun setProperty(property: UserPropertyAnalyticModel) {
        firebaseAnalytics.setUserProperty(
            property.key,
            property.value.toString()
        )
    }

    private fun Map<String, Any?>.toBundle(): Bundle = bundleOf(*this.toList().toTypedArray())


}