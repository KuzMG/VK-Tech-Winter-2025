package com.example.avito.tech.vk_tech_winter_2025.repository

import com.example.avito.tech.vk_tech_winter_2025.api.PixelsApi
import com.example.avito.tech.vk_tech_winter_2025.ui.video_list.data.Result
import com.example.avito.tech.vk_tech_winter_2025.ui.video_list.data.Status
import javax.inject.Inject

class PixelsRepository @Inject constructor(private val pixelsApi: PixelsApi) {
    suspend fun getVideos(): Result = try {
        val response = pixelsApi.getVideos()
        if (response.code() == 200) {
            Result(Status.OK, videos = response.body()?.videos)
        } else {
            Result(Status.ERROR, error = "${response.code()} ${response.errorBody()}")
        }
    } catch (e: Exception) {
        Result(Status.ERROR, error = e.message)
    }


}