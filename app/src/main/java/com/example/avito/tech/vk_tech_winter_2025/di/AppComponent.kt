package com.example.avito.tech.vk_tech_winter_2025.di

import android.app.Application
import com.example.avito.tech.vk_tech_winter_2025.di.module.ServiceModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ServiceModule::class])
interface AppComponent {
    @Component.Builder
    interface Builder{
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }
}