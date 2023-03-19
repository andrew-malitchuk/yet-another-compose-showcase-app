package dev.yacsa.datastore.impl.serializer

import androidx.datastore.core.Serializer
import dev.yacsa.datastore.model.PreferencesDataStoreModel
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream

object PreferencesSerializer : Serializer<PreferencesDataStoreModel> {

    override val defaultValue: PreferencesDataStoreModel
        get() = PreferencesDataStoreModel()

    override suspend fun readFrom(input: InputStream): PreferencesDataStoreModel {
        return try {
            Json.decodeFromString(
                deserializer = PreferencesDataStoreModel.serializer(),
                string = input.readBytes().decodeToString()
            )
        } catch (e: SerializationException) {
            e.printStackTrace()
            defaultValue
        }
    }

    override suspend fun writeTo(t: PreferencesDataStoreModel, output: OutputStream) {
        output.write(
            Json.encodeToString(
                serializer = PreferencesDataStoreModel.serializer(),
                value = t
            ).encodeToByteArray()
        )
    }

}