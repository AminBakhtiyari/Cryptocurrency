package me.bakhtiyari.cryptocurrency.domain.di

import me.bakhtiyari.cryptocurrency.domain.errorHandler.ErrorHandler
import me.bakhtiyari.cryptocurrency.domain.repository.GetTagsRepository
import me.bakhtiyari.cryptocurrency.domain.usecase.GetTagsUseCase
import me.bakhtiyari.cryptocurrency.domain.utils.CoroutineContextProvider


/**
 * provide SearchImageUseCase for dependency injection with *Hilt*
 *
 * @return the GetTagsUseCase object <GetTagsUseCase>
 *
 * @see GetTagsUseCase
 */
fun createGetTagsUseCase(
    repository: GetTagsRepository,
    errorHandler: ErrorHandler,
    contextProvider: CoroutineContextProvider
): GetTagsUseCase =
    GetTagsUseCase(
        repository = repository,
        errorHandler = errorHandler,
        contextProvider = contextProvider
    )
