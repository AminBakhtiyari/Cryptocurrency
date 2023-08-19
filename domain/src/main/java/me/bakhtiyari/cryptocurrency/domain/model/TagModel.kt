package me.bakhtiyari.cryptocurrency.domain.model

data class TagModel(
    val id: Long?,
    val tagType: Int?,
    val tagTypeName: String?,
    val name: String?,
    val symbol: String?,
    val rank: Long?,
    val logoUrl: String?,
    val isWatchlist: Boolean?
)
