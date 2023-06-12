package dev.yacsa.model.model.featureflag

import dev.yacsa.model.model.base.BaseUiModel

data class FeatureFlagUiModel(
    val key: String,
    val value: Boolean? = null,
) : BaseUiModel
