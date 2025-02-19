package com.example.avito.tech.vk_tech_winter_2025.ui.video_list.data

import com.example.avito.tech.vk_tech_winter_2025.api.dto.model.Video

data class Result(
    val status: Status,
    val videos: List<Video>? = null,
    val error: String? = null,
)

