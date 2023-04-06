package dev.yacsa.domain.usecase

import dev.yacsa.domain.model.RemoteKeyDomainModel


interface AddAllRemoteKeysUseCase {
    @Throws(Throwable::class)
    suspend operator fun invoke(remoteKeys: List<RemoteKeyDomainModel?>)
}