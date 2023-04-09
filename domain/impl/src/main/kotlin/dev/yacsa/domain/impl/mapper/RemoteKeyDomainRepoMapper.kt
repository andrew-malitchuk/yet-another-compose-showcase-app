package dev.yacsa.domain.impl.mapper

import dev.yacsa.domain.impl.mapper.base.DomainRepoMapper
import dev.yacsa.domain.model.RemoteKeyDomainModel
import dev.yacsa.repository.model.RemoteKeyRepoModel
import javax.inject.Inject

class RemoteKeyDomainRepoMapper@Inject constructor() :
    DomainRepoMapper<RemoteKeyDomainModel, RemoteKeyRepoModel>() {
    override fun toDomain(value: RemoteKeyRepoModel): RemoteKeyDomainModel {
        return RemoteKeyDomainModel(
            value.bookId,
            value.prevKey,
            value.currentPage,
            value.nextKey,
            value.createdAt,
        )
    }

    override fun toRepo(value: RemoteKeyDomainModel): RemoteKeyRepoModel {
        return RemoteKeyRepoModel(
            value.bookId,
            value.prevKey,
            value.currentPage,
            value.nextKey,
            value.createdAt,
        )
    }
}
