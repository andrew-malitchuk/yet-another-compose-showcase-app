package dev.yacsa.datastore.impl.dao

import android.content.Context
import androidx.datastore.dataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import dev.yacsa.datastore.impl.dao.base.BaseDataStoreDao
import dev.yacsa.datastore.impl.serializer.PreferencesSerializer
import dev.yacsa.datastore.model.PreferencesDataStoreModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PreferencesDataStoreDao @Inject constructor(
    @ApplicationContext private val appContext: Context
) : BaseDataStoreDao<PreferencesDataStoreModel>() {

    override val Context.dataStore by dataStore(
        PREFERENCES_DATASTORE_FILENAME,
        PreferencesSerializer
    )

    override fun getData(): Flow<PreferencesDataStoreModel> {
        return appContext.dataStore.data
    }

    override suspend fun updateData(value: PreferencesDataStoreModel) {
        appContext.dataStore.updateData {
            it.copy(
                theme = value.theme
            )
        }
    }

    companion object {
        const val PREFERENCES_DATASTORE_FILENAME = "preferences.json"
    }

}