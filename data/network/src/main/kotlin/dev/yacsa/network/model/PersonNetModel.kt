package dev.yacsa.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import dev.yacsa.network.model.base.BaseNetModel

/**
 *
 * Expected JSON:
 * ```
 *  {
 *      "name": "Forster, E. M. (Edward Morgan)",
 *      "birth_year": 1879,
 *      "death_year": 1970
 *  }
 * ```
 */
@JsonClass(generateAdapter = true)
data class PersonNetModel(
    @Json(name = "birth_year")
    val birthYear: Int?,
    @Json(name = "death_year")
    val deathYear: Int?,
    @Json(name = "name")
    val name: String?,
) : BaseNetModel
