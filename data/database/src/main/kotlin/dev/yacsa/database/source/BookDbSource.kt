package dev.yacsa.database.source

import dev.yacsa.database.model.BookDbModel
import dev.yacsa.database.source.base.BaseDbSource
import kotlinx.coroutines.flow.Flow

interface BookDbSource : BaseDbSource<BookDbModel> {
    suspend fun getPaged(page: Int): List<BookDbModel>?
    suspend fun removePage(page: Int)
    suspend fun search(
        query: String,
        sort: String?,
        lang: String?
    ): List<BookDbModel>

    suspend fun markFavourite(id: Int, isFavourite: Boolean)
    suspend fun getFavourite(): Flow<List<BookDbModel>?>
}
