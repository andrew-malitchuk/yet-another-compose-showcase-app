package dev.yacsa.datastore.impl.serializer

import androidx.datastore.core.Serializer
import dev.yacsa.datastore.model.ThemeConfigureDataStoreModel
import dev.yacsa.datastore.model.ThemeMode
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream

// TODO: somehow remove boilerplate
object ThemeConfigureSerializer : Serializer<ThemeConfigureDataStoreModel> {

    override val defaultValue: ThemeConfigureDataStoreModel
        get() = ThemeConfigureDataStoreModel(ThemeMode.LIGHT)

    override suspend fun readFrom(input: InputStream): ThemeConfigureDataStoreModel {
        return try {
            Json.decodeFromString(
                deserializer = ThemeConfigureDataStoreModel.serializer(),
                string = input.readBytes().decodeToString(),
            )
        } catch (e: SerializationException) {
            e.printStackTrace()
            defaultValue
        }
    }

    override suspend fun writeTo(t: ThemeConfigureDataStoreModel, output: OutputStream) {
        output.write(
            Json.encodeToString(
                serializer = ThemeConfigureDataStoreModel.serializer(),
                value = t,
            ).encodeToByteArray(),
        )
    }
}
