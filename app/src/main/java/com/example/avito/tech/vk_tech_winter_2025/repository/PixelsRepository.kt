package com.example.avito.tech.vk_tech_winter_2025.repository

import com.example.avito.tech.vk_tech_winter_2025.api.PixelsApi
import javax.inject.Inject

class PixelsRepository @Inject constructor(private val pixelsApi: PixelsApi) {
    suspend fun getVideos() = pixelsApi.getVideos()
}