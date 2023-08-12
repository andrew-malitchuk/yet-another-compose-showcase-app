package dev.yacsa.model.mapper

import dev.yacsa.domain.model.featureflag.FeatureFlagDomainModel
import dev.yacsa.model.model.featureflag.FeatureFlagUiModel
import org.mapstruct.Mapper

@Mapper
interface FeatureFlagDomainRepoMapper {

    fun toUi(
        featureFlagDomainModel: FeatureFlagDomainModel,
    ): FeatureFlagUiModel

    fun toDomain(
        featureFlagUiModel: FeatureFlagUiModel,
    ): FeatureFlagDomainModel
}
