package me.bakhtiyari.cryptocurrency.data.remote.datasource

import me.bakhtiyari.cryptocurrency.data.remote.model.GetTagsRequestBodyModel
import me.bakhtiyari.cryptocurrency.data.remote.model.GetTagsResponseModel


interface GetTagsRemoteDataSource {
    suspend fun getTags(body: GetTagsRequestBodyModel): GetTagsResponseModel
}