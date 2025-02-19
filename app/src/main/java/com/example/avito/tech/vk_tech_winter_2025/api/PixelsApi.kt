package com.example.avito.tech.vk_tech_winter_2025.api

import android.media.session.MediaSession.Token
import com.example.avito.tech.vk_tech_winter_2025.api.dto.response.VideoResponse
import retrofit2.Response
import retrofit2.http.GET

interface PixelsApi {
    companion object{
        const val HEADER_KEY = "Authorization"
        const val TOKEN = "JkXdZErNI3fwiGVXwRO7rZaPgBq39S5K4rXYD2LmNY58SoDg3f9ibWul"
        const val URL = "https://api.pexels.com/"
    }
    @GET("videos/popular")
    suspend fun getVideos(): Response<VideoResponse>
}