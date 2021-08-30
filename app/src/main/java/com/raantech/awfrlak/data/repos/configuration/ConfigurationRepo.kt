package com.raantech.awfrlak.data.repos.configuration

import com.raantech.awfrlak.common.CommonEnums
import com.raantech.awfrlak.data.api.response.APIResource
import com.raantech.awfrlak.data.api.response.ResponseWrapper
import com.raantech.awfrlak.data.models.City
import com.raantech.awfrlak.data.models.configuration.ConfigurationWrapperResponse
import com.raantech.awfrlak.data.models.more.AboutUsResponse

interface ConfigurationRepo {

    fun setAppLanguage(selectedLanguage: CommonEnums.Languages)
    fun getAppLanguage(): CommonEnums.Languages

    suspend fun loadConfigurationData(): APIResource<ResponseWrapper<ConfigurationWrapperResponse>>

    suspend fun getCities(
    ): APIResource<ResponseWrapper<List<City>>>

    suspend fun getAboutUs(): APIResource<ResponseWrapper<AboutUsResponse>>
}