package me.bakhtiyari.cryptocurrency.domain.utils

import me.bakhtiyari.cryptocurrency.domain.model.ErrorModel


sealed class Result<T> {

    data class Success<T>(val data: T) : Result<T>()

    data class Error<T>(val error: ErrorModel) : Result<T>()

    fun handleResult(successBlock: (T) -> Unit = {}, failureBlock: (ErrorModel) -> Unit = {}) {
        when (this) {
            is Success -> successBlock(data)
            is Error -> failureBlock(error)
        }
    }
}