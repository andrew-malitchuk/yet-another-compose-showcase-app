package dev.yacsa.database.impl.dao.base

import dev.yacsa.database.model.base.BaseDbModel

//interface BaseDbDao<T : DbModel> {
//
//    suspend fun get(id: Int): T?
//    suspend fun get(): List<T>?
//    suspend fun insert(value: T)
//    suspend fun update(value: T)
//    suspend fun delete(id: Int)
//    suspend fun deleteAll()
//
//}
abstract class BaseDbDao<T : BaseDbModel> {

    abstract suspend fun get(id: Int): T?
    abstract suspend fun get(): List<T>?
    abstract suspend fun insert(value: T)
    abstract suspend fun update(value: T)
    abstract suspend fun delete(id: Int)
    abstract suspend fun deleteAll()

}