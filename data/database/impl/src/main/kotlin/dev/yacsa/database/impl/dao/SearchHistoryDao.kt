package dev.yacsa.database.impl.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import dev.yacsa.database.YacsaDb
import dev.yacsa.database.model.SearchHistoryDbModel
import kotlinx.coroutines.flow.Flow

@Dao
interface SearchHistoryDao {

    @Query("SELECT * FROM ${YacsaDb.Table.SEARCH_HISTORY}")
    suspend fun get(): List<SearchHistoryDbModel>?

    @Query("SELECT * FROM ${YacsaDb.Table.SEARCH_HISTORY} WHERE id=:id")
    suspend fun get(id: Int): SearchHistoryDbModel?

    @Query("SELECT * FROM ${YacsaDb.Table.SEARCH_HISTORY} ORDER BY createAt DESC LIMIT :count")
    suspend fun getTop(count: Int): List<SearchHistoryDbModel>?

    // TODO: rename to subscribe?
    @Query("SELECT * FROM ${YacsaDb.Table.SEARCH_HISTORY}")
    fun subscribe(): Flow<List<SearchHistoryDbModel>?>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(value: SearchHistoryDbModel): Long

    @Update
    suspend fun update(value: SearchHistoryDbModel)

    @Query("DELETE FROM ${YacsaDb.Table.SEARCH_HISTORY} WHERE id=:id")
    suspend fun delete(id: Int)

    @Query("DELETE FROM ${YacsaDb.Table.SEARCH_HISTORY}")
    suspend fun deleteAll()

}