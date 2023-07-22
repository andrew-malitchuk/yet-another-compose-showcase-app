package dev.yacsa.repository.impl.impl

import arrow.core.Either
import arrow.core.Option
import arrow.core.none
import arrow.core.some
import dev.yacsa.database.model.FeatureFlagDbModel
import dev.yacsa.database.source.FeatureFlagDbSource
import dev.yacsa.remoteconfig.source.RemoteConfigSource
import dev.yacsa.repository.impl.mapper.featureflag.NewFeatureFlagRepoDbMapper
import dev.yacsa.repository.model.FeatureFlagRepoModel
import dev.yacsa.repository.repository.FeatureFlagRepository
import javax.inject.Inject

class FeatureFlagRepositoryImpl @Inject constructor(
    private val remoteConfigSource: RemoteConfigSource,
    private val featureFlagDbSource: FeatureFlagDbSource,
    private val featureFlagRepoDbMapper: NewFeatureFlagRepoDbMapper,
) : FeatureFlagRepository {

    override suspend fun getFeatureFlagValue(
        key: String,
        debugKey: String,
    ): Either<Exception, Boolean> {
        return remoteConfigSource.getBoolean(key)
    }

    override suspend fun loadFeatureFlag(key: String): Either<Exception, FeatureFlagRepoModel?> {
        return try {
            val result = featureFlagDbSource.getKey(key)
            Either.Right(result?.let { featureFlagRepoDbMapper.toRepo(it) })
        } catch (ex: Exception) {
            Either.Left(ex)
        }
    }

    override suspend fun loadFeatureFlags(): Either<Exception, List<FeatureFlagRepoModel?>> {
        return try {
            val result = featureFlagDbSource.get() ?: emptyList()
            val temp = result.map {
                featureFlagRepoDbMapper.toRepo(it)
            }
            Either.Right(temp)
        } catch (ex: Exception) {
            Either.Left(ex)
        }
    }

    override suspend fun updateLocalFeatureFlag(featureFlagRepoModel: FeatureFlagRepoModel): Option<Exception> {
        return try {
            featureFlagDbSource.update(featureFlagRepoDbMapper.toDb(featureFlagRepoModel))
            none()
        } catch (ex: Exception) {
            ex.some()
        }
    }

    override suspend fun updateKey(key: String): Option<Exception> {
        return try {
            featureFlagDbSource.insert(
                FeatureFlagDbModel(
                    0,
                    key,
                ),
            )
            none()
        } catch (ex: Exception) {
            ex.some()
        }
    }

}
