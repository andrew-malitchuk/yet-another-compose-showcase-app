package dev.yacsa.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.yacsa.database.YacsaDb
import dev.yacsa.database.model.base.DbModel

@Entity(tableName = YacsaDb.Table.BOOK)
data class BookDbModel(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "title")
    val title: String?,
//    @ColumnInfo(name = "subjects")
//    val subjects: List<String?>?,
//    @ColumnInfo(name = "authors")
//    val authors: List<PersonNetModel?>?,
//    @ColumnInfo(name = "translators")
//    val translators: List<PersonNetModel?>?,
//    @ColumnInfo(name = "bookshelves")
//    val bookshelves: List<String?>?,
//    @ColumnInfo(name = "languages")
//    val languages: List<String?>?,
    @ColumnInfo(name = "copyright")
    val copyright: Boolean?,
    @ColumnInfo(name = "media_type")
    val mediaType: String?,
//    @ColumnInfo(name = "formats")
//    val formats: FormatsNetModel?,
    @ColumnInfo(name = "download_count")
    val downloadCount: Int?,
) : DbModel
