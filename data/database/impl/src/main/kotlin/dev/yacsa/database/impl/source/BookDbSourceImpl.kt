package dev.yacsa.database.impl.source

import dev.yacsa.database.impl.dao.BookDbDao
import dev.yacsa.database.model.BookDbModel
import dev.yacsa.database.source.BookDbSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BookDbSourceImpl @Inject constructor(
    private val booksDao: BookDbDao,
) : BookDbSource {

    //
    override suspend fun getPaged(page: Int): List<BookDbModel>? {
        return booksDao.getPaged(page)
    }

    override suspend fun removePage(page: Int) {
        booksDao.removePage(page)
    }

    override suspend fun search(query: String): List<BookDbModel> {
        return booksDao.search(query)
    }

    override suspend fun markFavourite(id:Int, isFavourite: Boolean) {
        booksDao.markFavourite(id,if(isFavourite){1}else{0})
    }

    override suspend fun getFavourite(): Flow<List<BookDbModel>?> {
        return booksDao.getFavouriteFlow()
    }
    //

    override suspend fun get(id: Int): BookDbModel? {
        return booksDao.get(id)
    }

    override suspend fun get(): List<BookDbModel>? {
        return booksDao.get()
    }

    override suspend fun getFlow(): Flow<List<BookDbModel>?> {
        return booksDao.getFlow()
    }

    override suspend fun insert(value: BookDbModel): Int {
        return booksDao.insert(value).toInt()
    }

    override suspend fun insert(values: List<BookDbModel>) {
        values.forEach {
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
