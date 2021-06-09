package com.technzone.baseapp.data.repos.configuration

import com.technzone.baseapp.common.CommonEnums
import com.technzone.baseapp.data.api.response.APIResource
import com.technzone.baseapp.data.api.response.ResponseHandler
import com.technzone.baseapp.data.api.response.ResponseWrapper
import com.technzone.baseapp.data.daos.remote.configuration.ConfigurationRemoteDao
import com.technzone.baseapp.data.models.configuration.ConfigurationWrapperResponse
import com.technzone.baseapp.data.pref.configuration.ConfigurationPref
import com.technzone.baseapp.data.repos.base.BaseRepo
import javax.inject.Inject

class ConfigurationRepoImp @Inject constructor(
    responseHandler: ResponseHandler,
    private val configurationRemoteDao: ConfigurationRemoteDao,
    private val configurationPref: ConfigurationPref
) : BaseRepo(responseHandler), ConfigurationRepo {

    override fun setAppLanguage(selectedLanguage: CommonEnums.Languages) {
        configurationPref.setAppLanguageValue(selectedLanguage.value)
    }

    override fun getAppLanguage(): CommonEnums.Languages {
        return CommonEnums.Languages.getLanguageByValue(configurationPref.getAppLanguageValue())
    }

    override suspend fun loadConfigurationData():
            APIResource<ResponseWrapper<ConfigurationWrapperResponse>> {
        return try {
            responseHandle.handleSuccess(configurationRemoteDao.getAppConfiguration())
        } catch (e: Exception) {
            responseHandle.handleException(e)
        }
    }

}