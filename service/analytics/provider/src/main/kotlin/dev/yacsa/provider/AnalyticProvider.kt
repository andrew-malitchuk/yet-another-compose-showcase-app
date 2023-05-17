package dev.yacsa.provider

import dev.yacsa.analytics.event.UserPropertyAnalyticModel
import dev.yacsa.analytics.event.base.EventAnalyticModel

interface AnalyticProvider {
    // TODO: arrow?
    suspend fun log(event: EventAnalyticModel)
    suspend fun setProperty(property: UserPropertyAnalyticModel)
}