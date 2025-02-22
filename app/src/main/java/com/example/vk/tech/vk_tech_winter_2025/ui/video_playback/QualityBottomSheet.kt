package com.example.vk.tech.vk_tech_winter_2025.ui.video_playback

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.vk.tech.vk_tech_winter_2025.R
import com.example.vk.tech.vk_tech_winter_2025.database.entity.video_file.VideoFile
import com.example.vk.tech.vk_tech_winter_2025.databinding.QualityBottomSheetBinding
import com.example.vk.tech.vk_tech_winter_2025.databinding.QualityItemBinding
import com.example.vk.tech.vk_tech_winter_2025.utils.appComponent
import com.example.vk.tech.vk_tech_winter_2025.utils.getParcelableArrayCompat
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class QualityBottomSheet : BottomSheetDialogFragment() {
    private lateinit var binding: QualityBottomSheetBinding
    private val viewModel by viewModels<VideoPlaybackViewModel>({requireParentFragment()})

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        videoFiles = arguments?.getParcelableArrayCompat(ARG_VIDEO_FILES) ?: arrayOf()
//    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = QualityBottomSheetBinding.inflate(
            layoutInflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.adapter = Adapter(viewModel.videoFiles ?: listOf())
    }


    companion object {
        const val TAG = "QualityBottomSheet"
        private const val ARG_VIDEO_FILES = "videoFiles"
//        fun newInstance(videoFiles: ArrayList<VideoFile>) = QualityBottomSheet().apply {
//            arguments = Bundle().apply {
//                putParcelableArrayList(ARG_VIDEO_FILES, videoFiles)
//            }
//        }
    }

    inner class ViewHolder(private val binding: QualityItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: VideoFile) {
            binding.qualityTextView.text = item.quality
            binding.resolutionTextView.text =
                getString(R.string.resolution, item.width.toString(), item.height.toString())
            binding.root.setOnClickListener {
                viewModel.setQuality(item)
                this@QualityBottomSheet.dismiss()
            }
        }
    }

    inner class Adapter(private val videoFiles: List<VideoFile>) :
        RecyclerView.Adapter<ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(QualityItemBinding.inflate(layoutInflater, parent, false))


        override fun getItemCount() = videoFiles.size

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.onBind(videoFiles[position])
        }

    }
}