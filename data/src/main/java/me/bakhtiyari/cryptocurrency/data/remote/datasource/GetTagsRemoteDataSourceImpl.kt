package me.bakhtiyari.cryptocurrency.data.remote.datasource

import me.bakhtiyari.cryptocurrency.data.remote.api.ApiService
import me.bakhtiyari.cryptocurrency.data.remote.model.GetTagsRequestBodyModel
import me.bakhtiyari.cryptocurrency.data.remote.model.GetTagsResponseModel
import javax.inject.Inject

class GetTagsRemoteDataSourceImpl @Inject constructor(
    private val service: ApiService
) : GetTagsRemoteDataSource {

    override suspend fun getTags(body: GetTagsRequestBodyModel): GetTagsResponseModel = service.getTags(body = body)
}