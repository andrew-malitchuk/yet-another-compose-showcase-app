package dev.yacsa.datastore.impl.dao

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import dev.yacsa.datastore.impl.dao.base.BaseDataStoreDao
import dev.yacsa.datastore.impl.serializer.PreferencesSerializer
import dev.yacsa.datastore.impl.serializer.StartupConfigureSerializer
import dev.yacsa.datastore.model.PreferencesDataStoreModel
import dev.yacsa.datastore.model.StartUpConfigureDataStoreModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class StartupConfigureDataStoreDao @Inject constructor(
    @ApplicationContext private val appContext: Context
) : BaseDataStoreDao<StartUpConfigureDataStoreModel>() {

    override val Context.dataStore by dataStore(
        STARTUP_CONFIGURE_DATASTORE_FILENAME,
        StartupConfigureSerializer
    )


    override fun getData(): Flow<StartUpConfigureDataStoreModel> {
        return appContext.dataStore.data
    }

    override suspend fun updateData(value: StartUpConfigureDataStoreModel) {
        appContext.dataStore.updateData {
            it.copy(
                hasBeenOnboardingShown = value.hasBeenOnboardingShown
            )
        }
    }

    companion object {
        const val STARTUP_CONFIGURE_DATASTORE_FILENAME = "startup.json"
    }

}