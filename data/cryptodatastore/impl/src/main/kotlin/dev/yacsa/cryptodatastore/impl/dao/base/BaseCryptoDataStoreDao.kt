package dev.yacsa.cryptodatastore.impl.dao.base

import android.content.Context
import androidx.datastore.core.DataStore
import dev.yacsa.cryptodatastore.model.base.BaseCryptoDataStoreModel
import kotlinx.coroutines.flow.Flow

abstract class BaseCryptoDataStoreDao<T : BaseCryptoDataStoreModel> {
    abstract val Context.dataStore: DataStore<T>
    abstract fun getData(): Flow<T>
    abstract suspend fun updateData(value: T)
}