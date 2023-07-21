package dev.yacsa.domain.impl.usecase.update

import arrow.core.Either
import dev.yacsa.domain.error.DataError
import dev.yacsa.domain.error.DomainError
import dev.yacsa.domain.impl.mapper.featureflag.FeatureFlagDomainRepoMapper
import dev.yacsa.domain.model.update.CheckUpdateDomainModel
import dev.yacsa.domain.usecase.update.CheckUpdateUseCase
import dev.yacsa.repository.repository.FeatureFlagRepository
import javax.inject.Inject

class CheckUpdateUseCaseImpl @Inject constructor(
    private val featureFlagRepository: FeatureFlagRepository,
    private val featureFlagDomainRepoMapper: FeatureFlagDomainRepoMapper,
) : CheckUpdateUseCase {

    override suspend fun invoke(): Either<DomainError, List<CheckUpdateDomainModel?>> {
        return try {
            val result = featureFlagRepository.loadFeatureFlags()
            result.fold(
                {
                    Either.Left(DataError(it))
                },
                { result ->
                    Either.Right(
                        result.map {
                            it?.let { it1 -> featureFlagDomainRepoMapper.toDomain(it1) }
                        },
                    )
                },
            )
        } catch (ex: Exception) {
            Either.Left(DataError(ex))
        }
        Either.Left(DataError(ex))
    }
}