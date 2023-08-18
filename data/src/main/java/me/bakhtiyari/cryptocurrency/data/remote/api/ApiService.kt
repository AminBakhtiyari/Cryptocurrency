package me.bakhtiyari.cryptocurrency.data.remote.api

import me.bakhtiyari.cryptocurrency.data.remote.model.GetTagsRequestBodyModel
import me.bakhtiyari.cryptocurrency.data.remote.model.GetTagsResponseModel
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("v2/tags")
    suspend fun getTags(
        @Body body: GetTagsRequestBodyModel
    ): GetTagsResponseModel
}