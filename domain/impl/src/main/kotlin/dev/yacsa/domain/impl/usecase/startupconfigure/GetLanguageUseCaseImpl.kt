package dev.yacsa.domain.impl.usecase.startupconfigure

import arrow.core.Either
import dev.yacsa.domain.error.DataError
import dev.yacsa.domain.error.DomainError
import dev.yacsa.domain.error.NoDataError
import dev.yacsa.domain.usecase.startupconfigure.GetLanguageUseCase
import dev.yacsa.repository.repository.StartUpConfigureRepository
import javax.inject.Inject

class GetLanguageUseCaseImpl @Inject constructor(
    private val startUpConfigureRepository: StartUpConfigureRepository,
) : GetLanguageUseCase {

    override suspend fun invoke(): Either<DomainError, String?> {
        return try {
            val result = startUpConfigureRepository.getStartUpConfigure()
            if (result == null) {
                Either.Left(NoDataError)
            } else {
                Either.Right(
                    result.language,
                )
            }
        } catch (ex: Exception) {
            Either.Left(DataError(ex))
        }
    }
}
