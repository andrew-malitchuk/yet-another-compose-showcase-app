package dev.yacsa.database.model.analytics

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.yacsa.database.YacsaDb
import dev.yacsa.database.model.base.BaseDbModel

// TODO: fix & add create at
@Entity(tableName = YacsaDb.Table.ANALYTICS)
data class AnalyticsDbModel(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "key")
    val key: String?,
    @ColumnInfo(name = "value")
    var value: String?,
) : BaseDbModel
