package dev.yacsa.domain.impl.mapper.featureflag

import dev.yacsa.domain.model.featureflag.FeatureFlagDomainModel
import dev.yacsa.repository.model.FeatureFlagRepoModel
import org.mapstruct.Mapper

@Mapper
interface FeatureFlagDomainRepoMapper {

    fun toRepo(
        featureFlagDomainModel: FeatureFlagDomainModel,
    ): FeatureFlagRepoModel

    fun toDomain(
        featureFlagRepoModel: FeatureFlagRepoModel,
    ): FeatureFlagDomainModel
}
