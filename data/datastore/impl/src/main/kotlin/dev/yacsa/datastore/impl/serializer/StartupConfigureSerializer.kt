package dev.yacsa.datastore.impl.serializer

import androidx.datastore.core.Serializer
import dev.yacsa.datastore.model.StartUpConfigureDataStoreModel
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream

// TODO: somehow remove boilerplate
object StartupConfigureSerializer : Serializer<StartUpConfigureDataStoreModel> {

    override val defaultValue: StartUpConfigureDataStoreModel
        get() = StartUpConfigureDataStoreModel(false, null)

    override suspend fun readFrom(input: InputStream): StartUpConfigureDataStoreModel {
        return try {
            Json.decodeFromString(
                deserializer = StartUpConfigureDataStoreModel.serializer(),
                string = input.readBytes().decodeToString(),
            )
        } catch (e: SerializationException) {
            e.printStackTrace()
            defaultValue
        }
    }

    override suspend fun writeTo(t: StartUpConfigureDataStoreModel, output: OutputStream) {
        output.write(
            Json.encodeToString(
                serializer = StartUpConfigureDataStoreModel.serializer(),
                value = t,
            ).encodeToByteArray(),
        )
    }
}
