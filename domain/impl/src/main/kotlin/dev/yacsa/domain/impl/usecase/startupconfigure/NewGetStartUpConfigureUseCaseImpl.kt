package dev.yacsa.domain.impl.usecase.startupconfigure

import arrow.core.Either
import dev.yacsa.domain.error.DataError
import dev.yacsa.domain.error.DomainError
import dev.yacsa.domain.error.NoDataError
import dev.yacsa.domain.impl.mapper.NewStartUpConfigureDomainRepoMapper
import dev.yacsa.domain.model.StartUpConfigureDomainModel
import dev.yacsa.domain.usecase.startupconfigure.NewGetStartUpConfigureUseCase
import dev.yacsa.repository.repository.StartUpConfigureRepository
import javax.inject.Inject


class NewGetStartUpConfigureUseCaseImpl @Inject constructor(
    private val startUpConfigureDomainRepoMapper: NewStartUpConfigureDomainRepoMapper,
    private val startUpConfigureRepository: StartUpConfigureRepository,
) : NewGetStartUpConfigureUseCase {

    override suspend fun invoke(): Either<DomainError, StartUpConfigureDomainModel> {
        return try {
            val result = startUpConfigureRepository.getStartUpConfigure()
            if (result == null) {
                Either.Left(NoDataError)
            } else {
                Either.Right(
                    startUpConfigureDomainRepoMapper.toDomain(result)
                )
            }
        } catch (ex: Exception) {
            Either.Left(DataError(ex))
        }

    }
}
