package dev.yacsa.database.source

import androidx.paging.PagingSource
import androidx.room.Query
import dev.yacsa.database.YacsaDb
import dev.yacsa.database.model.BookDbModel
import dev.yacsa.database.source.base.BaseDbSource

interface BookDbSource : BaseDbSource<BookDbModel>{
    @Query("Select * From ${YacsaDb.Table.BOOK}")
    suspend fun getMovies(): PagingSource<Int, BookDbModel>
}