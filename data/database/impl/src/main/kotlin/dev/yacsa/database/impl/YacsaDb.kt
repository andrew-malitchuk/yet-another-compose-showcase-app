package dev.yacsa.database.impl

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.yacsa.database.impl.dao.BookAuthorRelationshipDao
import dev.yacsa.database.impl.dao.BookDbDao
import dev.yacsa.database.impl.dao.FeatureFlagDbDao
import dev.yacsa.database.impl.dao.PersonDbDao
import dev.yacsa.database.model.BookDbModel
import dev.yacsa.database.model.FeatureFlagDbModel
import dev.yacsa.database.model.PersonDbModel
import dev.yacsa.database.model.relationships.BookAuthorRelationship

const val DB_VERSION = 1

@Database(
    version = DB_VERSION,
    entities = [
        BookDbModel::class,
        PersonDbModel::class,
        BookAuthorRelationship::class,
        FeatureFlagDbModel::class,
    ],
    exportSchema = true,
)
abstract class YacsaDb : RoomDatabase() {
    // TODO: rename to get..Dao()
    abstract fun bookDao(): BookDbDao
    abstract fun personDbDao(): PersonDbDao
    abstract fun bookAuthorRelationshipDao(): BookAuthorRelationshipDao
    abstract fun featureFlagDbDao(): FeatureFlagDbDao
}
