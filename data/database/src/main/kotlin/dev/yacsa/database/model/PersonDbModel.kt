package dev.yacsa.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import dev.yacsa.database.YacsaDb
import dev.yacsa.database.model.base.BaseDbModel

@Entity(
    tableName = YacsaDb.Table.PERSON,
    indices = [
        Index(
            value = [
                "name",
            ],
            unique = true,
        ),
    ],
)
data class PersonDbModel(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "personId")
    val id: Int = 0,
    @ColumnInfo(name = "birth_year")
    val birthYear: Int?,
    @ColumnInfo(name = "death_year")
    val deathYear: Int?,
    @ColumnInfo(name = "name")
    val name: String?,
) : BaseDbModel
