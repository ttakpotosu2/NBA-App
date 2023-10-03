package com.example.basketballapp.di

import com.example.basketballapp.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class AppInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader("X-RapidAPI-Key", BuildConfig.API_KEY)
            .addHeader("X-RapidAPI-Host", "api-nba-v1.p.rapidapi.com")
            .build()
        return chain.proceed(request)
    }
}