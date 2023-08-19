package me.bakhtiyari.cryptocurrency.data.errorHandler


import me.bakhtiyari.cryptocurrency.domain.errorHandler.ErrorHandler
import me.bakhtiyari.cryptocurrency.domain.model.ErrorEntity
import retrofit2.HttpException
import java.io.IOException
import java.net.HttpURLConnection
import javax.inject.Inject

class ErrorHandlerImpl @Inject constructor() : ErrorHandler {
    override fun getError(throwable: Throwable): ErrorEntity {
        return when (throwable) {
            is IOException -> ErrorEntity.ApiError.Network
            is HttpException -> {
                when (throwable.code()) {
                    // not found
                    HttpURLConnection.HTTP_NOT_FOUND -> ErrorEntity.ApiError.NotFound

                    // access denied
                    HttpURLConnection.HTTP_FORBIDDEN -> ErrorEntity.ApiError.AccessDenied

                    // unavailable service
                    HttpURLConnection.HTTP_UNAVAILABLE -> ErrorEntity.ApiError.ServiceUnavailable

                    // all the others will be treated as unknown error
                    else -> ErrorEntity.Unknown
                }
            }
            else -> ErrorEntity.Unknown
        }
    }
}