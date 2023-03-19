package dev.yacsa.database.impl.source

import dev.yacsa.database.impl.dao.BookDbDao
import dev.yacsa.database.model.BookDbModel
import dev.yacsa.database.source.BookDbSource
import javax.inject.Inject

class BookDbSourceImpl @Inject constructor(
    private val booksDao: BookDbDao
): BookDbSource {

    override suspend fun get(id: Int): BookDbModel? {
        return booksDao.get(id)
    }

    override suspend fun get(): List<BookDbModel>? {
        return booksDao.get()
    }

    override suspend fun insert(value: BookDbModel) {
        return booksDao.insert(value)
    }

    override suspend fun insert(values: List<BookDbModel>) {
        values.forEach{
            booksDao.insert(it)
        }
    }

    override suspend fun update(value: BookDbModel) {
        booksDao.update(value)
    }

    override suspend fun update(values: List<BookDbModel>) {
        values.forEach {
            booksDao.update(it)
        }
    }

    override suspend fun delete(id: Int) {
        return booksDao.delete(id)
    }

    override suspend fun delete(ids: List<Int>) {
        ids.forEach {
            booksDao.delete(it)
        }
    }

    override suspend fun deleteAll() {
        return booksDao.deleteAll()
    }

}