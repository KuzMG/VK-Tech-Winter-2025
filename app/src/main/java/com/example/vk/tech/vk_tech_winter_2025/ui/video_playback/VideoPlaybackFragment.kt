package com.example.vk.tech.vk_tech_winter_2025.ui.video_playback

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.OptIn
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import com.example.vk.tech.vk_tech_winter_2025.R
import com.example.vk.tech.vk_tech_winter_2025.databinding.FragmentVideoPlaybackBinding
import com.example.vk.tech.vk_tech_winter_2025.utils.appComponent
import com.example.vk.tech.vk_tech_winter_2025.utils.isVisible

private const val ARG_ID = "id"

class VideoPlaybackFragment : Fragment() {
    private lateinit var binding: FragmentVideoPlaybackBinding
    private lateinit var player: ExoPlayer
    private val viewModel by viewModels<VideoPlaybackViewModel>({this}) {
        val id = arguments?.getInt(ARG_ID) ?: 0
        appComponent.videoPlaybackFactoryHelper.create(id)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        player = ExoPlayer.Builder(requireContext()).build()
        player.playWhenReady = true
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentVideoPlaybackBinding.inflate(layoutInflater, container, false)
        binding.playerView.player = player
        return binding.root
    }

    @OptIn(UnstableApi::class)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.playerView.setControllerAnimationEnabled(false)

        viewModel.videoLiveData.observe(viewLifecycleOwner) { res ->
            res.run {
                video?.let {
                   viewModel.videoFiles = it.videoFiles
                    viewModel.setQuality(it.videoFiles[it.videoFiles.size-1])
                }
                error?.let {
                    Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
                }
            }
        }

        viewModel.qualityLiveData.observe(viewLifecycleOwner){
            if(viewModel.time< player.currentPosition)
                viewModel.time = player.currentPosition
            player.setMediaItem(MediaItem.fromUri(it.link))
            player.seekTo(viewModel.time)
            player.prepare()
            if (viewModel.isPlaying) {
                player.play()
            } else {
                player.pause()
            }
        }
    }

    override fun onStart() {
        super.onStart()
       binding.root.findViewById<ImageView>(R.id.quality_image_view).setOnClickListener {
           viewModel.time = player.currentPosition
           QualityBottomSheet().show(childFragmentManager,QualityBottomSheet.TAG)
       }
    }

    override fun onStop() {
        super.onStop()
        viewModel.isPlaying = player.isPlaying
        player.pause()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.time = player.currentPosition
        player.release()
    }

    companion object {
        @JvmStatic
        fun newInstance(id: Int) =
            VideoPlaybackFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_ID, id)
                }
            }
    }
}