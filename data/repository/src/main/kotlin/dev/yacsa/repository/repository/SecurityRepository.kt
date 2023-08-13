package dev.yacsa.repository.repository

import dev.yacsa.repository.model.security.SecurityStatusRepoModel

interface SecurityRepository {
    suspend fun getSecurityStatus(): SecurityStatusRepoModel
}
