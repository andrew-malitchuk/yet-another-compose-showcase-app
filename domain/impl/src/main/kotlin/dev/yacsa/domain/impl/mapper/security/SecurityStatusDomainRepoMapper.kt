package dev.yacsa.domain.impl.mapper.security

import dev.yacsa.domain.model.security.SecurityStatusDomainModel
import dev.yacsa.repository.model.security.SecurityStatusRepoModel
import org.mapstruct.Mapper

@Mapper
interface SecurityStatusDomainRepoMapper {

    fun toRepo(
        securityStatusDomainModel: SecurityStatusDomainModel,
    ): SecurityStatusRepoModel

    fun toDomain(
        securityStatusRepoModel: SecurityStatusRepoModel,
    ): SecurityStatusDomainModel
}
