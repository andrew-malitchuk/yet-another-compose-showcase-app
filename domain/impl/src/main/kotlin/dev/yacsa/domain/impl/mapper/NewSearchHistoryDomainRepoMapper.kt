package dev.yacsa.domain.impl.mapper

import dev.yacsa.domain.model.SearchHistoryDomainModel
import dev.yacsa.repository.model.SearchHistoryRepoModel
import org.mapstruct.Mapper


@Mapper
interface NewSearchHistoryDomainRepoMapper {

    fun toRepo(
        searchHistoryDomainModel: SearchHistoryDomainModel
    ): SearchHistoryRepoModel

    fun toDomain(
        searchHistoryRepoModel: SearchHistoryRepoModel
    ): SearchHistoryDomainModel

}