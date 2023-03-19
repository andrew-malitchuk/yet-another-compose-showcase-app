package dev.yacsa.cryptodatastore.impl.serializer

import androidx.datastore.core.Serializer
import dev.yacsa.cryptodatastore.impl.cryptomanager.CryptoManager
import dev.yacsa.cryptodatastore.model.AccessTokenCryptoDataStoreModel
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream
import javax.inject.Inject

class AccessTokenSerializer @Inject constructor(
    private val cryptoManager: CryptoManager
) : Serializer<AccessTokenCryptoDataStoreModel> {

    override val defaultValue: AccessTokenCryptoDataStoreModel
        get() = AccessTokenCryptoDataStoreModel()

    override suspend fun readFrom(input: InputStream): AccessTokenCryptoDataStoreModel {
        val decryptedBytes = cryptoManager.decrypt(input)
        return try {
            Json.decodeFromString(
                deserializer = AccessTokenCryptoDataStoreModel.serializer(),
                string = decryptedBytes.decodeToString()
            )
        } catch (e: SerializationException) {
            e.printStackTrace()
            defaultValue
        }
    }

    override suspend fun writeTo(t: AccessTokenCryptoDataStoreModel, output: OutputStream) {
        cryptoManager.encrypt(
            bytes = Json.encodeToString(
                serializer = AccessTokenCryptoDataStoreModel.serializer(),
                value = t
            ).encodeToByteArray(),
            outputStream = output
        )
    }

}