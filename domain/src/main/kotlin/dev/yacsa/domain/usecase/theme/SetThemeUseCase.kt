package dev.yacsa.domain.usecase.theme

import arrow.core.Option
import dev.yacsa.domain.error.DomainError
import dev.yacsa.domain.model.theme.ThemeDomainModel

interface SetThemeUseCase {
    @Throws(Throwable::class)
    suspend operator fun invoke(themeDomainModel: ThemeDomainModel): Option<DomainError>
}
