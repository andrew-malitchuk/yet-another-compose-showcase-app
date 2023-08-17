package dev.yacsa.datastore.impl.source

import dev.yacsa.datastore.impl.dao.ThemeConfigureDataStoreDao
import dev.yacsa.datastore.model.ThemeConfigureDataStoreModel
import dev.yacsa.datastore.source.ThemeConfigureDataStoreSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ThemeConfigureDataStoreSourceImpl @Inject constructor(
    private val themeConfigureDataStoreDao: ThemeConfigureDataStoreDao,
) : ThemeConfigureDataStoreSource() {

    override fun getData(): Flow<ThemeConfigureDataStoreModel> {
        return themeConfigureDataStoreDao.getData()
    }

    override suspend fun updateData(value: ThemeConfigureDataStoreModel) {
        themeConfigureDataStoreDao.updateData(value)
    }
}
