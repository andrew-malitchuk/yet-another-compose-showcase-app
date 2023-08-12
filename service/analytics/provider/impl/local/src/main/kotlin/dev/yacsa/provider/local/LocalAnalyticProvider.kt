package dev.yacsa.provider.local

import dev.yacsa.analytics.event.ContentViewAnalyticModel
import dev.yacsa.analytics.event.CustomAnalyticModel
import dev.yacsa.analytics.event.UserPropertyAnalyticModel
import dev.yacsa.analytics.event.base.EventAnalyticModel
import dev.yacsa.provider.AnalyticProvider
import logcat.logcat
import javax.inject.Inject

class LocalAnalyticProvider @Inject constructor() : AnalyticProvider {

    override suspend fun log(event: EventAnalyticModel) {
        when (event) {
            is ContentViewAnalyticModel -> {
                logcat { "ContentViewAnalyticModel: ${event.viewName}" }
            }

            is CustomAnalyticModel -> {
                logcat { "CustomAnalyticModel: $event" }
            }
        }
    }

    override suspend fun setProperty(property: UserPropertyAnalyticModel) {
        logcat { "CustomAnalyticModel: {${property.key} : ${property.value}}" }
    }
}
