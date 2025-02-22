package com.example.vk.tech.vk_tech_winter_2025.di

import android.app.Application
import com.example.vk.tech.vk_tech_winter_2025.di.module.DBModule
import com.example.vk.tech.vk_tech_winter_2025.di.module.ServiceModule
import com.example.vk.tech.vk_tech_winter_2025.di.module.ViewModelModule
import com.example.vk.tech.vk_tech_winter_2025.ui.video_playback.VideoPlaybackViewModel
import com.example.vk.tech.vk_tech_winter_2025.view_model.MultiViewModelFactory
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ServiceModule::class, ViewModelModule::class, DBModule::class])
interface AppComponent {
    val multiViewModelFactory: MultiViewModelFactory
    val videoPlaybackFactoryHelper: VideoPlaybackViewModel.FactoryHelper

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }
}