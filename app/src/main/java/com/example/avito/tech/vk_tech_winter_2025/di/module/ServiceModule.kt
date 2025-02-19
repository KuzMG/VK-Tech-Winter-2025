package com.example.avito.tech.vk_tech_winter_2025.di.module

import com.example.avito.tech.vk_tech_winter_2025.api.HeaderInterceptor
import com.example.avito.tech.vk_tech_winter_2025.api.PixelsApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class ServiceModule {
    @Singleton
    @Provides
    fun providePixelsApi() = Retrofit
        .Builder()
        .baseUrl(PixelsApi.URL)
        .client(
            OkHttpClient.Builder().connectTimeout(5, TimeUnit.SECONDS)
                .addInterceptor(HeaderInterceptor()).build()
        )
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(PixelsApi::class.java)
}