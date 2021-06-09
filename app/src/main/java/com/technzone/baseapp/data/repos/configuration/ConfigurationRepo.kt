package com.technzone.baseapp.data.repos.configuration

import com.technzone.baseapp.common.CommonEnums
import com.technzone.baseapp.data.api.response.APIResource
import com.technzone.baseapp.data.api.response.ResponseWrapper
import com.technzone.baseapp.data.models.configuration.ConfigurationWrapperResponse

interface ConfigurationRepo {

    fun setAppLanguage(selectedLanguage: CommonEnums.Languages)
    fun getAppLanguage(): CommonEnums.Languages

    suspend fun loadConfigurationData(): APIResource<ResponseWrapper<ConfigurationWrapperResponse>>
}