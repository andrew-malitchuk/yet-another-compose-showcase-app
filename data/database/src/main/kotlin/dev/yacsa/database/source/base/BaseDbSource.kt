package dev.yacsa.database.source.base

import dev.yacsa.database.model.base.BaseDbModel

interface BaseDbSource<T : BaseDbModel> {

    suspend fun get(id: Int): T?
    suspend fun get(): List<T>?
    suspend fun insert(value: T)
    suspend fun insert(values: List<T>)
    suspend fun update(value: T)
    suspend fun update(values: List<T>)
    suspend fun delete(id: Int)
    suspend fun delete(ids: List<Int>)
    suspend fun deleteAll()
}