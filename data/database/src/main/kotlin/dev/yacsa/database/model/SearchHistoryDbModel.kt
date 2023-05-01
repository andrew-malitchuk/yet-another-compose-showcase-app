package dev.yacsa.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.yacsa.database.YacsaDb
import dev.yacsa.database.model.base.BaseDbModel

// TODO: fix & add create at
@Entity(tableName = YacsaDb.Table.SEARCH_HISTORY)
data class SearchHistoryDbModel(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "query")
    val query: String?,
    @ColumnInfo(name = "createAt")
    val createAt: Long,
) : BaseDbModel
