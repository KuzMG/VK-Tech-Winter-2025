package com.example.vk.tech.vk_tech_winter_2025.database.entity.video_file

import android.os.Parcel
import android.os.Parcelable


data class VideoFile(
    val id: Int,
    val quality: String,
    val link: String,
    val width: Int,
    val height: Int
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(quality)
        parcel.writeString(link)
        parcel.writeInt(width)
        parcel.writeInt(height)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<VideoFile> {
        override fun createFromParcel(parcel: Parcel): VideoFile {
            return VideoFile(parcel)
        }

        override fun newArray(size: Int): Array<VideoFile?> {
            return arrayOfNulls(size)
        }
    }
}
