package com.example.vk.tech.vk_tech_winter_2025.repository

import com.example.vk.tech.vk_tech_winter_2025.R
import com.example.vk.tech.vk_tech_winter_2025.api.PixelsApi
import com.example.vk.tech.vk_tech_winter_2025.database.entity.video.Video
import com.example.vk.tech.vk_tech_winter_2025.database.entity.video.VideoDao
import com.example.vk.tech.vk_tech_winter_2025.database.entity.video.VideoEntity
import com.example.vk.tech.vk_tech_winter_2025.database.entity.video_file.VideoFileEntity
import com.example.vk.tech.vk_tech_winter_2025.database.entity.video_file.VideoFilesDao
import com.example.vk.tech.vk_tech_winter_2025.ui.data.ResultVideoList
import com.example.vk.tech.vk_tech_winter_2025.ui.data.ResultVideoPlayback
import com.example.vk.tech.vk_tech_winter_2025.ui.data.Status
import javax.inject.Inject
import javax.net.ssl.SSLHandshakeException

class PixelsRepository @Inject constructor(
    private val pixelsApi: PixelsApi,
    private val videoDao: VideoDao,
    private val videoFilesDao: VideoFilesDao
) {
    suspend fun getVideos(): ResultVideoList = try {
        val response = pixelsApi.getVideos()
        if (response.code() == 200) {
            insertVideo(response.body()?.videos ?: arrayListOf())
            ResultVideoList(Status.OK, videos = response.body()?.videos)
        } else {
            val videos = getVideosIfError()
            if (response.code() == 522) {
                ResultVideoList(
                    Status.ERROR,
                    errorId = R.string.vpn_exception,
                    error = "${response.code()} ${response.errorBody()}",
                    videos = videos
                )
            } else {
                ResultVideoList(
                    Status.ERROR,
                    error = "${response.code()} ${response.errorBody()}",
                    videos = videos
                )
            }
        }
    } catch (e: Exception) {
        val videos = getVideosIfError()
        if (e is SSLHandshakeException) {
            ResultVideoList(
                Status.ERROR,
                errorId = R.string.vpn_exception,
                error = e.message,
                videos = videos
            )
        } else {
            ResultVideoList(Status.ERROR, error = e.message, videos = videos)
        }
    }

    private fun getVideosIfError(): List<Video>? {
        val videos: List<Video> = videoDao.getVideos()
        return videos.ifEmpty {
            null
        }
    }

    private fun insertVideo(videos: List<Video>) {
        for (video in videos) {
            videoDao.insert(VideoEntity(video.id, video.duration, video.user, video.image))
            videoFilesDao.insert(video.videoFiles.map {
                VideoFileEntity(it.id, video.id, it.quality, it.link, it.width, it.height)
            })
        }
    }


    suspend fun getVideo(id: Int): ResultVideoPlayback = try {
        val response = pixelsApi.getVideo(id)
        if (response.code() == 200) {
            ResultVideoPlayback(Status.OK, video = response.body())
        } else {
            ResultVideoPlayback(Status.ERROR, error = "${response.code()} ${response.errorBody()}")
        }
    } catch (e: Exception) {
        ResultVideoPlayback(Status.ERROR, error = e.message)
    }

}