package dev.yacsa.domain.impl.mapper

import dev.yacsa.domain.model.RemoteKeyDomainModel
import dev.yacsa.repository.model.RemoteKeyRepoModel
import org.mapstruct.Mapper

@Mapper
interface NewRemoteKeyDomainRepoMapper {

    fun toRepo(
        remoteKeyDomainModel: RemoteKeyDomainModel,
    ): RemoteKeyRepoModel

    fun toDomain(
        remoteKeyRepoModel: RemoteKeyRepoModel,
    ): RemoteKeyDomainModel
}
