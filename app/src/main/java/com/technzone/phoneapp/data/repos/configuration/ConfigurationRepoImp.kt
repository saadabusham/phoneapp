package com.technzone.phoneapp.data.repos.configuration

import com.technzone.phoneapp.common.CommonEnums
import com.technzone.phoneapp.data.api.response.APIResource
import com.technzone.phoneapp.data.api.response.ResponseHandler
import com.technzone.phoneapp.data.api.response.ResponseWrapper
import com.technzone.phoneapp.data.daos.remote.configuration.ConfigurationRemoteDao
import com.technzone.phoneapp.data.models.City
import com.technzone.phoneapp.data.models.configuration.ConfigurationWrapperResponse
import com.technzone.phoneapp.data.models.more.AboutUsResponse
import com.technzone.phoneapp.data.pref.configuration.ConfigurationPref
import com.technzone.phoneapp.data.repos.base.BaseRepo
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
    override suspend fun getCities(): APIResource<ResponseWrapper<List<City>>> {
        return try {
            responseHandle.handleSuccess(configurationRemoteDao.getCities())
        } catch (e: Exception) {
            responseHandle.handleException(e)
        }
    }

    override suspend fun getAboutUs(): APIResource<ResponseWrapper<AboutUsResponse>> {
        return try {
            responseHandle.handleSuccess(configurationRemoteDao.getAboutUs())
        } catch (e: Exception) {
            responseHandle.handleException(e)
        }
    }

}