package dev.yacsa.database.impl.dao

import androidx.room.*
import dev.yacsa.database.YacsaDb
import dev.yacsa.database.impl.dao.base.BaseDbDao
import dev.yacsa.database.model.BookDbModel
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDbDao {

    @Query("SELECT * FROM ${YacsaDb.Table.BOOK} WHERE id=:id")
    suspend fun get(id: Int): BookDbModel?

    @Query("SELECT * FROM ${YacsaDb.Table.BOOK}")
    suspend fun get(): List<BookDbModel>?

    // TODO: rename to subscribe?
    @Query("SELECT * FROM ${YacsaDb.Table.BOOK}")
    fun getFlow(): Flow<List<BookDbModel>?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(value: BookDbModel)

    @Update
    suspend fun update(value: BookDbModel)

    @Query("DELETE FROM ${YacsaDb.Table.BOOK} WHERE id=:id")
    suspend fun delete(id: Int)

    @Query("DELETE FROM ${YacsaDb.Table.BOOK}")
    suspend fun deleteAll()

}