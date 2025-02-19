package com.example.avito.tech.vk_tech_winter_2025.ui.video_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.avito.tech.vk_tech_winter_2025.api.dto.model.Video
import com.example.avito.tech.vk_tech_winter_2025.databinding.FragmentVideoListBinding
import com.example.avito.tech.vk_tech_winter_2025.databinding.VideoItemBinding
import com.example.avito.tech.vk_tech_winter_2025.ui.video_list.data.Status
import com.example.avito.tech.vk_tech_winter_2025.utils.appComponent
import com.example.avito.tech.vk_tech_winter_2025.utils.isVisible
import com.example.avito.tech.vk_tech_winter_2025.utils.secondsFormat
import com.squareup.picasso.Picasso


class VideoListFragment : Fragment() {

    private lateinit var binding: FragmentVideoListBinding
    private val viewModel by viewModels<VideoListViewModel> {
        appComponent.multiViewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentVideoListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.videoLiveData.observe(viewLifecycleOwner) { result ->
            result.run {
                error?.let {
                    binding.exceptionTextView.text = it
                }
                videos?.let {
                    binding.recyclerView.adapter = VideoAdapter(it)
                }
                binding.progressIndicator.isVisible(status == Status.LOADING)
                binding.recyclerView.isVisible(status == Status.OK)
                binding.exceptionTextView.isVisible(status == Status.ERROR)

                binding.swipeRefreshLayout.isRefreshing = status == Status.LOADING
            }
        }


        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.getTracks()
        }
    }

    class ViewHolder(private val binding: VideoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(video: Video) {
            Picasso.get().load(video.image).into(binding.imageView)
            binding.titleTextView.text = video.user.name
            binding.durationTextView.text = secondsFormat(video.duration)
        }

    }

    inner class VideoAdapter(private val videos: List<Video>) : RecyclerView.Adapter<ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(VideoItemBinding.inflate(layoutInflater, parent, false))


        override fun getItemCount() = videos.size

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.onBind(videos[position])
        }

    }
}