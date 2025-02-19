package com.example.avito.tech.vk_tech_winter_2025.api.dto.model

data class Video(
    val duration: Int,
    val user: User,
    val videoFiles: List<VideoFile>,
    val image: String
)
