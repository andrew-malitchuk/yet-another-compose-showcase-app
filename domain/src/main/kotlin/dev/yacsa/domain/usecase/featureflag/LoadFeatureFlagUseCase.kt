package dev.yacsa.domain.usecase.featureflag

import arrow.core.Either
import dev.yacsa.domain.error.DomainError
import dev.yacsa.domain.model.featureflag.FeatureFlagDomainModel

interface LoadFeatureFlagUseCase {
    @Throws(Throwable::class)
    suspend operator fun invoke(): Either<DomainError, List<FeatureFlagDomainModel?>>
}
