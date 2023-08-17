package dev.yacsa.domain.impl.mapper.analytics

import dev.yacsa.domain.model.analytics.AnalyticsDomainModel
import dev.yacsa.repository.model.analytics.AnalyticsRepoModel
import org.mapstruct.Mapper

@Mapper
interface AnalyticsDomainRepoMapper {

    fun toRepo(
        analyticsDomainModel: AnalyticsDomainModel,
    ): AnalyticsRepoModel

    fun toDomain(
        analyticsRepoModel: AnalyticsRepoModel,
    ): AnalyticsDomainModel
}
