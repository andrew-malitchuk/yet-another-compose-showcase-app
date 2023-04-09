package dev.yacsa.domain.usecase

import dev.yacsa.domain.model.RemoteKeyDomainModel

interface GetRemoteKeyByBookId {
    @Throws(Throwable::class)
    operator fun invoke(bookId: Int): RemoteKeyDomainModel
}
