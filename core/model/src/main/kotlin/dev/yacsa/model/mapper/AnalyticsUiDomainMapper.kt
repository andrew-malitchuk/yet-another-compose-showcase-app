package dev.yacsa.model.mapper

import dev.yacsa.domain.model.analytics.AnalyticsDomainModel
import dev.yacsa.model.model.analytics.AnalyticsUiModel
import org.mapstruct.Mapper

@Mapper
interface AnalyticsUiDomainMapper {

    fun toDomain(
        analyticsUiModel: AnalyticsUiModel,
    ): AnalyticsDomainModel

    fun toUi(
        analyticsDomainModel: AnalyticsDomainModel,
    ): AnalyticsUiModel
}
