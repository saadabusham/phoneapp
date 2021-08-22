package com.technzone.phoneapp.ui.purchase

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.liveData
import com.technzone.phoneapp.data.api.response.APIResource
import com.technzone.phoneapp.data.repos.configuration.ConfigurationRepo
import com.technzone.phoneapp.ui.base.viewmodel.BaseViewModel
import com.technzone.phoneapp.utils.pref.SharedPreferencesUtil
import dagger.assisted.Assisted
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PurchasesViewModel @Inject constructor(
        @Assisted private val savedStateHandle: SavedStateHandle,
        private val configurationRepo: ConfigurationRepo,
        private val sharedPreferencesUtil: SharedPreferencesUtil
) : BaseViewModel() {


    fun getPhoneNumber(): String? {
        return sharedPreferencesUtil.getConfigurationPreferences().appPhoneNumber
    }

}