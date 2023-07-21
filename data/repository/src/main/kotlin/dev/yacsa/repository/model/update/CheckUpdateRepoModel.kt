package dev.yacsa.repository.model.update

import dev.yacsa.repository.model.base.BaseRepoModel

data class CheckUpdateRepoModel(
    val isSoftUpdate: Boolean,
    val targetVersion: Int,
    val title: String?,
    val content: String?
) : BaseRepoModel