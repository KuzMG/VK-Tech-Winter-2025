package com.example.vk.tech.vk_tech_winter_2025.di.module

import android.app.Application
import androidx.room.Room
import com.example.vk.tech.vk_tech_winter_2025.database.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DBModule {
    @Singleton
    @Provides
    fun getDB(app: Application) =
        Room.databaseBuilder(
            app,
            AppDatabase::class.java,
            AppDatabase.DATABASE_NAME
        ).allowMainThreadQueries().build()

    @Singleton
    @Provides
    fun getVideoDao(db: AppDatabase) = db.videoDao()

    @Singleton
    @Provides
    fun getVideoFilesDao(db: AppDatabase) = db.videoFilesDao()
}