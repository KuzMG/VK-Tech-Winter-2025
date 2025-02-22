package com.example.vk.tech.vk_tech_winter_2025.ui.video_playback

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.vk.tech.vk_tech_winter_2025.database.entity.video_file.VideoFile
import com.example.vk.tech.vk_tech_winter_2025.repository.PixelsRepository
import com.example.vk.tech.vk_tech_winter_2025.ui.data.ResultVideoPlayback
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class VideoPlaybackViewModel @Inject constructor(
    private val id: Int,
    private val pixelsRepository: PixelsRepository
) : ViewModel() {
    var videoFiles: List<VideoFile>? = null
    var time = 0L
    var isPlaying = true
    val videoLiveData: LiveData<ResultVideoPlayback>
        get() = mutableVideoLiveData
    private val mutableVideoLiveData = MutableLiveData<ResultVideoPlayback>()

    val qualityLiveData: LiveData<VideoFile>
        get() = mutableQualityLiveData
    private val mutableQualityLiveData = MutableLiveData<VideoFile>()

    init {
        getVideo()
    }

    fun setQuality(videoFile: VideoFile){
        mutableQualityLiveData.value = videoFile
    }

    private fun getVideo() {
        viewModelScope.launch(Dispatchers.IO) {
            mutableVideoLiveData.postValue(pixelsRepository.getVideo(id))
        }
    }

    class ViewModelFactory @AssistedInject constructor(
        @Assisted private val id: Int,
        private val repository: PixelsRepository
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            require(modelClass == VideoPlaybackViewModel::class.java)
            @Suppress("UNCHECKED_CAST")
            return VideoPlaybackViewModel(id, repository) as T
        }
    }

    @AssistedFactory
    interface FactoryHelper {
        fun create(
            @Assisted id: Int
        ): ViewModelFactory
    }
}