package dev.yacsa.database.impl.typeconverter

// class PersonTypeConverter {
//
//    private val moshi: Moshi by lazy { Moshi.Builder().build() }
//
//    private val objectAdapter: JsonAdapter<PersonDbModel> by lazy { moshi.adapter(PersonDbModel::class.java) }
//
//    @TypeConverter
//    fun personToString(person: PersonDbModel): String {
//        return objectAdapter.toJson(person)
//    }
//
//    @TypeConverter
//    fun stringToPerson(person: String): PersonDbModel? {
//        return objectAdapter.fromJson(person)
//    }
//
// }
