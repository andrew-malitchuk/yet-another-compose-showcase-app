package dev.yacsa.domain.model.analytics

import dev.yacsa.domain.model.base.BaseDomainModel

data class AnalyticsDomainModel(
    val key: String?,
    var value: String?,
) : BaseDomainModel
