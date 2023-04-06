package dev.yacsa.database.impl.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.yacsa.database.YacsaDb.Table.REMOVE_KEY
import dev.yacsa.database.model.RemoteKeyDbModel

@Dao
interface RemoteKeyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(remoteKey: List<RemoteKeyDbModel>)

    @Query("SELECT * FROM $REMOVE_KEY WHERE bookId = :bookId")
    fun remoteId(bookId: Long): RemoteKeyDbModel?

    @Query("DELETE FROM $REMOVE_KEY ")
    fun clearRemoteKeys()
}