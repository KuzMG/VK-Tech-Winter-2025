package com.example.vk.tech.vk_tech_winter_2025.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.vk.tech.vk_tech_winter_2025.database.entity.video.VideoDao
import com.example.vk.tech.vk_tech_winter_2025.database.entity.video.VideoEntity
import com.example.vk.tech.vk_tech_winter_2025.database.entity.video_file.VideoFileEntity
import com.example.vk.tech.vk_tech_winter_2025.database.entity.video_file.VideoFilesDao

@Database(
    version = 1,
    entities = [
        VideoFileEntity::class,
        VideoEntity::class
    ]
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun videoDao(): VideoDao
    abstract fun videoFilesDao(): VideoFilesDao

    companion object {
        const val DATABASE_NAME = "VKDB"
    }
}