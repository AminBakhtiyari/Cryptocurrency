package me.bakhtiyari.cryptocurrency.data.remote.interceptors

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response


/**
 * a Interceptor class for add all server's needed params to all request
 * implement from Interceptor (okhttp3.Interceptor)
 */
class AllRequestInterceptor(val apiKey: String) : Interceptor {

    /**
     * override function for add all server's needed params to all request
     *
     * @param chain
     *
     * @return Response
     */
    override fun intercept(chain: Interceptor.Chain): Response {
        val original: Request = chain.request()

        val requestBuilder: Request.Builder = original.newBuilder()
            .addHeader("Authorization", apiKey)
            .url(original.url)

        val request: Request = requestBuilder.build()
        return chain.proceed(request)
    }

}
