package dev.yacsa.domain.model.security

import dev.yacsa.domain.model.base.BaseDomainModel

data class SecurityStatusDomainModel(
    val isRooted: Boolean?,
    val somePiracyStuff: Boolean?,
    val isIntegrityOk: Boolean?,
) : BaseDomainModel
