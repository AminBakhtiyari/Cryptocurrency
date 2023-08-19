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


    override fun getTags(query: String): PagingSource<Int, TagCacheEntity> = tagDao.getTags(query)

    override suspend fun saveTags(tags: List<TagCacheEntity>) {
        tagDao.addTags(*tags.toTypedArray())
    }

    override suspend fun clearAllData() {
        tagDao.nukeTable()
    }

    override suspend fun insertOrReplaceRemoteKey(remoteKey: RemoteKeyEntity) {
        remoteKeyDao.insertOrReplace(remoteKey)
    }

    override suspend fun insertOrReplaceAllRemoteKey(remoteKeys: List<RemoteKeyEntity>) {
        remoteKeyDao.insertOrReplaceAll(*remoteKeys.toTypedArray())
    }

    override suspend fun remoteKeyByTagId(tagId: Long): RemoteKeyEntity = remoteKeyDao.remoteKeyByTagId(tagId)

    override suspend fun clearRemoteKeys() {
        remoteKeyDao.clearRemoteKeys()
    }
}