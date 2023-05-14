package dev.yacsa.repository.impl.mapper.featureflag

import dev.yacsa.database.model.FeatureFlagDbModel
import dev.yacsa.repository.model.FeatureFlagRepoModel
import org.mapstruct.Mapper
import org.mapstruct.NullValueCheckStrategy
import org.mapstruct.NullValuePropertyMappingStrategy

@Mapper(
    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_DEFAULT,
)
interface NewFeatureFlagRepoDbMapper {

    fun toRepo(
        featureFlagDbModel: FeatureFlagDbModel,
    ): FeatureFlagRepoModel

    fun toDb(
        featureFlagRepoModel: FeatureFlagRepoModel,
    ): FeatureFlagDbModel
}
