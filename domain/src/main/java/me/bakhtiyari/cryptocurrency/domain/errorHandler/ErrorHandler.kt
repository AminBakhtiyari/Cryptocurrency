package me.bakhtiyari.cryptocurrency.domain.errorHandler

import me.bakhtiyari.cryptocurrency.domain.model.ErrorEntity


interface ErrorHandler {

    fun getError(throwable: Throwable): ErrorEntity
}