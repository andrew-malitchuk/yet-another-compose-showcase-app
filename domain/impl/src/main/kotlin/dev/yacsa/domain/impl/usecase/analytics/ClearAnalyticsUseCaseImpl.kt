package dev.yacsa.domain.impl.usecase.analytics

import arrow.core.Option
import arrow.core.none
import arrow.core.some
import dev.yacsa.domain.error.DataError
import dev.yacsa.domain.error.DomainError
import dev.yacsa.domain.impl.mapper.analytics.AnalyticsDomainRepoMapper
import dev.yacsa.domain.usecase.analytics.ClearAnalyticsUseCase
import dev.yacsa.repository.repository.AnalyticsRepository
import javax.inject.Inject

class ClearAnalyticsUseCaseImpl @Inject constructor(
    private val analyticsRepository: AnalyticsRepository,
    private val analyticsDomainRepoMapper: AnalyticsDomainRepoMapper,
) : ClearAnalyticsUseCase {

    override suspend fun invoke(): Option<DomainError> {
        return try {
            analyticsRepository.deleteAll()
            none()
        } catch (ex: Exception) {
            DataError(ex).some()
        }
    }
}
