package com.example.vk.tech.vk_tech_winter_2025.database.entity.video

import androidx.room.Embedded
import androidx.room.Entity

@Entity(
    tableName = "video",
    primaryKeys = ["id"]
)
data class VideoEntity(
    val id: Int,
    val duration: Int,
    @Embedded
    val user: User,
    val image: String
)
