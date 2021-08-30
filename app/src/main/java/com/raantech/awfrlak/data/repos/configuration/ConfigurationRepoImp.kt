package com.raantech.awfrlak.data.repos.configuration

import com.raantech.awfrlak.common.CommonEnums
import com.raantech.awfrlak.data.api.response.APIResource
import com.raantech.awfrlak.data.api.response.ResponseHandler
import com.raantech.awfrlak.data.api.response.ResponseWrapper
import com.raantech.awfrlak.data.daos.remote.configuration.ConfigurationRemoteDao
import com.raantech.awfrlak.data.models.City
import com.raantech.awfrlak.data.models.configuration.ConfigurationWrapperResponse
import com.raantech.awfrlak.data.models.more.AboutUsResponse
import com.raantech.awfrlak.data.pref.configuration.ConfigurationPref
import com.raantech.awfrlak.data.repos.base.BaseRepo
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