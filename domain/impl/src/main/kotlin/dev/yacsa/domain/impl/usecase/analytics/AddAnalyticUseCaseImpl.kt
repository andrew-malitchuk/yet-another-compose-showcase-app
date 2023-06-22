package dev.yacsa.domain.impl.usecase.analytics

import arrow.core.Option
import arrow.core.none
import arrow.core.some
import dev.yacsa.domain.error.DataError
import dev.yacsa.domain.error.DomainError
import dev.yacsa.domain.impl.mapper.analytics.AnalyticsDomainRepoMapper
import dev.yacsa.domain.model.analytics.AnalyticsDomainModel
import dev.yacsa.domain.usecase.analytics.AddAnalyticUseCase
import dev.yacsa.repository.repository.AnalyticsRepository
import javax.inject.Inject

class AddAnalyticUseCaseImpl @Inject constructor(
    private val analyticsRepository: AnalyticsRepository,
    private val analyticsDomainRepoMapper: AnalyticsDomainRepoMapper,
) : AddAnalyticUseCase {

    override suspend fun invoke(value: AnalyticsDomainModel): Option<DomainError> {
        return try {
            analyticsRepository.insert(
                analyticsDomainRepoMapper.toRepo(
                    value,
                ),
            )
            none()
        } catch (ex: Exception) {
            DataError(ex).some()
        }
    }
}
