package dev.yacsa.repository.impl.mapper.search

import dev.yacsa.database.model.SearchHistoryDbModel
import dev.yacsa.repository.model.SearchHistoryRepoModel
import org.mapstruct.Mapper
import org.mapstruct.NullValueCheckStrategy
import org.mapstruct.NullValuePropertyMappingStrategy

@Mapper(
    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_DEFAULT,
)
interface NewSearchHistoryRepoDbMapper {

    fun toRepo(
        searchHistoryDbModel: SearchHistoryDbModel
    ): SearchHistoryRepoModel

    fun toDb(
        searchHistoryRepoModel: SearchHistoryRepoModel
    ): SearchHistoryDbModel

}