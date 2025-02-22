package com.example.vk.tech.vk_tech_winter_2025.utils

import android.content.Context
import android.view.View
import androidx.fragment.app.Fragment
import com.example.vk.tech.vk_tech_winter_2025.VKTechApp
import com.example.vk.tech.vk_tech_winter_2025.di.AppComponent
import java.util.Locale
import java.util.concurrent.TimeUnit

val Context.appComponent: AppComponent
    get() = when (this) {
        is VKTechApp -> appComponent
        else -> applicationContext.appComponent
    }

val Fragment.appComponent: AppComponent
    get() = requireContext().appComponent

fun View.isVisible(flag: Boolean) = if (flag) {
    this.visibility = View.VISIBLE
} else {
    this.visibility = View.GONE
}

fun secondsFormat(seconds: Int): String {
    val minutes = TimeUnit.SECONDS.toMinutes(seconds.toLong())
    return String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds - minutes * 60)
}