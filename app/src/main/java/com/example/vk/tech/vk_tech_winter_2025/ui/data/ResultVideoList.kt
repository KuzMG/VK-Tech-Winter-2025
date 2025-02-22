package com.example.vk.tech.vk_tech_winter_2025.ui.data

import androidx.annotation.StringRes
import com.example.vk.tech.vk_tech_winter_2025.database.entity.video.Video

data class ResultVideoList(
    val status: Status,
    val videos: List<Video>? = null,
    val error: String? = null,
    @StringRes
    val errorId: Int? = null,
)

