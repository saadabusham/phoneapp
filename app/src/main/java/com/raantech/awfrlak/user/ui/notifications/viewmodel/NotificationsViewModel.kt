package com.raantech.awfrlak.user.ui.notifications.viewmodel

import androidx.lifecycle.liveData
import com.raantech.awfrlak.user.data.api.response.APIResource
import com.raantech.awfrlak.user.data.repos.auth.UserRepo
import com.raantech.awfrlak.user.data.repos.configuration.ConfigurationRepo
import com.raantech.awfrlak.user.ui.base.viewmodel.BaseViewModel
import com.raantech.awfrlak.user.utils.pref.SharedPreferencesUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NotificationsViewModel @Inject constructor(
        private val userRepo: UserRepo,
        private val sharedPreferencesUtil: SharedPreferencesUtil
) : BaseViewModel() {

    fun getNotifications(
        skip: Int
    ) = liveData {
        emit(APIResource.loading())
        val response =
            userRepo.getNotifications(skip)
        emit(response)
    }

}