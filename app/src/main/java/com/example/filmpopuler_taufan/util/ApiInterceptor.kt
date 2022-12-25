package com.example.filmpopuler_taufan.util

import okhttp3.Interceptor
import okhttp3.Response

//interceptor untuk menambahkan kueri kunci api untuk setiap permintaan
class ApiInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val originalHttpUrl = original.url
        val requestBuilder = original.newBuilder().url(
            originalHttpUrl.newBuilder().addQueryParameter(
                API_KEY,
                API_VALUE
            ).build()
        )
        return chain.proceed(requestBuilder.build())
    }
}