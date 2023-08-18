package dev.yacsa.widget.favourite

import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dev.yacsa.model.model.BookUiModel

class Foo {

    private val moshi: Moshi = Moshi
        .Builder()
        .add(KotlinJsonAdapterFactory())
        .build()


    fun getJson(list:List<BookUiModel>):String{
        return moshi.listToJson(list)
    }

    fun fromJson(json:String):List<BookUiModel>?{
        return moshi.parseList(json)
    }



    // TODO: move to core
    inline fun <reified T> Moshi.listToJson(list: List<T>): String {
        return adapter<List<T>>(
            Types.newParameterizedType(
                List::class.java,
                T::class.java,
            ),
        ).toJson(list)
    }

    inline fun <reified T> Moshi.parseList(jsonString: String): List<T>? {
        return adapter<List<T>>(Types.newParameterizedType(List::class.java, T::class.java)).fromJson(
            jsonString,
        )
    }

}