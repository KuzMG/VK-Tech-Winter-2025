package com.example.vk.tech.vk_tech_winter_2025.ui.video_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vk.tech.vk_tech_winter_2025.repository.PixelsRepository
import com.example.vk.tech.vk_tech_winter_2025.ui.data.ResultVideoList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class VideoListViewModel @Inject constructor(private val pixelsRepository: PixelsRepository) :
    ViewModel() {

    val videoLiveData: LiveData<ResultVideoList>
        get() = mutableVideoLiveData
    private val mutableVideoLiveData = MutableLiveData<ResultVideoList>()

    init {
        getVideos()
    }

    fun getVideos() {
        viewModelScope.launch(Dispatchers.IO) {
            mutableVideoLiveData.postValue(pixelsRepository.getVideos())
        }
    }
}