package dev.yacsa.analytics.event

import dev.yacsa.analytics.event.base.EventAnalyticModel

abstract class ContentViewAnalyticModel : EventAnalyticModel {
    abstract val viewName:String
}