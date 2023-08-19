package me.bakhtiyari.cryptocurrency.data.cache.datasource

import androidx.paging.PagingSource
import me.bakhtiyari.cryptocurrency.data.cache.model.RemoteKeyEntity
import me.bakhtiyari.cryptocurrency.data.cache.model.TagCacheEntity


interface GetTagsCacheDataSource {
    fun getTags(query: String): PagingSource<Int, TagCacheEntity>
    suspend fun saveTags(tags: List<TagCacheEntity>)
    suspend fun clearAllData()
    suspend fun insertOrReplaceRemoteKey(remoteKey: RemoteKeyEntity)
    suspend fun insertOrReplaceAllRemoteKey(remoteKeys: List<RemoteKeyEntity>)
    suspend fun remoteKeyByTagId(tagId: Long): RemoteKeyEntity
    suspend fun clearRemoteKeys()
}