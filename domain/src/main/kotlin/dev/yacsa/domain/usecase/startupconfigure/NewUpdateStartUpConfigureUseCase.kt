package dev.yacsa.domain.usecase.startupconfigure

import arrow.core.Option
import dev.yacsa.domain.error.DataError
import dev.yacsa.domain.model.StartUpConfigureDomainModel

interface NewUpdateStartUpConfigureUseCase {
    @Throws(Throwable::class)
    suspend operator fun invoke(value: StartUpConfigureDomainModel): Option<DataError>
}
