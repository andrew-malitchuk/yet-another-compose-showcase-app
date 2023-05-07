package dev.yacsa.domain.usecase.featureflag

import arrow.core.Option
import dev.yacsa.domain.error.DomainError


interface NewUpdateLocalFeatureFlagUseCase {
    @Throws(Throwable::class)
    suspend operator fun invoke(key: String, value: Boolean?): Option<DomainError>
}
