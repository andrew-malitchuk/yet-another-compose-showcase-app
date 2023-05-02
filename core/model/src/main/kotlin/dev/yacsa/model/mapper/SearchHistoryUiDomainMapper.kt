package dev.yacsa.model.mapper

import dev.yacsa.domain.model.SearchHistoryDomainModel
import dev.yacsa.model.mapper.base.UiDomainMapper
import dev.yacsa.model.model.SearchHistoryUiModel
import javax.inject.Inject

class SearchHistoryUiDomainMapper @Inject constructor() :
    UiDomainMapper<SearchHistoryUiModel, SearchHistoryDomainModel>() {

    override fun toUi(value: SearchHistoryDomainModel): SearchHistoryUiModel {
        return SearchHistoryUiModel(value.query)
    }

    override fun toDomain(value: SearchHistoryUiModel): SearchHistoryDomainModel {
        return SearchHistoryDomainModel(value.query ?: "")
    }
}
