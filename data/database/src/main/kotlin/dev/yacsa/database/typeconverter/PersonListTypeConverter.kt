package dev.yacsa.database.typeconverter

// class PersonListTypeConverter {
//
//    private val moshi: Moshi by lazy { Moshi.Builder().build() }
//
//    private val objectAdapter: JsonAdapter<PersonDbModel> by lazy { moshi.adapter(PersonDbModel::class.java) }
//
//    private val listType: ParameterizedType by lazy {
//        Types.newParameterizedType(
//            List::class.java,
//            PersonDbModel::class.java
//        )
//    }
//    private val listAdapter: JsonAdapter<List<PersonDbModel>> by lazy {
//        moshi.adapter(listType)
//    }
//
//
//    @TypeConverter
//    fun personToString(persons: List<PersonDbModel>): String {
//        return listAdapter.toJson(persons)
//    }
//
//    @TypeConverter
//    fun stringToPerson(person: String): List<PersonDbModel?>? {
//        return listAdapter.fromJson(person)
//    }
//
// }
