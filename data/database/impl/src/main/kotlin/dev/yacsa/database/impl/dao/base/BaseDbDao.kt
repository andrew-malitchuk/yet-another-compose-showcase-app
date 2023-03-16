package dev.yacsa.database.impl.dao.base

import dev.yacsa.database.model.base.DbModel

interface BaseDbDao<T : DbModel> {

    suspend fun get(id: Int): T?
    suspend fun get(): List<T>?
    suspend fun insert(value: T)
    suspend fun update(value: T)
    suspend fun delete(id: Int)
    suspend fun deleteAll()

}