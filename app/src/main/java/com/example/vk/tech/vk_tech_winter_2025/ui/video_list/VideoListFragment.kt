package com.example.vk.tech.vk_tech_winter_2025.ui.video_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.vk.tech.vk_tech_winter_2025.R
import com.example.vk.tech.vk_tech_winter_2025.database.entity.video.Video
import com.example.vk.tech.vk_tech_winter_2025.databinding.FragmentVideoListBinding
import com.example.vk.tech.vk_tech_winter_2025.databinding.VideoItemBinding
import com.example.vk.tech.vk_tech_winter_2025.ui.data.Status
import com.example.vk.tech.vk_tech_winter_2025.ui.video_playback.VideoPlaybackFragment
import com.example.vk.tech.vk_tech_winter_2025.utils.appComponent
import com.example.vk.tech.vk_tech_winter_2025.utils.isVisible
import com.example.vk.tech.vk_tech_winter_2025.utils.secondsFormat
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
                val errorMessage = StringBuilder()
                errorId?.let {
                    val message = getString(it)
                    errorMessage.append(message)
                    errorMessage.append("\n")
                }
                error?.let {
                    errorMessage.append(it)
                    binding.exceptionTextView.text = it
                }
                if (errorMessage.isNotBlank())
                    binding.exceptionTextView.text = errorMessage.toString()

                binding.recyclerView.isVisible(status == Status.OK)
                videos?.let {
                    binding.recyclerView.adapter = VideoAdapter(it)
                    binding.recyclerView.isVisible(true)
                }

                binding.exceptionScrollView.isVisible(status == Status.ERROR)
                binding.swipeRefreshLayout.isRefreshing = (status == Status.LOADING)
            }
        }

        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.getVideos()
        }
    }

    inner class ViewHolder(private val binding: VideoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(video: Video) {
            Picasso.get().load(video.image).into(binding.imageView)
            binding.titleTextView.text = video.user.name
            binding.durationTextView.text = secondsFormat(video.duration)
            binding.root.setOnClickListener {
                parentFragmentManager.commit {
                    replace(
                        R.id.fragment_container_view,
                        VideoPlaybackFragment.newInstance(video.id)
                    )
                    addToBackStack(null)
                }
            }
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