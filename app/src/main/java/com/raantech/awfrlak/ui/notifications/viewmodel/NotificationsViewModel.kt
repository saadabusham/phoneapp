package com.raantech.awfrlak.ui.notifications.viewmodel

import com.raantech.awfrlak.data.repos.configuration.ConfigurationRepo
import com.raantech.awfrlak.ui.base.viewmodel.BaseViewModel
import com.raantech.awfrlak.utils.pref.SharedPreferencesUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NotificationsViewModel @Inject constructor(
        private val configurationRepo: ConfigurationRepo,
        private val sharedPreferencesUtil: SharedPreferencesUtil
) : BaseViewModel() {


    fun getPhoneNumber(): String? {
        return sharedPreferencesUtil.getConfigurationPreferences().appPhoneNumber
    }

}