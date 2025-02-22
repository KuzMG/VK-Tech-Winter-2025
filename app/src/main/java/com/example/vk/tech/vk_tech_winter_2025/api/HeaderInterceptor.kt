package com.example.vk.tech.vk_tech_winter_2025.api

import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response = chain.proceed(
        chain.request().newBuilder()
            .addHeader(PixelsApi.HEADER_KEY, PixelsApi.TOKEN)
            .build()
    )

}