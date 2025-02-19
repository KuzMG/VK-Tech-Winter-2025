package com.example.avito.tech.vk_tech_winter_2025

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.avito.tech.vk_tech_winter_2025.databinding.ActivityVideoListBinding
import com.example.avito.tech.vk_tech_winter_2025.databinding.VideoItemBinding

class VideoListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityVideoListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVideoListBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }

    class ViewHolder(private val binding: VideoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    inner class VideoRecyclerView() : RecyclerView.Adapter<ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(VideoItemBinding.inflate(layoutInflater, parent, false))


        override fun getItemCount(): Int {
            TODO("Not yet implemented")
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            TODO("Not yet implemented")
        }

    }
}