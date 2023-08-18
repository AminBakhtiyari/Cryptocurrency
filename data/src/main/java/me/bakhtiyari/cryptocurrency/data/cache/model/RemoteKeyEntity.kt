package me.bakhtiyari.cryptocurrency.data.cache.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import me.bakhtiyari.cryptocurrency.data.cache.utils.CacheConstants

@Entity(tableName = CacheConstants.REMOTE_KEY_TABLE_NAME)
data class RemoteKeyEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = 0,
    @ColumnInfo(name = "totalCount")
    val totalCount: Int?,
    @ColumnInfo(name = "nextKey")
    val nextKey: Int?
    )