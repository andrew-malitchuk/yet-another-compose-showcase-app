package dev.yacsa.database.impl.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.yacsa.database.impl.YacsaDb
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DbModule {

    @Provides
    @Singleton
    fun providesAppDatabase(
        @ApplicationContext appContext: Context,
    ): YacsaDb {
        return Room
            .databaseBuilder(
                appContext,
                YacsaDb::class.java,
                dev.yacsa.database.YacsaDb.NAME
            )
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

}