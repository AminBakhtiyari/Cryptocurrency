package me.bakhtiyari.cryptocurrency.data.cache.datasource

import androidx.paging.PagingSource
import me.bakhtiyari.cryptocurrency.data.cache.dao.RemoteKeyDao
import me.bakhtiyari.cryptocurrency.data.cache.dao.TagDao
import me.bakhtiyari.cryptocurrency.data.cache.model.RemoteKeyEntity
import me.bakhtiyari.cryptocurrency.data.cache.model.TagCacheEntity
import javax.inject.Inject

class GetTagsCacheDataSourceImpl @Inject constructor(
    private val tagDao: TagDao,
    private val remoteKeyDao: RemoteKeyDao
) : GetTagsCacheDataSource {


    override suspend fun getTags(): PagingSource<Int, TagCacheEntity> = tagDao.getTags()

    override suspend fun saveTags(tags: List<TagCacheEntity>) {
        tagDao.addTags(*tags.toTypedArray())
    }

    override suspend fun clearAllData() {
        tagDao.nukeTable()
    }

    override suspend fun insertOrReplaceRemoteKey(remoteKey: RemoteKeyEntity) {
        remoteKeyDao.insertOrReplace(remoteKey)
    }

    override suspend fun remoteKeyByQuery(query: String): RemoteKeyEntity = remoteKeyDao.remoteKeyByQuery(query)

    override suspend fun clearRemoteKeyByQuery(query: String) {
        remoteKeyDao.deleteByQuery(query)
    }
}