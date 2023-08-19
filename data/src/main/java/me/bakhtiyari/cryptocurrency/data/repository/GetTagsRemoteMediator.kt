package me.bakhtiyari.cryptocurrency.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import me.bakhtiyari.cryptocurrency.data.cache.datasource.GetTagsCacheDataSource
import me.bakhtiyari.cryptocurrency.data.cache.model.RemoteKeyEntity
import me.bakhtiyari.cryptocurrency.data.cache.model.TagCacheEntity
import me.bakhtiyari.cryptocurrency.data.remote.datasource.GetTagsRemoteDataSource
import me.bakhtiyari.cryptocurrency.data.remote.model.GetTagsRequestBodyModel
import me.bakhtiyari.cryptocurrency.data.remote.model.GetTagsResponseModel
import me.bakhtiyari.cryptocurrency.data.remote.model.TagResponseModel
import me.bakhtiyari.cryptocurrency.data.repository.mapper.TagResponseModelMapper
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class GetTagsRemoteMediator(
    private val cacheDataSource: GetTagsCacheDataSource,
    private val remoteDataSource: GetTagsRemoteDataSource,
    private val mapper: TagResponseModelMapper
) : RemoteMediator<Int, TagCacheEntity>() {

    override suspend fun initialize(): InitializeAction = InitializeAction.LAUNCH_INITIAL_REFRESH

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, TagCacheEntity>
    ): MediatorResult {
        return try {

            val loadKey = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = fetchRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextKey?.minus(0) ?: 0
                }
                LoadType.PREPEND -> {
                    0
                }
                LoadType.APPEND -> {
                    val remoteKeys = fetchRemoteKeyForLastItem(state)
                    val nextKey = remoteKeys?.nextKey
                    nextKey ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                }
            }

            val response = fetchFromRemote(GetTagsRequestBodyModel(state.config.pageSize, loadKey))

            if (loadType == LoadType.REFRESH) {
                clearRemoteKeys()
                clearCached()
            }

            saveRemoteKeys(response.result?.tags?.map {
                RemoteKeyEntity(
                    tagId = it.id,
                    totalCount = response.result.totalCount,
                    nextKey = response.result.nextKey
                )
            } ?: emptyList())
            saveRemoteData(response.result?.tags ?: emptyList())

            MediatorResult.Success(
                endOfPaginationReached = response.result?.nextKey == response.result?.totalCount
            )
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }


    /**
     * Saves retrieved from remote into the persistence storage.
     */
    private suspend fun saveRemoteData(data: List<TagResponseModel>) {
        cacheDataSource.saveTags(data.map { tag -> mapper.mapFrom(tag) })

    }

    /**
     * clear all data from persistence storage.
     */
    private suspend fun clearCached() {
        cacheDataSource.clearAllData()

    }

    private suspend fun clearRemoteKeys() {
        cacheDataSource.clearRemoteKeys()
    }

    /**
     * Fetches [GetTagsResponseModel] from the remote end point.
     */
    private suspend fun fetchFromRemote(
        body: GetTagsRequestBodyModel
    ): GetTagsResponseModel =
        remoteDataSource.getTags(body = body)

    private suspend fun fetchRemoteKeyClosestToCurrentPosition(state: PagingState<Int, TagCacheEntity>): RemoteKeyEntity? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                cacheDataSource.remoteKeyByTagId(id)
            }
        }
    }

    private suspend fun fetchRemoteKeyForLastItem(state: PagingState<Int, TagCacheEntity>): RemoteKeyEntity? {
        return state.pages.lastOrNull {
            it.data.isNotEmpty()
        }?.data?.lastOrNull()?.let { tag ->
            cacheDataSource.remoteKeyByTagId(tag.id ?: 0)
        }
    }

    /**
     * Saves remote key into the persistence storage.
     */
    private suspend fun saveRemoteKeys(remoteKeys: List<RemoteKeyEntity>) {
        cacheDataSource.insertOrReplaceAllRemoteKey(remoteKeys = remoteKeys)

    }



}