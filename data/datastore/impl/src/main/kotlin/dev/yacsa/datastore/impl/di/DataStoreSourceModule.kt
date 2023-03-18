package dev.yacsa.datastore.impl.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.yacsa.datastore.impl.source.PreferencesDataStoreSourceImpl
import dev.yacsa.datastore.source.PreferencesDataStoreSource

@Module
@InstallIn(SingletonComponent::class)
abstract class DataStoreSourceModule {

    @Binds
    abstract fun bindsPreferencesDataStoreSource(
        PreferencesDataStoreSourceImpl: PreferencesDataStoreSourceImpl
    ): PreferencesDataStoreSource

}