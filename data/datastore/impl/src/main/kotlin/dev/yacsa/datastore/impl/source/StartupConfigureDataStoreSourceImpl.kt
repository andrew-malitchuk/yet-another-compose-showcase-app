package dev.yacsa.datastore.impl.source

import dev.yacsa.datastore.impl.dao.PreferencesDataStoreDao
import dev.yacsa.datastore.impl.dao.StartupConfigureDataStoreDao
import dev.yacsa.datastore.model.PreferencesDataStoreModel
import dev.yacsa.datastore.model.StartUpConfigureDataStoreModel
import dev.yacsa.datastore.model.ThemeDateStoreModel
import dev.yacsa.datastore.source.PreferencesDataStoreSource
import dev.yacsa.datastore.source.StartupConfigureDataStoreSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapLatest
import javax.inject.Inject

class StartupConfigureDataStoreSourceImpl @Inject constructor(
    private val startupConfigureDataStoreDao: StartupConfigureDataStoreDao
) : StartupConfigureDataStoreSource() {


    override fun getData(): Flow<StartUpConfigureDataStoreModel> {
        return startupConfigureDataStoreDao.getData()
    }

    override suspend fun updateData(value: StartUpConfigureDataStoreModel) {
        startupConfigureDataStoreDao.updateData(value)
    }

}