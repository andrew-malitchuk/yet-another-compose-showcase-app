package dev.yacsa.analytics.event

import dev.yacsa.analytics.event.base.EventAnalyticModel

abstract class CustomAnalyticModel : EventAnalyticModel {
    abstract var eventName: String
    abstract fun getParameters(): Map<String, Any>
}
