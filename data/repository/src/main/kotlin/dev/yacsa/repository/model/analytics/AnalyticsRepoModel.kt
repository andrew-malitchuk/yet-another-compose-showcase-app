package dev.yacsa.repository.model.analytics

import dev.yacsa.repository.model.base.BaseRepoModel

data class AnalyticsRepoModel(
    val id: Int,
    val key: String?,
    var value: String?,
) : BaseRepoModel
