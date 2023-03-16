package dev.yacsa.database.impl.dao

import androidx.room.*
import dev.yacsa.database.YacsaDb
import dev.yacsa.database.impl.dao.base.BaseDbDao
import dev.yacsa.database.model.BookDbModel

@Dao
interface BookDbDao : BaseDbDao<BookDbModel> {

    @Query("SELECT * FROM ${YacsaDb.Table.BOOK} WHERE id=:id")
    override suspend fun get(id: Int): BookDbModel?

    @Query("SELECT * FROM ${YacsaDb.Table.BOOK}")
    override suspend fun get(): List<BookDbModel>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override suspend fun insert(value: BookDbModel)

    @Update
    override suspend fun update(value: BookDbModel)

    @Query("DELETE FROM ${YacsaDb.Table.BOOK} WHERE id=:id")
    override suspend fun delete(id: Int) 

    @Query("DELETE FROM ${YacsaDb.Table.BOOK}")
    override suspend fun deleteAll()

}