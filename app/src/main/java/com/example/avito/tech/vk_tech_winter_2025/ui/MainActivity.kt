package com.example.avito.tech.vk_tech_winter_2025.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.avito.tech.vk_tech_winter_2025.R
import com.example.avito.tech.vk_tech_winter_2025.ui.video_list.VideoListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container_view, VideoListFragment())
            .commit()

    }


}