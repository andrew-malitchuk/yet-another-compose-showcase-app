package dev.yacsa.cryptodatastore.source.base

import dev.yacsa.cryptodatastore.model.base.BaseCryptoDataStoreModel
import kotlinx.coroutines.flow.Flow

abstract class BaseCryptoDataStoreSource<T : BaseCryptoDataStoreModel> {
    abstract fun getData(): Flow<T>
    abstract suspend fun updateData(value: T)
}