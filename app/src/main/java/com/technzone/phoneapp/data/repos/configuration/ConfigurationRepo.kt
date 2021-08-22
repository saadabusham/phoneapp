package com.technzone.phoneapp.data.repos.configuration

import com.technzone.phoneapp.common.CommonEnums
import com.technzone.phoneapp.data.api.response.APIResource
import com.technzone.phoneapp.data.api.response.ResponseWrapper
import com.technzone.phoneapp.data.models.City
import com.technzone.phoneapp.data.models.configuration.ConfigurationWrapperResponse
import com.technzone.phoneapp.data.models.more.AboutUsResponse

interface ConfigurationRepo {

    fun setAppLanguage(selectedLanguage: CommonEnums.Languages)
    fun getAppLanguage(): CommonEnums.Languages

    suspend fun loadConfigurationData(): APIResource<ResponseWrapper<ConfigurationWrapperResponse>>

    suspend fun getCities(
    ): APIResource<ResponseWrapper<List<City>>>

    suspend fun getAboutUs(): APIResource<ResponseWrapper<AboutUsResponse>>
}