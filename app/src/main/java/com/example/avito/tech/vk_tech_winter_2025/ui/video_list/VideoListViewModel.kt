package com.example.avito.tech.vk_tech_winter_2025.ui.video_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.avito.tech.vk_tech_winter_2025.repository.PixelsRepository
import javax.inject.Inject
import com.example.avito.tech.vk_tech_winter_2025.ui.video_list.data.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class VideoListViewModel @Inject constructor(private val pixelsRepository: PixelsRepository) : ViewModel() {

    val videoLiveData: LiveData<Result>
        get() = mutableVideoLiveData
    private val mutableVideoLiveData = MutableLiveData<Result>()

    init {
        getTracks()
    }
    fun getTracks(){
        viewModelScope.launch(Dispatchers.IO) {
            mutableVideoLiveData.postValue(pixelsRepository.getVideos())
        }
    }
}