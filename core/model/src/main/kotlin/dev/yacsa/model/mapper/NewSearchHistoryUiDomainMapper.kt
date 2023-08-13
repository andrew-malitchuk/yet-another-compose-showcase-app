package dev.yacsa.model.mapper

import dev.yacsa.domain.model.SearchHistoryDomainModel
import dev.yacsa.model.model.SearchHistoryUiModel
import org.mapstruct.Mapper

@Mapper
interface NewSearchHistoryUiDomainMapper {

    fun toDomain(
        searchHistoryUiModel: SearchHistoryUiModel,
    ): SearchHistoryDomainModel

    fun toUi(
        searchHistoryDomainModel: SearchHistoryDomainModel,
    ): SearchHistoryUiModel
}
