package com.technzone.phoneapp.data.repos.media

import com.technzone.phoneapp.data.api.response.APIResource
import com.technzone.phoneapp.data.api.response.ResponseWrapper
import com.technzone.phoneapp.data.models.media.Media
import okhttp3.MultipartBody

interface MediaRepo {
    suspend fun getMedia(
            skip: Int
    ): APIResource<ResponseWrapper<List<Media>>>

    suspend fun uploadMedia(
        mediaFile : MultipartBody.Part
    ): APIResource<ResponseWrapper<Any>>

    suspend fun deleteMedia(
        mediaId: Int
    ): APIResource<ResponseWrapper<Any>>
}