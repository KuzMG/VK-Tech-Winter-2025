package com.example.vk.tech.vk_tech_winter_2025

import android.app.Application
import com.example.vk.tech.vk_tech_winter_2025.di.AppComponent
import com.example.vk.tech.vk_tech_winter_2025.di.DaggerAppComponent

class VKTechApp : Application() {
    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder().application(this).build()
    }
}