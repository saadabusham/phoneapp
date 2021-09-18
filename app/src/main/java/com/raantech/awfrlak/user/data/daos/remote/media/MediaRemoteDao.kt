package com.raantech.awfrlak.user.data.daos.remote.media

import com.raantech.awfrlak.user.data.models.media.Media
import com.raantech.awfrlak.user.data.api.response.ResponseWrapper
import com.raantech.awfrlak.user.data.common.NetworkConstants
import okhttp3.MultipartBody
import retrofit2.http.*

interface MediaRemoteDao {

    @Headers("${NetworkConstants.SKIP_AUTHORIZATION_HEADER}:false")
    @GET("user/media")
    suspend fun getMedia(
        @Query("skip") skip: Int
    ): ResponseWrapper<List<Media>>

    @Headers("${NetworkConstants.SKIP_AUTHORIZATION_HEADER}:false")
    @Multipart
    @POST("user/media/store")
    suspend fun uploadMedia(
        @Part mediaFile: MultipartBody.Part
    ): ResponseWrapper<Any>

    @Headers("${NetworkConstants.SKIP_AUTHORIZATION_HEADER}:false")
    @DELETE("user/media/{mediaId}/destroy")
    suspend fun deleteMedia(
        @Path("mediaId") mediaId: Int
    ): ResponseWrapper<Any>


}