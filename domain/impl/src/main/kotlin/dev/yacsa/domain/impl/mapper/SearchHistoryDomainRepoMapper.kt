package dev.yacsa.domain.impl.mapper

import dev.yacsa.domain.impl.mapper.base.DomainRepoMapper
import dev.yacsa.domain.model.SearchHistoryDomainModel
import dev.yacsa.repository.model.SearchHistoryRepoModel
import javax.inject.Inject

class SearchHistoryDomainRepoMapper@Inject constructor() :
    DomainRepoMapper<SearchHistoryDomainModel, SearchHistoryRepoModel>() {

    override fun toDomain(value: SearchHistoryRepoModel): SearchHistoryDomainModel {
        return SearchHistoryDomainModel(value.query ?: "")
    }

    override fun toRepo(value: SearchHistoryDomainModel): SearchHistoryRepoModel {
        return SearchHistoryRepoModel(value.query ?: "")
    }
}
