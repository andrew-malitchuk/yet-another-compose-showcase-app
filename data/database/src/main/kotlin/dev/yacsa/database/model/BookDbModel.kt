package dev.yacsa.database.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.yacsa.database.YacsaDb
import dev.yacsa.database.model.base.BaseDbModel

@Entity(tableName = YacsaDb.Table.BOOK)
data class BookDbModel(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "bookId")
    val id: Int,
    @ColumnInfo(name = "title")
    val title: String?,
    @ColumnInfo(name = "subjects")
    val subjects: List<String?>?,
    @ColumnInfo(name = "authors")
    val authors: List<PersonDbModel?>?,
    @ColumnInfo(name = "translators")
    val translators: List<PersonDbModel?>?,
    @ColumnInfo(name = "bookshelves")
    val bookshelves: List<String?>?,
    @ColumnInfo(name = "languages")
    val languages: List<String?>?,
    @ColumnInfo(name = "copyright")
    val copyright: Boolean?,
    @ColumnInfo(name = "media_type")
    val mediaType: String?,
    @Embedded
    val formats: FormatsDbModel,
    @ColumnInfo(name = "download_count")
    val downloadCount: Int?,
    @ColumnInfo(name = "page")
    var page: Int? = 1,
    @ColumnInfo(name = "favourite")
    var favourite: Boolean? = false,
) : BaseDbModel
