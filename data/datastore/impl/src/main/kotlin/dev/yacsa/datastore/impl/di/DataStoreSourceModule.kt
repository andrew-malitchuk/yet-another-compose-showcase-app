package dev.yacsa.datastore.impl.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.yacsa.datastore.impl.source.PreferencesDataStoreSourceImpl
import dev.yacsa.datastore.impl.source.StartupConfigureDataStoreSourceImpl
import dev.yacsa.datastore.source.PreferencesDataStoreSource
import dev.yacsa.datastore.source.StartupConfigureDataStoreSource

@Module
@InstallIn(SingletonComponent::class)
abstract class DataStoreSourceModule {

    @Binds
    abstract fun bindsPreferencesDataStoreSource(
        preferencesDataStoreSourceImpl: PreferencesDataStoreSourceImpl
    ): PreferencesDataStoreSource

    @Binds
    abstract fun bindsStartupConfigureDataStoreSource(
        startupConfigureDataStoreSourceImpl: StartupConfigureDataStoreSourceImpl
    ): StartupConfigureDataStoreSource

}