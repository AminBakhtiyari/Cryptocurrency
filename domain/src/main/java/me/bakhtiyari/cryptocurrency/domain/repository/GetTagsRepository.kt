package me.bakhtiyari.cryptocurrency.domain.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import me.bakhtiyari.cryptocurrency.domain.model.TagModel


interface GetTagsRepository {
    suspend fun getTags(query: String): Flow<PagingData<TagModel>>
}