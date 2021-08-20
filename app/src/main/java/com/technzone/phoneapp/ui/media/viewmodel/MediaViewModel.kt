package com.technzone.phoneapp.ui.media.viewmodel

import androidx.lifecycle.liveData
import com.technzone.phoneapp.data.api.response.APIResource
import com.technzone.phoneapp.data.repos.media.MediaRepo
import com.technzone.phoneapp.ui.base.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import okhttp3.MultipartBody
import javax.inject.Inject

@HiltViewModel
class MediaViewModel @Inject constructor(
    val mediaRepo: MediaRepo
) : BaseViewModel() {


    fun getMedia(skip: Int) = liveData {
        emit(APIResource.loading())
        val response = mediaRepo.getMedia(skip)
        emit(response)
    }

    fun uploadMedia(media : MultipartBody.Part) = liveData {
        emit(APIResource.loading())
        val response = mediaRepo.uploadMedia(media)
        emit(response)
    }

    fun deleteMedia(mediaId: Int) = liveData {
        emit(APIResource.loading())
        val response = mediaRepo.deleteMedia(mediaId)
        emit(response)
    }
}