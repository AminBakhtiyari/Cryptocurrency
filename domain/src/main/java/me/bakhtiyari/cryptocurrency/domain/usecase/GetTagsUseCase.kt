package me.bakhtiyari.cryptocurrency.domain.usecase

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import me.bakhtiyari.cryptocurrency.domain.errorHandler.ErrorHandler
import me.bakhtiyari.cryptocurrency.domain.model.TagModel
import me.bakhtiyari.cryptocurrency.domain.repository.GetTagsRepository
import me.bakhtiyari.cryptocurrency.domain.utils.CoroutineContextProvider

class GetTagsUseCase(
    private val repository: GetTagsRepository,
    errorHandler: ErrorHandler,
    contextProvider: CoroutineContextProvider
) :
    BaseUseCase<PagingData<TagModel>>(
        errorHandler = errorHandler,
        contextProvider = contextProvider
    ) {

    private lateinit var query: String

    fun setParameters(
        query: String,
    ): GetTagsUseCase {
        this.query = query
        return this
    }

    override suspend fun buildUseCaseObservable(): Flow<PagingData<TagModel>> = repository.getTags(query = query)
}