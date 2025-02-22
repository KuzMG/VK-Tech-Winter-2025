package com.example.vk.tech.vk_tech_winter_2025.api

import com.example.vk.tech.vk_tech_winter_2025.api.dto.response.VideoResponse
import com.example.vk.tech.vk_tech_winter_2025.database.entity.video.Video
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PixelsApi {
    companion object {
        const val HEADER_KEY = "Authorization"
        const val TOKEN = "JkXdZErNI3fwiGVXwRO7rZaPgBq39S5K4rXYD2LmNY58SoDg3f9ibWul"
        const val URL = "https://api.pexels.com/"
    }

    @GET("videos/popular")
    suspend fun getVideos(): Response<VideoResponse>

    @GET("videos/videos/{id}")
    suspend fun getVideo(@Path("id") id: Int): Response<Video>

}