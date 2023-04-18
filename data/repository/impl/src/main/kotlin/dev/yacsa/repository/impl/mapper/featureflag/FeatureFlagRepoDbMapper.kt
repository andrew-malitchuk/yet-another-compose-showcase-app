package dev.yacsa.repository.impl.mapper.featureflag

import dev.yacsa.database.model.FeatureFlagDbModel
import dev.yacsa.repository.impl.mapper.base.RepoDbMapper
import dev.yacsa.repository.model.FeatureFlagRepoModel
import javax.inject.Inject

class FeatureFlagRepoDbMapper @Inject constructor() :
    RepoDbMapper<FeatureFlagRepoModel, FeatureFlagDbModel>() {

    override fun toRepo(value: FeatureFlagDbModel): FeatureFlagRepoModel {
        return FeatureFlagRepoModel(
            value.key,
            value.value,
        )
    }

    override fun toDb(value: FeatureFlagRepoModel): FeatureFlagDbModel {
        return FeatureFlagDbModel(
            0,
            value.key,
            value.value,
        )
    }
}
