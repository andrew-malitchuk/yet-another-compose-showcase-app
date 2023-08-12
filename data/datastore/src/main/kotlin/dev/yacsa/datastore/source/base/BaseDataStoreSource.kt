package dev.yacsa.datastore.source.base

import dev.yacsa.datastore.model.base.BaseDataStoreModel
import kotlinx.coroutines.flow.Flow

abstract class BaseDataStoreSource<T : BaseDataStoreModel> {
    abstract fun getData(): Flow<T>
    abstract suspend fun updateData(value: T)
}
