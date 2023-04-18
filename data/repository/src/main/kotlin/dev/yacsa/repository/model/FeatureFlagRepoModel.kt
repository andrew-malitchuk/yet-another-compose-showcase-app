package dev.yacsa.repository.model

import dev.yacsa.repository.model.base.BaseRepoModel

data class FeatureFlagRepoModel(
    val key: String,
    val value: Boolean? = null,
) : BaseRepoModel
