package dev.yacsa.cryptodatastore.impl.source

import dev.yacsa.cryptodatastore.impl.dao.AccessTokenCryptoDataStoreDao
import dev.yacsa.cryptodatastore.model.AccessTokenCryptoDataStoreModel
import dev.yacsa.cryptodatastore.source.AccessTokenCryptoDataStoreSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapLatest
import javax.inject.Inject

class AccessTokenCryptoDataStoreSourceImpl @Inject constructor(
    private val accessTokenCryptoDataStoreDao: AccessTokenCryptoDataStoreDao
) : AccessTokenCryptoDataStoreSource() {

    override fun collectSomeData() {
        accessTokenCryptoDataStoreDao.getData()
    }

    override fun getData(): Flow<AccessTokenCryptoDataStoreModel> {
        return accessTokenCryptoDataStoreDao.getData()
    }

    override suspend fun updateData(value: AccessTokenCryptoDataStoreModel) {
        accessTokenCryptoDataStoreDao.updateData(value)
    }

}