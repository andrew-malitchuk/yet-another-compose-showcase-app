package dev.yacsa.database.impl.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import dev.yacsa.database.YacsaDb
import dev.yacsa.database.model.analytics.AnalyticsDbModel
import kotlinx.coroutines.flow.Flow

@Dao
interface AnalyticsDbDao {

    @Query("SELECT * FROM ${YacsaDb.Table.ANALYTICS}")
    suspend fun get(): List<AnalyticsDbModel>?

    @Query("SELECT * FROM ${YacsaDb.Table.ANALYTICS} WHERE id=:id")
    suspend fun get(id: Int): AnalyticsDbModel?

    // TODO: rename to subscribe?
    @Query("SELECT * FROM ${YacsaDb.Table.ANALYTICS}")
    fun subscribe(): Flow<List<AnalyticsDbModel>?>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(value: AnalyticsDbModel): Long

    @Update
    suspend fun update(value: AnalyticsDbModel)

    @Query("DELETE FROM ${YacsaDb.Table.ANALYTICS} WHERE id=:id")
    suspend fun delete(id: Int)

    @Query("DELETE FROM ${YacsaDb.Table.ANALYTICS}")
    suspend fun deleteAll()
}
