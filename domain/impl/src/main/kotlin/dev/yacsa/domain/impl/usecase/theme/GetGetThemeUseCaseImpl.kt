package dev.yacsa.domain.impl.usecase.theme

import arrow.core.Either
import dev.yacsa.domain.error.DataError
import dev.yacsa.domain.error.DomainError
import dev.yacsa.domain.model.theme.ThemeDomainModel
import dev.yacsa.domain.usecase.theme.GetThemeUseCase
import dev.yacsa.repository.repository.StartUpConfigureRepository
import javax.inject.Inject


class GetGetThemeUseCaseImpl @Inject constructor(
    private val startUpConfigureRepository: StartUpConfigureRepository
) : GetThemeUseCase {

    override suspend fun invoke(): Either<DomainError, ThemeDomainModel> {
        return try {
            val result = ThemeDomainModel.valueOf(startUpConfigureRepository.getTheme().name)
            Either.Right(result)
        } catch (ex: Exception) {
            Either.Left(DataError(ex))
        }
    }

}
