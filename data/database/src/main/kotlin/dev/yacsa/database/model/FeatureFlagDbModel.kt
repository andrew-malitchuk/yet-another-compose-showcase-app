package dev.yacsa.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import dev.yacsa.database.YacsaDb
import dev.yacsa.database.model.base.BaseDbModel


@Entity(
    tableName = YacsaDb.Table.FEATURE_FLAG,
    indices = [
        Index(
            value = [
                "key",
            ],
            unique = true,
        ),
    ],
)
data class FeatureFlagDbModel(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "key")
    val key: String,
    @ColumnInfo(name = "value")
    val value: Boolean? = null
) : BaseDbModel