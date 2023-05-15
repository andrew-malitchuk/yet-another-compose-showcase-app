package dev.yacsa.provider

import dev.yacsa.analytics.event.UserPropertyAnalyticModel
import dev.yacsa.analytics.event.base.EventAnalyticModel

interface AnalyticProvider {
    // TODO: arrow?
    fun log(event: EventAnalyticModel)
    fun setProperty(property: UserPropertyAnalyticModel)
}