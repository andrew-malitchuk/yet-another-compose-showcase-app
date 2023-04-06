package dev.yacsa.database.source

import dev.yacsa.database.model.RemoteKeyDbModel

interface RemoveKeyDbSource {
    suspend fun insertAll(remoteKey: List<RemoteKeyDbModel>)
    suspend fun remoteId(bookId: Long): RemoteKeyDbModel?
    suspend fun clearRemoteKeys()
}