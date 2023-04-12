package dev.yacsa.database.impl.dao

import androidx.room.*
import dev.yacsa.database.YacsaDb
import dev.yacsa.database.model.FeatureFlagDbModel
import kotlinx.coroutines.flow.Flow


@Dao
interface FeatureFlagDbDao {

    @Query("SELECT * FROM ${YacsaDb.Table.FEATURE_FLAG} WHERE id=:id")
    suspend fun get(id: Int): FeatureFlagDbModel?

    @Query("SELECT * FROM ${YacsaDb.Table.FEATURE_FLAG}")
    suspend fun get(): List<FeatureFlagDbModel>?

    // TODO: rename to subscribe?
    @Query("SELECT * FROM ${YacsaDb.Table.FEATURE_FLAG}")
    fun getFlow(): Flow<List<FeatureFlagDbModel>?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(value: FeatureFlagDbModel): Long

    @Update
    suspend fun update(value: FeatureFlagDbModel)

    @Query("DELETE FROM ${YacsaDb.Table.FEATURE_FLAG} WHERE id=:id")
    suspend fun delete(id: Int)

    @Query("DELETE FROM ${YacsaDb.Table.FEATURE_FLAG}")
    suspend fun deleteAll()
}