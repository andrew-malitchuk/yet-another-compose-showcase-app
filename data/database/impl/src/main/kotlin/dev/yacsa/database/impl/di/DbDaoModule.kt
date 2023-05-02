package dev.yacsa.database.impl.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.yacsa.database.impl.YacsaDb
import dev.yacsa.database.impl.dao.BookAuthorRelationshipDao
import dev.yacsa.database.impl.dao.BookDbDao
import dev.yacsa.database.impl.dao.FeatureFlagDbDao
import dev.yacsa.database.impl.dao.PersonDbDao
import dev.yacsa.database.impl.dao.SearchHistoryDao

@Module
@InstallIn(SingletonComponent::class)
class DbDaoModule {

    @Provides
    fun providesBookDao(
        appDatabase: YacsaDb,
    ): BookDbDao {
        return appDatabase.getBookDao()
    }

    @Provides
    fun providesPersonDbDao(
        appDatabase: YacsaDb,
    ): PersonDbDao {
        return appDatabase.getPersonDbDao()
    }

    @Provides
    fun providesBookAuthorRelationshipDao(
        appDatabase: YacsaDb,
    ): BookAuthorRelationshipDao {
        return appDatabase.getBookAuthorRelationshipDao()
    }

    @Provides
    fun providesFeatureFlagDbDao(
        appDatabase: YacsaDb,
    ): FeatureFlagDbDao {
        return appDatabase.getFeatureFlagDbDao()
    }

    @Provides
    fun providesSearchHistoryDao(
        appDatabase: YacsaDb,
    ): SearchHistoryDao {
        return appDatabase.getSearchHistoryDao()
    }
}
