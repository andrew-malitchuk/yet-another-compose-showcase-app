package dev.yacsa.domain.model

import dev.yacsa.domain.model.base.BaseDomainModel

data class SearchHistoryDomainModel(
    val query:String
) : BaseDomainModel
