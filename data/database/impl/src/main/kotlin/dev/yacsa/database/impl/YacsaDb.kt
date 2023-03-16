package dev.yacsa.database.impl

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.yacsa.database.impl.dao.BookDbDao

const val DB_VERSION=1

@Database(
    version = DB_VERSION,
    entities = [

    ],
    exportSchema = true
)
abstract class YacsaDb:RoomDatabase(){
    abstract fun bookDao(): BookDbDao
}
