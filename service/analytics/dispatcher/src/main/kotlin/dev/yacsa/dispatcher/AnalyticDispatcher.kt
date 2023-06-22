package dev.yacsa.dispatcher

import dev.yacsa.analytics.event.UserPropertyAnalyticModel
import dev.yacsa.analytics.event.base.EventAnalyticModel

interface AnalyticDispatcher {
    suspend fun setUserProperty(property: UserPropertyAnalyticModel)
    suspend fun sendEvent(event: EventAnalyticModel)
}
