package com.technzone.baseapp.data.daos.remote.configuration

import com.technzone.baseapp.data.api.response.ResponseWrapper
import com.technzone.baseapp.data.models.configuration.ConfigurationWrapperResponse
import retrofit2.http.GET

interface ConfigurationRemoteDao {

    @GET("api/configuration")
    suspend fun getAppConfiguration(): ResponseWrapper<ConfigurationWrapperResponse>
}