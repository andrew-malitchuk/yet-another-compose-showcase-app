package dev.yacsa.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import dev.yacsa.network.model.base.BaseNetModel

// TODO: add json as a kdoc
@JsonClass(generateAdapter = true)
data class ResultNetModel(
    @Json(name = "count")
    val count: Int?,
    @Json(name = "next")
    val next: String?,
    @Json(name = "previous")
    val previous: Any?,
    @Json(name = "results")
    val results: List<BookNetModel?>?,
) : BaseNetModel
