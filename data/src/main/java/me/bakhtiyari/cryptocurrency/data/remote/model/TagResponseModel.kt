package me.bakhtiyari.cryptocurrency.data.remote.model

import com.squareup.moshi.Json

data class TagResponseModel(
    @Json(name = "id")
    val id: Long?,
    @Json(name = "tagType")
    val tagType: Int?,
    @Json(name = "tagTypeName")
    val tagTypeName: String?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "symbol")
    val symbol: String?,
    @Json(name = "rank")
    val rank: Long?,
    @Json(name = "logoUrl")
    val logoUrl: String?,
    @Json(name = "isWatchlist")
    val isWatchlist: Boolean?
)
