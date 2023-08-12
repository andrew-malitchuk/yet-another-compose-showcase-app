package dev.yacsa.database.impl.dao

import androidx.room.*
import dev.yacsa.database.YacsaDb
import dev.yacsa.database.model.PersonDbModel
import kotlinx.coroutines.flow.Flow

@Dao
interface PersonDbDao {

    @Query("SELECT * FROM ${YacsaDb.Table.PERSON} WHERE personId=:id")
    suspend fun get(id: Int): PersonDbModel?

    @Query("SELECT * FROM ${YacsaDb.Table.PERSON}")
    suspend fun get(): List<PersonDbModel>?

    @Query("SELECT * FROM ${YacsaDb.Table.PERSON}")
    fun subscribe(): Flow<List<PersonDbModel>?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(value: PersonDbModel): Long

    @Update
    suspend fun update(value: PersonDbModel)

    @Query("DELETE FROM ${YacsaDb.Table.PERSON} WHERE personId=:id")
    suspend fun delete(id: Int)

    @Query("DELETE FROM ${YacsaDb.Table.PERSON}")
    suspend fun deleteAll()
}
