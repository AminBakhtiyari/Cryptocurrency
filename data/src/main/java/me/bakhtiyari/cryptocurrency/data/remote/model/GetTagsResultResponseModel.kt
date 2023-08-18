package me.bakhtiyari.cryptocurrency.data.remote.model

import com.squareup.moshi.Json

data class GetTagsResultResponseModel (

    @Json(name = "tags")
    val tags: List<TagResponseModel>?,
    @Json(name = "nextKey")
    val nextKey: Int?,
    @Json(name = "totalCount")
    val totalCount: Int?
)