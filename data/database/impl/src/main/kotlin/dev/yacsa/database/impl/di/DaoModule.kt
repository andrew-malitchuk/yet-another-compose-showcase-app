package dev.yacsa.database.impl.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.yacsa.database.impl.YacsaDb
import dev.yacsa.database.impl.dao.BookDbDao

@Module
@InstallIn(SingletonComponent::class)
class DaoModule {

    @Provides
    fun providesBookDao(
        appDatabase: YacsaDb
    ): BookDbDao {
        return appDatabase.bookDao()
    }

}