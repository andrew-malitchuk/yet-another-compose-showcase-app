package dev.yacsa.datastore.impl.dao.base

import android.content.Context
import androidx.datastore.core.DataStore
import dev.yacsa.datastore.model.base.BaseDataStoreModel
import kotlinx.coroutines.flow.Flow

abstract class BaseDataStoreDao<T : BaseDataStoreModel> {
    abstract val Context.dataStore: DataStore<T>
    abstract fun getData(): Flow<T>
    abstract suspend fun updateData(value: T)
}