package me.bakhtiyari.cryptocurrency.domain.usecase

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import me.bakhtiyari.cryptocurrency.domain.errorHandler.ErrorHandler
import me.bakhtiyari.cryptocurrency.domain.model.ErrorModel
import me.bakhtiyari.cryptocurrency.domain.utils.CoroutineContextProvider
import kotlin.coroutines.CoroutineContext
import me.bakhtiyari.cryptocurrency.domain.utils.Result

abstract class BaseUseCase<T>(
    private val errorHandler: ErrorHandler,
    private val contextProvider: CoroutineContextProvider
) : CoroutineScope {

    private val parentJob = SupervisorJob()

    override val coroutineContext: CoroutineContext
        get() = parentJob + contextProvider.io


    abstract suspend fun buildUseCaseObservable(): Flow<T>

    fun execute(
        onResponse: (Result<T>) -> Unit,
    ) {

        launch {
            buildUseCaseObservable()
                .catch { e ->
                    e.stackTrace
                    val errorModel = ErrorModel(
                        throwable = e,
                        type = errorHandler.getError(e),
                        originalMessage = e.message
                    )
                    onResponse(Result.Error(errorModel))
                }
                .collect {
                    onResponse(Result.Success(it))
                }
        }
    }

}