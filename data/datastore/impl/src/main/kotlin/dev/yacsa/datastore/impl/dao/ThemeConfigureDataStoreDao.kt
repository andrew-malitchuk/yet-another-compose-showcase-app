package dev.yacsa.datastore.impl.dao

import androidx.datastore.core.DataStore
import dev.yacsa.datastore.impl.dao.base.BaseDataStoreDao
import dev.yacsa.datastore.model.ThemeConfigureDataStoreModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Named

class ThemeConfigureDataStoreDao @Inject constructor(
    @Named("themeConfigureDataStore") private val dataStore: DataStore<ThemeConfigureDataStoreModel>,
) : BaseDataStoreDao<ThemeConfigureDataStoreModel>() {

    override fun getData(): Flow<ThemeConfigureDataStoreModel> {
        return dataStore.data
    }

    override suspend fun updateData(value: ThemeConfigureDataStoreModel) {
        dataStore.updateData {
            it.copy(
                theme = value.theme,
            )
        }
    }

    companion object {
        const val THEME_CONFIGURE_DATASTORE_FILENAME = "theme.json"
    }
}
