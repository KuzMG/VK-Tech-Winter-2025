package com.example.avito.tech.vk_tech_winter_2025.di.module

import androidx.lifecycle.ViewModel
import com.example.avito.tech.vk_tech_winter_2025.ui.video_list.VideoListViewModel
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass

@Module
interface ViewModelModule {
    @[Binds IntoMap ViewModelKey(VideoListViewModel::class)]
    fun provideVideoListViewModel(viewModel: VideoListViewModel): ViewModel
}

@MustBeDocumented
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)