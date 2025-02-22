package com.example.vk.tech.vk_tech_winter_2025.database.entity.video

import androidx.room.Embedded
import androidx.room.Relation
import com.example.vk.tech.vk_tech_winter_2025.database.entity.video_file.VideoFile
import com.example.vk.tech.vk_tech_winter_2025.database.entity.video_file.VideoFileEntity
import com.google.gson.annotations.SerializedName


data class Video(
    val id: Int,
    val duration: Int,
    @Embedded
    val user: User,
    @SerializedName("video_files")
    @Relation(entity = VideoFileEntity::class, parentColumn = "id", entityColumn = "video_id")
    val videoFiles: List<VideoFile>,
    val image: String
)
