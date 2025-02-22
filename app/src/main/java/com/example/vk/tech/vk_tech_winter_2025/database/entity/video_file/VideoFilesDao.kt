package com.example.vk.tech.vk_tech_winter_2025.database.entity.video_file

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface VideoFilesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(videoFilesEntities: List<VideoFileEntity>)
}