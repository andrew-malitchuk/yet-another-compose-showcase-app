package dev.yacsa.datastore.impl.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.yacsa.datastore.impl.dao.PreferencesDataStoreDao
import dev.yacsa.datastore.impl.dao.StartupConfigureDataStoreDao
import dev.yacsa.datastore.impl.dao.ThemeConfigureDataStoreDao.Companion.THEME_CONFIGURE_DATASTORE_FILENAME
import dev.yacsa.datastore.impl.serializer.PreferencesSerializer
import dev.yacsa.datastore.impl.serializer.StartupConfigureSerializer
import dev.yacsa.datastore.impl.serializer.ThemeConfigureSerializer
import dev.yacsa.datastore.model.PreferencesDataStoreModel
import dev.yacsa.datastore.model.StartUpConfigureDataStoreModel
import dev.yacsa.datastore.model.ThemeConfigureDataStoreModel
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataStoreModule {

    @Singleton
    private val Context.startUpConfigureDataStore: DataStore<StartUpConfigureDataStoreModel> by dataStore(
        StartupConfigureDataStoreDao.STARTUP_CONFIGURE_DATASTORE_FILENAME,
        StartupConfigureSerializer,
    )

    @Singleton
    private val Context.preferencesDataStore by dataStore(
        PreferencesDataStoreDao.PREFERENCES_DATASTORE_FILENAME,
        PreferencesSerializer,
    )

    @Singleton
    private val Context.themeConfigureDataStore by dataStore(
        THEME_CONFIGURE_DATASTORE_FILENAME,
        ThemeConfigureSerializer,
    )

    @Provides
    @Named("startUpConfigureDataStore")
    fun providesDataStoreStartUpConfigure(
        @ApplicationContext applicationContext: Context,
    ): DataStore<StartUpConfigureDataStoreModel> {
        return applicationContext.startUpConfigureDataStore
    }

    @Provides
    @Named("preferencesDataStore")
    fun providesPreferencesDataStore(
        @ApplicationContext applicationContext: Context,
    ): DataStore<PreferencesDataStoreModel> {
        return applicationContext.preferencesDataStore
    }

    @Provides
    @Named("themeConfigureDataStore")
    fun providesThemeConfigureDataStore(
        @ApplicationContext applicationContext: Context,
    ): DataStore<ThemeConfigureDataStoreModel> {
        return applicationContext.themeConfigureDataStore
    }
}
