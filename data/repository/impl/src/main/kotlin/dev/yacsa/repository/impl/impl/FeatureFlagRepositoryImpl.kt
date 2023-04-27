package dev.yacsa.repository.impl.impl

import dev.yacsa.database.model.FeatureFlagDbModel
import dev.yacsa.database.source.FeatureFlagDbSource
import dev.yacsa.remoteconfig.source.RemoteConfigSource
import dev.yacsa.repository.impl.mapper.featureflag.FeatureFlagRepoDbMapper
import dev.yacsa.repository.model.FeatureFlagRepoModel
import dev.yacsa.repository.repository.FeatureFlagRepository
import javax.inject.Inject

class FeatureFlagRepositoryImpl @Inject constructor(
    private val remoteConfigSource: RemoteConfigSource,
    private val featureFlagDbSource: FeatureFlagDbSource,
    private val featureFlagRepoDbMapper: FeatureFlagRepoDbMapper,
) : FeatureFlagRepository {

    override suspend fun getFeatureFlagValue(key: String, debugKey: String): Result<Boolean> {
        return remoteConfigSource.getBoolean(key)
    }

    override suspend fun loadFeatureFlag(key: String): Result<FeatureFlagRepoModel> {
        return try {
            val result = featureFlagDbSource.getKey(key)
            if (result == null) {
                Result.failure(NoSuchElementException())
            } else {
                Result.success(featureFlagRepoDbMapper.toRepo(result))
            }
        } catch (ex: Exception) {
            Result.failure(ex)
        }
    }

    override suspend fun loadFeatureFlags(): Result<List<FeatureFlagRepoModel>> {
        return try {
            val result = featureFlagDbSource.get()
            if (result == null) {
                Result.failure(NoSuchElementException())
            } else {
                Result.success(result.map(featureFlagRepoDbMapper::toRepo))
            }
        } catch (ex: Exception) {
            Result.failure(ex)
        }
    }

    override suspend fun updateLocalFeatureFlag(featureFlagRepoModel: FeatureFlagRepoModel) {
        featureFlagDbSource.update(featureFlagRepoDbMapper.toDb(featureFlagRepoModel))
    }

    override suspend fun updateKey(key: String) {
        featureFlagDbSource.insert(
            FeatureFlagDbModel(
                0,
                key,
            ),
        )
    }
}