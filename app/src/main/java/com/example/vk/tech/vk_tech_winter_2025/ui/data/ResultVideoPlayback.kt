package com.example.vk.tech.vk_tech_winter_2025.ui.data

import com.example.vk.tech.vk_tech_winter_2025.database.entity.video.Video

data class ResultVideoPlayback(
    val status: Status,
    val video: Video? = null,
    val error: String? = null,
)

