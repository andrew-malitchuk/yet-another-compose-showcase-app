package dev.yacsa.domain.impl.usecase.featureflag

import arrow.core.Either
import dev.yacsa.domain.error.DataError
import dev.yacsa.domain.error.DomainError
import dev.yacsa.domain.impl.mapper.featureflag.FeatureFlagDomainRepoMapper
import dev.yacsa.domain.model.featureflag.FeatureFlagDomainModel
import dev.yacsa.domain.usecase.featureflag.LoadFeatureFlagUseCase
import dev.yacsa.repository.repository.FeatureFlagRepository
import javax.inject.Inject

class LoadFeatureFlagUseCaseImpl @Inject constructor(
    private val featureFlagRepository: FeatureFlagRepository,
    private val featureFlagDomainRepoMapper: FeatureFlagDomainRepoMapper
) : LoadFeatureFlagUseCase {

    override suspend fun invoke(): Either<DomainError, List<FeatureFlagDomainModel?>> {
        return try{
            val result = featureFlagRepository.loadFeatureFlags()
            result.fold(
                {
                    Either.Left(DataError(it))
                },
                {result->
                    Either.Right(
                        result.map{
                            it?.let { it1 -> featureFlagDomainRepoMapper.toDomain(it1) }
                        }
                    )
                }
            )

        }catch (ex:Exception){
            Either.Left(DataError(ex))
        }
    }

}
