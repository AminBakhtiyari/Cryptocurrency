package me.bakhtiyari.cryptocurrency.data.repository

import androidx.paging.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import me.bakhtiyari.cryptocurrency.data.cache.datasource.GetTagsCacheDataSource
import me.bakhtiyari.cryptocurrency.data.remote.datasource.GetTagsRemoteDataSource
import me.bakhtiyari.cryptocurrency.data.repository.mapper.TagModelMapper
import me.bakhtiyari.cryptocurrency.data.repository.mapper.TagResponseModelMapper
import me.bakhtiyari.cryptocurrency.domain.model.TagModel
import me.bakhtiyari.cryptocurrency.domain.repository.GetTagsRepository
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class GetTagsRepositoryImpl @Inject constructor(
    private val cacheDataSource: GetTagsCacheDataSource,
    private val remoteDataSource: GetTagsRemoteDataSource,
    private val tagModelMapper: TagModelMapper,
    private val tagResponseModelMapper: TagResponseModelMapper
) : GetTagsRepository {

    companion object {
        private const val PAGE_SIZE = 100
    }

    override suspend fun getTags(query: String): Flow<PagingData<TagModel>> = Pager(
        config = PagingConfig(
            pageSize = PAGE_SIZE,
            enablePlaceholders = false
        ),
        pagingSourceFactory = {
            cacheDataSource.getTags(query)
        },
        remoteMediator = GetTagsRemoteMediator(
            cacheDataSource = cacheDataSource,
            remoteDataSource = remoteDataSource,
            mapper = tagResponseModelMapper
        ),
        initialKey = 0
    ).flow.map { pagingData ->
        pagingData.map { tagCacheEntity ->
            tagModelMapper.mapTo(tagCacheEntity)
        }
    }
}