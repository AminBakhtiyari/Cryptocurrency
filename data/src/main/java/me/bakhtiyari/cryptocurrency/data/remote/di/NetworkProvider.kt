package me.bakhtiyari.cryptocurrency.data.remote.di

import me.bakhtiyari.cryptocurrency.data.remote.api.ApiService
import me.bakhtiyari.cryptocurrency.data.remote.interceptors.AllRequestInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit


fun createService(isDebug: Boolean, baseUrl: String, apiKey: String): ApiService {
    val loggingInterceptor = provideLoggingInterceptor(isDebug = isDebug)
    val allRequestInterceptor = provideAllRequestInterceptor(apiKey = apiKey)
    val okHttpClient = provideOkHttpClient(
        loggingInterceptor = loggingInterceptor,
        allRequestInterceptor = allRequestInterceptor
    )
    val retrofit = provideRetrofit(baseUrl = baseUrl, okHttpClient = okHttpClient)
    return provideApiService(retrofit)
}

/**
 * provide ClientAuthInterceptor for dependency injection with *Hilt*
 *
 * @return the ClientAuthInterceptor object <ClientAuthInterceptor>
 *
 * @see AllRequestInterceptor
 */
fun provideAllRequestInterceptor(apiKey: String): AllRequestInterceptor =
    AllRequestInterceptor(apiKey = apiKey)


/**
 * provide HttpLoggingInterceptor for dependency injection with *Hilt*
 *
 * @return the HttpLoggingInterceptor object <HttpLoggingInterceptor>
 *
 * @see HttpLoggingInterceptor
 */
fun provideLoggingInterceptor(isDebug: Boolean): HttpLoggingInterceptor {
    val logger = HttpLoggingInterceptor()
    logger.level = if (isDebug) {
        HttpLoggingInterceptor.Level.BODY
    } else {
        HttpLoggingInterceptor.Level.NONE
    }
    return logger
}


/**
 * provide OkHttpClient for dependency injection with *Hilt*
 *
 * @param loggingInterceptor: a HttpLoggingInterceptor object, injected
 *
 * @return the OkHttpClient built object <OkHttpClient>
 *
 * @see HttpLoggingInterceptor
 */
fun provideOkHttpClient(
    loggingInterceptor: HttpLoggingInterceptor,
    allRequestInterceptor: AllRequestInterceptor
): OkHttpClient {

    return OkHttpClient()
        .newBuilder()
        .followRedirects(true)
        .followSslRedirects(true)
        .retryOnConnectionFailure(true)
        .connectTimeout(OK_HTTP_TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(OK_HTTP_TIMEOUT, TimeUnit.SECONDS)
        .writeTimeout(OK_HTTP_TIMEOUT, TimeUnit.SECONDS)
        .addInterceptor(loggingInterceptor)
        .addInterceptor(allRequestInterceptor)
        .build()
}


/**
 * provide Retrofit for dependency injection with *Hilt*
 *
 * @param okHttpClient: a OkHttpClient object, injected
 *
 * @return the Retrofit built object <Retrofit>
 *
 * @see OkHttpClient
 * @see Retrofit
 */
fun provideRetrofit(baseUrl: String, okHttpClient: OkHttpClient): Retrofit {

    return Retrofit.Builder().baseUrl(baseUrl).client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create()).build()
}

/**
 * provide ApiService for dependency injection with *Hilt*
 *
 * @param retrofit: a Retrofit object, injected
 *
 * @return object created retrofit from retrofit's interface <PaymentApi>
 *
 * @see Retrofit
 * @see ApiService
 */
fun provideApiService(retrofit: Retrofit): ApiService =
    retrofit.create(ApiService::class.java)


private const val OK_HTTP_TIMEOUT = 5L