package dev.yacsa.analytics.event

import dev.yacsa.analytics.event.base.BaseAnalyticModel

abstract class UserPropertyAnalyticModel : BaseAnalyticModel {

    abstract val key: String
    abstract val value: Any

}