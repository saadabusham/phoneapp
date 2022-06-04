package com.raantech.awfrlak.user.data.daos.remote.configuration

import com.raantech.awfrlak.user.data.api.response.ResponseWrapper
import com.raantech.awfrlak.user.data.common.NetworkConstants
import com.raantech.awfrlak.user.data.models.City
import com.raantech.awfrlak.user.data.models.configuration.ConfigurationWrapperResponse
import com.raantech.awfrlak.user.data.models.more.AboutUsResponse
import com.raantech.awfrlak.user.data.models.notification.Notification
import retrofit2.http.GET
import retrofit2.http.Headers

interface ConfigurationRemoteDao {

    @GET("app/settings")
    suspend fun getAppConfiguration(): ResponseWrapper<ConfigurationWrapperResponse>

    @Headers("${NetworkConstants.SKIP_AUTHORIZATION_HEADER}:false")
    @GET("app/cities")
    suspend fun getCities(
    ): ResponseWrapper<List<City>>

    @Headers("${NetworkConstants.SKIP_AUTHORIZATION_HEADER}:true")
    @GET("app/about-us")
    suspend fun getAboutUs(): ResponseWrapper<AboutUsResponse>

}