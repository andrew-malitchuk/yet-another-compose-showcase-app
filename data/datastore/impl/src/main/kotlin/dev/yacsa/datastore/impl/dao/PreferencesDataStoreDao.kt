package dev.yacsa.datastore.impl.dao

import androidx.datastore.core.DataStore
import dev.yacsa.datastore.impl.dao.base.BaseDataStoreDao
import dev.yacsa.datastore.model.PreferencesDataStoreModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Named

class PreferencesDataStoreDao @Inject constructor(
    @Named("preferencesDataStore") private val dataStore: DataStore<PreferencesDataStoreModel>,
) : BaseDataStoreDao<PreferencesDataStoreModel>() {

    override fun getData(): Flow<PreferencesDataStoreModel> {
        return dataStore.data
    }

    override suspend fun updateData(value: PreferencesDataStoreModel) {
        dataStore.updateData {
            it.copy(
                theme = value.theme,
            )
        }
    }

    companion object {
        const val PREFERENCES_DATASTORE_FILENAME = "preferences.json"
    }
}
