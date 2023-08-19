package me.bakhtiyari.cryptocurrency.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.bakhtiyari.cryptocurrency.domain.di.createGetTagsUseCase
import me.bakhtiyari.cryptocurrency.domain.errorHandler.ErrorHandler
import me.bakhtiyari.cryptocurrency.domain.repository.GetTagsRepository
import me.bakhtiyari.cryptocurrency.domain.usecase.GetTagsUseCase
import me.bakhtiyari.cryptocurrency.domain.utils.CoroutineContextProvider
import me.bakhtiyari.cryptocurrency.domain.utils.CoroutineContextProviderImp
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Provides
    @Singleton
    fun provideGetTagsUseCase(
        repository: GetTagsRepository,
        errorHandler: ErrorHandler,
        contextProvider: CoroutineContextProvider
    ): GetTagsUseCase {
        return createGetTagsUseCase(
            repository = repository,
            errorHandler = errorHandler,
            contextProvider = contextProvider
        )
    }

    @Provides
    @Singleton
    fun provideCoroutineContextProvider(contextProvider: CoroutineContextProviderImp): CoroutineContextProvider =
        contextProvider
}