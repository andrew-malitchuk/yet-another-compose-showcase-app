package dev.yacsa.datastore.impl.dao.base

import dev.yacsa.datastore.model.base.BaseDataStoreModel
import kotlinx.coroutines.flow.Flow

abstract class BaseDataStoreDao<T : BaseDataStoreModel> {
    abstract fun getData(): Flow<T>
    abstract suspend fun updateData(value: T)
}
