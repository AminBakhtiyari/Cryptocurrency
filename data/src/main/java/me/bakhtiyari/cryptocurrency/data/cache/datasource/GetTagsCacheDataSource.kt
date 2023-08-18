package me.bakhtiyari.cryptocurrency.data.cache.datasource

import androidx.paging.PagingSource
import me.bakhtiyari.cryptocurrency.data.cache.model.RemoteKeyEntity
import me.bakhtiyari.cryptocurrency.data.cache.model.TagCacheEntity


interface GetTagsCacheDataSource {
    suspend fun getTags(): PagingSource<Int, TagCacheEntity>
    suspend fun saveTags(tags: List<TagCacheEntity>)
    suspend fun clearAllData()
    suspend fun insertOrReplaceRemoteKey(remoteKey: RemoteKeyEntity)
    suspend fun remoteKeyByQuery(query: String): RemoteKeyEntity
    suspend fun clearRemoteKeyByQuery(query: String)
}