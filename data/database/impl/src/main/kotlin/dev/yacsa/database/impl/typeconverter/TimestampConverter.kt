package dev.yacsa.database.impl.typeconverter

import androidx.room.TypeConverter
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date

class TimestampConverter {

    @TypeConverter
    fun fromTimestamp(value: String?): Date? {
        return if (value != null) {
            try {
                return SimpleDateFormat(TIMESTAMP).parse(value)
            } catch (e: ParseException) {
                e.printStackTrace()
            }
            null
        } else {
            null
        }
    }

    companion object{
        const val TIMESTAMP="yyyy-MM-dd HH:mm:ss"
    }
}