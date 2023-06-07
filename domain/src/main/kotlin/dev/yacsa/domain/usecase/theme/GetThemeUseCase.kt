package dev.yacsa.domain.usecase.theme

import arrow.core.Either
import dev.yacsa.domain.error.DomainError
import dev.yacsa.domain.model.theme.ThemeDomainModel

interface GetThemeUseCase {
    @Throws(Throwable::class)
    suspend operator fun invoke(): Either<DomainError, ThemeDomainModel>
}