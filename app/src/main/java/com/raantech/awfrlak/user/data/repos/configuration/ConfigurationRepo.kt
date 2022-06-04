package com.raantech.awfrlak.user.data.repos.configuration

import com.raantech.awfrlak.user.common.CommonEnums
import com.raantech.awfrlak.user.data.api.response.APIResource
import com.raantech.awfrlak.user.data.api.response.ResponseWrapper
import com.raantech.awfrlak.user.data.common.NetworkConstants
import com.raantech.awfrlak.user.data.models.City
import com.raantech.awfrlak.user.data.models.configuration.ConfigurationWrapperResponse
import com.raantech.awfrlak.user.data.models.more.AboutUsResponse
import com.raantech.awfrlak.user.data.models.notification.Notification
import retrofit2.http.GET
import retrofit2.http.Headers

interface ConfigurationRepo {

    fun setAppLanguage(selectedLanguage: CommonEnums.Languages)
    fun getAppLanguage(): CommonEnums.Languages

    suspend fun loadConfigurationData(): APIResource<ResponseWrapper<ConfigurationWrapperResponse>>

    suspend fun getCities(
    ): APIResource<ResponseWrapper<List<City>>>

    suspend fun getAboutUs(): APIResource<ResponseWrapper<AboutUsResponse>>
}