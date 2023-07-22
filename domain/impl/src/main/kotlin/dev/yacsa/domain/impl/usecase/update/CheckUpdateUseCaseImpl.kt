package dev.yacsa.domain.impl.usecase.update

import arrow.core.Either
import dev.yacsa.domain.error.DataError
import dev.yacsa.domain.error.DomainError
import dev.yacsa.domain.impl.mapper.update.CheckUpdateDomainRepoMapper
import dev.yacsa.domain.model.update.CheckUpdateDomainModel
import dev.yacsa.domain.usecase.update.CheckUpdateUseCase
import dev.yacsa.repository.repository.CheckUpdateRepository
import javax.inject.Inject

class CheckUpdateUseCaseImpl @Inject constructor(
    private val checkUpdateRepository: CheckUpdateRepository,
    private val checkUpdateDomainRepoMapper: CheckUpdateDomainRepoMapper,
) : CheckUpdateUseCase {

    override suspend fun invoke(): Either<DomainError, CheckUpdateDomainModel> {
        return try {
            val result = checkUpdateRepository.checkUpdate("1")
            result.fold(
                {
                    Either.Left(DataError(it))
                },
                { result ->
                    val output = checkUpdateDomainRepoMapper.toDomain(result)
                    Either.Right(output )
                },
            )
        } catch (ex: Exception) {
            Either.Left(DataError(ex))
        }
    }
}