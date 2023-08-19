package me.bakhtiyari.cryptocurrency.domain.model

sealed interface ErrorEntity {

    sealed class ApiError : ErrorEntity {

        object Network : ApiError()

        object NotFound : ApiError()

        object AccessDenied : ApiError()

        object ServiceUnavailable : ApiError()
    }

    object Paging : ErrorEntity

    object Unknown : ErrorEntity

}