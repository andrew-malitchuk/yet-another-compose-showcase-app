package dev.yacsa.domain.impl.usecase.theme

import arrow.core.Option
import arrow.core.none
import arrow.core.some
import dev.yacsa.domain.error.DataError
import dev.yacsa.domain.error.DomainError
import dev.yacsa.domain.model.theme.ThemeDomainModel
import dev.yacsa.domain.usecase.theme.SetThemeUseCase
import dev.yacsa.repository.model.theme.ThemeRepoModel
import dev.yacsa.repository.repository.StartUpConfigureRepository
import javax.inject.Inject

class SetThemeUseCaseImpl @Inject constructor(
    private val startUpConfigureRepository: StartUpConfigureRepository
) : SetThemeUseCase {

    // Raise doesnt work
    // https://slack-chats.kotlinlang.org/t/10974556/have-anyone-experienced-this-error-it-happened-right-after-e
    override suspend fun invoke(themeDomainModel: ThemeDomainModel): Option<DomainError> {

            return try {
                startUpConfigureRepository.setTheme(ThemeRepoModel.valueOf(themeDomainModel.name))
                none()
            } catch (ex: Exception) {
                DataError(ex).some()
            }
    }

}
