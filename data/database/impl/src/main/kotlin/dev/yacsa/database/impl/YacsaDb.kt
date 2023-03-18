package dev.yacsa.database.impl

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.yacsa.database.impl.dao.BookDbDao
import dev.yacsa.database.model.BookDbModel

const val DB_VERSION=1

@Database(
    version = DB_VERSION,
    entities = [
        BookDbModel::class
    ],
    exportSchema = false
)
abstract class YacsaDb:RoomDatabase(){
    abstract fun bookDao(): BookDbDao
}
