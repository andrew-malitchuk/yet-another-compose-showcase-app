package dev.yacsa.datastore.impl.source

import dev.yacsa.datastore.impl.dao.PreferencesDataStoreDao
import dev.yacsa.datastore.model.PreferencesDataStoreModel
import dev.yacsa.datastore.model.ThemeDateStoreModel
import dev.yacsa.datastore.source.PreferencesDataStoreSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapLatest
import javax.inject.Inject

class PreferencesDataStoreSourceImpl @Inject constructor(
    private val preferencesDataStoreDao: PreferencesDataStoreDao
) : PreferencesDataStoreSource() {

    override fun collectSomeData() {
        preferencesDataStoreDao.getData().mapLatest { it.theme == ThemeDateStoreModel.LIGHT }
    }

    override fun getData(): Flow<PreferencesDataStoreModel> {
        return preferencesDataStoreDao.getData()
    }

    override suspend fun updateData(value: PreferencesDataStoreModel) {
        preferencesDataStoreDao.updateData(value)
    }

}