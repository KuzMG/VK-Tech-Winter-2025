package com.example.vk.tech.vk_tech_winter_2025.di.module

import com.example.vk.tech.vk_tech_winter_2025.api.HeaderInterceptor
import com.example.vk.tech.vk_tech_winter_2025.api.PixelsApi
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
        .client(
            OkHttpClient
                .Builder()
                .addInterceptor(HeaderInterceptor())
                .callTimeout(5, TimeUnit.SECONDS)
                .build()
        )
        .baseUrl(PixelsApi.URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(PixelsApi::class.java)
}