package dev.yacsa.database.impl.typeconverter

import androidx.room.TypeConverter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class StringListTypeConvertor {

    private val moshi: Moshi = Moshi
        .Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    @TypeConverter
    fun personToString(values: List<String?>?): String {
        return values?.filterNotNull()?.let {
            moshi.listToJson(it)
        } ?: ""
    }

    @TypeConverter
    fun stringToPerson(values: String): List<String?>? {
        return moshi.parseList<String>(values)
    }
}
