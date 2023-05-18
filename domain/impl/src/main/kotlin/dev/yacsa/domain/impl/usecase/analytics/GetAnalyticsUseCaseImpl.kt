package dev.yacsa.domain.impl.usecase.analytics

import arrow.core.Either
import dev.yacsa.domain.error.DataError
import dev.yacsa.domain.error.DomainError
import dev.yacsa.domain.impl.mapper.analytics.AnalyticsDomainRepoMapper
import dev.yacsa.domain.model.analytics.AnalyticsDomainModel
import dev.yacsa.domain.usecase.analytics.GetAnalyticsUseCase
import dev.yacsa.repository.repository.AnalyticsRepository
import javax.inject.Inject

class GetAnalyticsUseCaseImpl @Inject constructor(
    private val analyticsRepository: AnalyticsRepository,
    private val analyticsDomainRepoMapper: AnalyticsDomainRepoMapper,
) : GetAnalyticsUseCase {

    override suspend fun invoke(): Either<DomainError, List<AnalyticsDomainModel>> {
        return try {
            val result = analyticsRepository.get().map(analyticsDomainRepoMapper::toDomain)
            Either.Right(result)
        } catch (ex: Exception) {
            Either.Left(DataError(ex))
        }
    }

}
