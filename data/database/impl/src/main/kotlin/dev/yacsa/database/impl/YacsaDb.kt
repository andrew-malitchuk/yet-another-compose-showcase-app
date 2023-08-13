package dev.yacsa.database.impl

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import dev.yacsa.database.impl.dao.AnalyticsDbDao
import dev.yacsa.database.impl.dao.BookAuthorRelationshipDao
import dev.yacsa.database.impl.dao.BookDbDao
import dev.yacsa.database.impl.dao.FeatureFlagDbDao
import dev.yacsa.database.impl.dao.PersonDbDao
import dev.yacsa.database.impl.dao.SearchHistoryDao
import dev.yacsa.database.impl.typeconverter.PersonListTypeConverter
import dev.yacsa.database.impl.typeconverter.StringListTypeConvertor
import dev.yacsa.database.impl.typeconverter.TimestampConverter
import dev.yacsa.database.model.BookDbModel
import dev.yacsa.database.model.FeatureFlagDbModel
import dev.yacsa.database.model.PersonDbModel
import dev.yacsa.database.model.SearchHistoryDbModel
import dev.yacsa.database.model.analytics.AnalyticsDbModel
import dev.yacsa.database.model.relationships.BookAuthorRelationship

const val DB_VERSION = 1

@TypeConverters(StringListTypeConvertor::class, PersonListTypeConverter::class, TimestampConverter::class)
@Database(
    version = DB_VERSION,
    entities = [
        BookDbModel::class,
        PersonDbModel::class,
        BookAuthorRelationship::class,
        FeatureFlagDbModel::class,
        SearchHistoryDbModel::class,
        AnalyticsDbModel::class,
    ],
    exportSchema = true,
)
abstract class YacsaDb : RoomDatabase() {
    abstract fun getBookDao(): BookDbDao
    abstract fun getPersonDbDao(): PersonDbDao
    abstract fun getBookAuthorRelationshipDao(): BookAuthorRelationshipDao
    abstract fun getFeatureFlagDbDao(): FeatureFlagDbDao
    abstract fun getSearchHistoryDao(): SearchHistoryDao
    abstract fun getAnalyticsDao(): AnalyticsDbDao
}
