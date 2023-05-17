package dev.yacsa.domain.usecase.analytics

import arrow.core.Either
import dev.yacsa.domain.error.DomainError
import dev.yacsa.domain.model.analytics.AnalyticsDomainModel

interface GetAnalyticsUseCase {
    @Throws(Throwable::class)
    suspend operator fun invoke(): Either<DomainError, List<AnalyticsDomainModel>>
}
