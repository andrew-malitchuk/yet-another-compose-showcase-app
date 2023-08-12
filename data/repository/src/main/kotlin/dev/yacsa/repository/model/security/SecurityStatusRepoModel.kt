package dev.yacsa.repository.model.security

import dev.yacsa.repository.model.base.BaseRepoModel

data class SecurityStatusRepoModel(
    val isRooted: Boolean?,
    val somePiracyStuff: Boolean?,
    val isIntegrityOk: Boolean?,
) : BaseRepoModel
