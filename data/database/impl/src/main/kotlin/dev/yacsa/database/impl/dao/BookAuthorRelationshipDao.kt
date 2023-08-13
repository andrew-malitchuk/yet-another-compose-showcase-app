package dev.yacsa.database.impl.dao

import androidx.room.*
import dev.yacsa.database.YacsaDb
import dev.yacsa.database.model.BookAuthorDbModel
import dev.yacsa.database.model.relationships.BookAuthorRelationship
import kotlinx.coroutines.flow.Flow

@Dao
interface BookAuthorRelationshipDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(valur: BookAuthorRelationship)

    @Transaction
    @Query("SELECT * FROM ${YacsaDb.Table.BOOK}")
    suspend fun get(): List<BookAuthorDbModel>

    @Query("SELECT * FROM ${YacsaDb.Table.BOOK}")
    fun getFlow(): Flow<List<BookAuthorDbModel>>
}
