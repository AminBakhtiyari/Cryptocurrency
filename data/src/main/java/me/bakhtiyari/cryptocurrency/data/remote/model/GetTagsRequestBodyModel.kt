package me.bakhtiyari.cryptocurrency.data.remote.model

import com.squareup.moshi.Json

data class GetTagsRequestBodyModel(
    @Json(name = "limit")
    val limit: Int? = 100,
    @Json(name = "from_id")
    val from_id: Int? = 0,
    @Json(name = "from_date")
    val from_date: String? = "",

)
