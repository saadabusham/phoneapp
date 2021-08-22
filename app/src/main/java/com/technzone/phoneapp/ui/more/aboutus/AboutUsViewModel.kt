package com.technzone.phoneapp.ui.more.aboutus

import androidx.lifecycle.liveData
import com.technzone.phoneapp.data.api.response.APIResource
import com.technzone.phoneapp.data.models.configuration.ConfigurationWrapperResponse
import com.technzone.phoneapp.data.pref.configuration.ConfigurationPref
import com.technzone.phoneapp.data.repos.configuration.ConfigurationRepo
import com.technzone.phoneapp.ui.base.viewmodel.BaseViewModel
import com.technzone.phoneapp.utils.pref.SharedPreferencesUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AboutUsViewModel @Inject constructor(
        val sharedPreferencesUtil: SharedPreferencesUtil,
        val configurationPref: ConfigurationPref,
        private val configurationRepo: ConfigurationRepo
) : BaseViewModel() {

    fun getAboutUs() = liveData {
        emit(APIResource.loading())
        val response = configurationRepo.getAboutUs()
        emit(response)
    }


    fun getSocialMedia(): ConfigurationWrapperResponse? {
        return sharedPreferencesUtil.getConfigurationPreferences()
    }

}