package com.example.vk.tech.vk_tech_winter_2025.database.entity.video_file

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import com.example.vk.tech.vk_tech_winter_2025.database.entity.video.VideoEntity

@Entity(
    tableName = "video_file",
    primaryKeys = ["id"],
    foreignKeys = [ForeignKey(
        VideoEntity::class,
        parentColumns = ["id"],
        childColumns = ["video_id"]
    )]
)
data class VideoFileEntity(
    val id: Int,
    @ColumnInfo("video_id")
    val videoId: Int,
    val quality: String,
    val link: String,
    val width: Int,
    val height: Int
)
