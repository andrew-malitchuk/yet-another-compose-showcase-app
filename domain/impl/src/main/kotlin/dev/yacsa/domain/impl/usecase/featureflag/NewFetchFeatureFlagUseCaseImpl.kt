package dev.yacsa.domain.impl.usecase.featureflag

import arrow.core.Either
import dev.yacsa.domain.error.DataError
import dev.yacsa.domain.error.DomainError
import dev.yacsa.domain.error.NoDataError
import dev.yacsa.domain.usecase.featureflag.NewFetchFeatureFlagUseCase
import dev.yacsa.repository.repository.FeatureFlagRepository
import java.io.IOException
import javax.inject.Inject

class NewFetchFeatureFlagUseCaseImpl @Inject constructor(
    private val featureFlagRepository: FeatureFlagRepository,
) : NewFetchFeatureFlagUseCase {

    override suspend fun invoke(key: String): Either<DomainError, Boolean> {
        return try {
            val localKeyValue = featureFlagRepository.loadFeatureFlag(key).getOrNull()
            // TODO: fix
            val remoteKeyValue = featureFlagRepository.getFeatureFlagValue(key, key).getOrNull()
            if (localKeyValue?.value != null) {
                Either.Right(localKeyValue.value ?: remoteKeyValue ?: false)
            } else {
                Either.Right(remoteKeyValue ?: false)
            }
        } catch (ex: Exception) {
            when (ex) {
                is IOException -> {
                    try {
                        val localKeyValue = featureFlagRepository.loadFeatureFlag(key).getOrNull()
                        if (localKeyValue == null) {
                            Either.Left(NoDataError)
                        } else {
                            Either.Right(localKeyValue.value ?: false)
                        }

                    } catch (ex: Exception) {
                        Either.Left(DataError(ex))
                    }
                }

                else -> {
                    Either.Left(DataError(ex))
                }
            }
        }
    }
}