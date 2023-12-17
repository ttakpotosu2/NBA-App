package com.example.basketballapp.di

import com.example.basketballapp.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class AppInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader(name = "X-RapidAPI-Key", value = BuildConfig.API_KEY)
            .addHeader(name = "X-RapidAPI-Host", value = "api-nba-v1.p.rapidapi.com")
            .build()
        return chain.proceed(request)
    }
}