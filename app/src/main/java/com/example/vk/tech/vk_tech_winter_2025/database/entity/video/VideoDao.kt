package com.example.vk.tech.vk_tech_winter_2025.database.entity.video

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface VideoDao {

    @Query("select * from video")
    fun getVideos(): List<Video>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(videoEntity: VideoEntity)
}