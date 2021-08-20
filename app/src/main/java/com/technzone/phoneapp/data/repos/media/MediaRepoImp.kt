package com.technzone.phoneapp.data.repos.media

import com.technzone.phoneapp.data.api.response.APIResource
import com.technzone.phoneapp.data.api.response.ResponseHandler
import com.technzone.phoneapp.data.api.response.ResponseWrapper
import com.technzone.phoneapp.data.daos.remote.media.MediaRemoteDao
import com.technzone.phoneapp.data.models.media.Media
import com.technzone.phoneapp.data.repos.base.BaseRepo
import okhttp3.MultipartBody
import javax.inject.Inject

class MediaRepoImp @Inject constructor(
    responseHandler: ResponseHandler,
    private val mediaRemoteDao: MediaRemoteDao
) : BaseRepo(responseHandler), MediaRepo {

    override suspend fun getMedia(skip: Int): APIResource<ResponseWrapper<List<Media>>> {
        return try {
            responseHandle.handleSuccess(mediaRemoteDao.getMedia(skip))
        } catch (e: Exception) {
            responseHandle.handleException(e)
        }
    }

    override suspend fun uploadMedia(mediaFile: MultipartBody.Part): APIResource<ResponseWrapper<Any>> {
        return try {
            responseHandle.handleSuccess(mediaRemoteDao.uploadMedia(mediaFile))
        } catch (e: Exception) {
            responseHandle.handleException(e)
        }
    }

    override suspend fun deleteMedia(mediaId: Int): APIResource<ResponseWrapper<Any>> {
        return try {
            responseHandle.handleSuccess(mediaRemoteDao.deleteMedia(mediaId))
        } catch (e: Exception) {
            responseHandle.handleException(e)
        }
    }

}