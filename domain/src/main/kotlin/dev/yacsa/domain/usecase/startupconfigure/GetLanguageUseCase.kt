package dev.yacsa.domain.usecase.startupconfigure

import arrow.core.Either
import dev.yacsa.domain.error.DomainError

interface GetLanguageUseCase {
    @Throws(Throwable::class)
    suspend operator fun invoke(): Either<DomainError, String?>
}
