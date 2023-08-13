package dev.yacsa.domain.usecase.analytics

import arrow.core.Option
import dev.yacsa.domain.error.DomainError
import dev.yacsa.domain.model.analytics.AnalyticsDomainModel

interface AddAnalyticUseCase {
    @Throws(Throwable::class)
    suspend operator fun invoke(value: AnalyticsDomainModel): Option<DomainError>
}
