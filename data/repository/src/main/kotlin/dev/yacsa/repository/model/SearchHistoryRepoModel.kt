package dev.yacsa.repository.model

import dev.yacsa.repository.model.base.BaseRepoModel

data class SearchHistoryRepoModel(
    val query: String?,
) : BaseRepoModel