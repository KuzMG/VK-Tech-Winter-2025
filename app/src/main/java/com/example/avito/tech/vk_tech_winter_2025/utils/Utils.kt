package com.example.avito.tech.vk_tech_winter_2025.utils

import android.content.Context
import androidx.fragment.app.Fragment
import com.example.avito.tech.vk_tech_winter_2025.VKTechApp
import com.example.avito.tech.vk_tech_winter_2025.di.AppComponent

val Context.appComponent: AppComponent
    get() =  when(this){
        is VKTechApp -> appComponent
        else -> applicationContext.appComponent
    }

val Fragment.appComponent: AppComponent
    get() = requireContext().appComponent