package dev.yacsa.dispatcher

import dev.yacsa.analytics.event.UserPropertyAnalyticModel
import dev.yacsa.analytics.event.base.EventAnalyticModel

interface AnalyticDispatcher {
    fun setUserProperty(property: UserPropertyAnalyticModel)
    fun sendEvent(event: EventAnalyticModel)
}