package dev.yacsa.domain.model.featureflag

import dev.yacsa.domain.model.base.BaseDomainModel

data class FeatureFlagDomainModel(
    val key: String,
    val value: Boolean? = null,
) : BaseDomainModel
