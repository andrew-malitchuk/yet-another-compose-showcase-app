package dev.yacsa.cryptodatastore.impl.dao

import android.content.Context
import androidx.datastore.dataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import dev.yacsa.cryptodatastore.impl.dao.base.BaseCryptoDataStoreDao
import dev.yacsa.cryptodatastore.impl.serializer.AccessTokenSerializer
import dev.yacsa.cryptodatastore.model.AccessTokenCryptoDataStoreModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AccessTokenCryptoDataStoreDao @Inject constructor(
    @ApplicationContext private val appContext: Context,
    private val accessTokenSerializer: AccessTokenSerializer,
) : BaseCryptoDataStoreDao<AccessTokenCryptoDataStoreModel>() {

    override val Context.dataStore by dataStore(
        ACCESS_TOKEN_DATASTORE_FILENAME,
        accessTokenSerializer,
    )

    override fun getData(): Flow<AccessTokenCryptoDataStoreModel> {
        return appContext.dataStore.data
    }

    override suspend fun updateData(value: AccessTokenCryptoDataStoreModel) {
        appContext.dataStore.updateData {
            it.copy(
                accessToken = value.accessToken,
            )
        }
    }

    companion object {
        const val ACCESS_TOKEN_DATASTORE_FILENAME = "access_token.json"
    }
}
