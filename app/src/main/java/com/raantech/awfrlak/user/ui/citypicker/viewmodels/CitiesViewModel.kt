package com.raantech.awfrlak.user.ui.citypicker.viewmodels

import androidx.lifecycle.liveData
import com.raantech.awfrlak.user.data.api.response.APIResource
import com.raantech.awfrlak.user.data.repos.configuration.ConfigurationRepo
import com.raantech.awfrlak.user.ui.base.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CitiesViewModel @Inject constructor(
    private val configurationRepo : ConfigurationRepo
) : BaseViewModel() {

    fun getCities(
    ) = liveData {
        emit(APIResource.loading())
        val response = configurationRepo.getCities()
        emit(response)
    }

}