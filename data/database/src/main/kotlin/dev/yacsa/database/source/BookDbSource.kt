package dev.yacsa.database.source

import dev.yacsa.database.model.BookDbModel
import dev.yacsa.database.source.base.BaseDbSource

interface BookDbSource : BaseDbSource<BookDbModel> {
    suspend fun getPaged(page: Int): List<BookDbModel>?
    suspend fun removePage(page: Int)
}
