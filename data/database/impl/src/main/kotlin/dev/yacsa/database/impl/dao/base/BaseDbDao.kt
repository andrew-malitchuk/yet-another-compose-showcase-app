package dev.yacsa.database.impl.dao.base

import dev.yacsa.database.model.base.BaseDbModel

abstract class BaseDbDao<T : BaseDbModel> {
    abstract suspend fun get(id: Int): T?
    abstract suspend fun get(): List<T>?
    abstract suspend fun insert(value: T)
    abstract suspend fun update(value: T)
    abstract suspend fun delete(id: Int)
    abstract suspend fun deleteAll()
}
