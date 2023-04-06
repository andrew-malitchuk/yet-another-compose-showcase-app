package dev.yacsa.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.yacsa.database.YacsaDb
import dev.yacsa.database.model.base.BaseDbModel

// TODO: fix
@Entity(
    tableName = YacsaDb.Table.REMOVE_KEY,
)
data class RemoteKeyDbModel(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "bookId")
    val bookId:Int,
    val prevKey: Int?,
    val currentPage: Int,
    val nextKey: Int?,
    @ColumnInfo(name = "created_at")
    val createdAt: Long = System.currentTimeMillis()
):BaseDbModel