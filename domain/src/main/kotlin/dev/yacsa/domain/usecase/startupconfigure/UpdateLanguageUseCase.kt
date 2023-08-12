package dev.yacsa.domain.usecase.startupconfigure

import arrow.core.Option
import dev.yacsa.domain.error.DataError

interface UpdateLanguageUseCase {
    @Throws(Throwable::class)
    suspend operator fun invoke(value: String): Option<DataError>
}
