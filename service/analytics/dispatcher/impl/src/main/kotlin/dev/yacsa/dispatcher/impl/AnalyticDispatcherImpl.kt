package dev.yacsa.dispatcher.impl

import dev.yacsa.analytics.event.UserPropertyAnalyticModel
import dev.yacsa.analytics.event.base.EventAnalyticModel
import dev.yacsa.dispatcher.AnalyticDispatcher
import dev.yacsa.provider.AnalyticProvider
import javax.inject.Inject

class AnalyticDispatcherImpl @Inject constructor(
    private val analyticProviders: ArrayList<AnalyticProvider>
) : AnalyticDispatcher {


    override fun setUserProperty(property: UserPropertyAnalyticModel) {
        analyticProviders.forEach {
            it.setProperty(property)
        }
    }

    override fun sendEvent(event: EventAnalyticModel) {
        analyticProviders.forEach {
            it.log(event)
        }
    }

}