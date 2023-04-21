package dev.yacsa.database.impl.typeconverter

import androidx.room.TypeConverter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dev.yacsa.database.model.PersonDbModel


class PersonListTypeConverter {

    private val moshi: Moshi = Moshi
        .Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    @TypeConverter
    fun personToString(persons: List<PersonDbModel?>?): String {
        return persons?.filterNotNull()?.let {
            moshi.listToJson(it)
        } ?: ""
    }

    @TypeConverter
    fun stringToPerson(person: String): List<PersonDbModel?>? {
        return moshi.parseList<PersonDbModel>(person)
    }

}

// TODO: move to core
inline fun <reified T> Moshi.listToJson(list: List<T>): String {
    return adapter<List<T>>(
        Types.newParameterizedType(
            List::class.java,
            T::class.java
        )
    ).toJson(list)
}

inline fun <reified T> Moshi.parseList(jsonString: String): List<T>? {
    return adapter<List<T>>(Types.newParameterizedType(List::class.java, T::class.java)).fromJson(
        jsonString
    )
}