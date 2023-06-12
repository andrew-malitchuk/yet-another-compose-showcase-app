package dev.yacsa.repository.repository

import arrow.core.Either
import arrow.core.Option
import dev.yacsa.repository.model.FeatureFlagRepoModel

interface FeatureFlagRepository {
    suspend fun getFeatureFlagValue(key: String, debugKey: String): Either<Exception, Boolean>
    suspend fun loadFeatureFlag(key: String):  Either<Exception, FeatureFlagRepoModel?>
    suspend fun loadFeatureFlags():  Either<Exception, List<FeatureFlagRepoModel?>>
    suspend fun updateLocalFeatureFlag(featureFlagRepoModel: FeatureFlagRepoModel): Option<Exception>
    suspend fun updateKey(key: String): Option<Exception>
}
