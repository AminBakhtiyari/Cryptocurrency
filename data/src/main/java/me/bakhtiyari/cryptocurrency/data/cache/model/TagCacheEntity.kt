package me.bakhtiyari.cryptocurrency.data.cache.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import me.bakhtiyari.cryptocurrency.data.cache.utils.CacheConstants

@Entity(tableName = CacheConstants.TAGS_TABLE_NAME)
data class TagCacheEntity(
    @PrimaryKey
    val id: Long?,
    val tagType: Int?,
    val tagTypeName: String?,
    val name: String?,
    val symbol: String?,
    val rank: Long?,
    val logoUrl: String?,
    val isWatchlist: Boolean?
)
