package me.bakhtiyari.cryptocurrency.data.remote.model


import com.squareup.moshi.Json

data class GetTagsResponseModel(
    @Json(name = "result")
    val result: GetTagsResultResponseModel?
)