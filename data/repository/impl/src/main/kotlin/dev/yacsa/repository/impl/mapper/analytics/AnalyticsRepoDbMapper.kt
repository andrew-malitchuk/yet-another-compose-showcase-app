package dev.yacsa.repository.impl.mapper.analytics

import dev.yacsa.database.model.analytics.AnalyticsDbModel
import dev.yacsa.repository.model.analytics.AnalyticsRepoModel
import org.mapstruct.Mapper
import org.mapstruct.NullValueCheckStrategy
import org.mapstruct.NullValuePropertyMappingStrategy

@Mapper(
    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_DEFAULT,
)
interface AnalyticsRepoDbMapper {

    fun toRepo(
        analyticsDbModel: AnalyticsDbModel,
    ): AnalyticsRepoModel

    fun toDb(
        analyticsRepoModel: AnalyticsRepoModel,
    ): AnalyticsDbModel
}
