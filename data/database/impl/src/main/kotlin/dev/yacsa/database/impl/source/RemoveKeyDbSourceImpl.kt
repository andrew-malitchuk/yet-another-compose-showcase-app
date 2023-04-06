package dev.yacsa.database.impl.source

import dev.yacsa.database.impl.dao.RemoteKeyDao
import dev.yacsa.database.model.RemoteKeyDbModel
import dev.yacsa.database.source.RemoveKeyDbSource
import javax.inject.Inject

class RemoveKeyDbSourceImpl @Inject constructor(
    private val remoteKeyDao: RemoteKeyDao
) : RemoveKeyDbSource {

    override suspend fun insertAll(remoteKey: List<RemoteKeyDbModel>) {
        remoteKeyDao.insertAll(remoteKey)
    }

    override suspend fun remoteId(bookId: Long): RemoteKeyDbModel? {
        return remoteKeyDao.remoteId(bookId)
    }

    override suspend fun clearRemoteKeys() {
        remoteKeyDao.clearRemoteKeys()
    }

}