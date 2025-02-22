package com.example.vk.tech.vk_tech_winter_2025.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.example.vk.tech.vk_tech_winter_2025.R
import com.example.vk.tech.vk_tech_winter_2025.ui.video_list.VideoListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null)
            supportFragmentManager.commit {
                replace(R.id.fragment_container_view, VideoListFragment())
            }
    }


}