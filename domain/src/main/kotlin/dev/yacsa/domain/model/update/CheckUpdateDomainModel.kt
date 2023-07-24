package dev.yacsa.domain.model.update

import dev.yacsa.domain.model.base.BaseDomainModel

data class CheckUpdateDomainModel(
    val isSoftUpdate: Boolean,
    val targetVersion: Int,
    val title: String?,
    val content: String?,
) : BaseDomainModel
