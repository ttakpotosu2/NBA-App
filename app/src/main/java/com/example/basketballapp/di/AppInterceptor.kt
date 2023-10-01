package com.example.basketballapp.di

import okhttp3.Interceptor
import okhttp3.Response

class AppInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader("X-RapidAPI-Key", "aba75077c8msh2c58f949f2ad73dp11f5f0jsndc45c071316b")
            .addHeader("X-RapidAPI-Host", "api-nba-v1.p.rapidapi.com")
            .build()
        return chain.proceed(request)
    }
}