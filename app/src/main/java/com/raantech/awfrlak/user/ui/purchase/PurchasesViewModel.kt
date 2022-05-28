package com.raantech.awfrlak.user.ui.purchase

import androidx.lifecycle.liveData
import com.raantech.awfrlak.user.data.api.response.APIResource
import com.raantech.awfrlak.user.data.repos.configuration.ConfigurationRepo
import com.raantech.awfrlak.user.data.repos.orders.OrdersRepo
import com.raantech.awfrlak.user.ui.base.viewmodel.BaseViewModel
import com.raantech.awfrlak.user.utils.pref.SharedPreferencesUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PurchasesViewModel @Inject constructor(
    private val configurationRepo: ConfigurationRepo,
    private val sharedPreferencesUtil: SharedPreferencesUtil,
    private val ordersRepo: OrdersRepo
) : BaseViewModel() {


    fun getPhoneNumber(): String? {
        return sharedPreferencesUtil.getConfigurationPreferences().appPhoneNumber
    }

    fun getOrders(
        skip: Int
    ) = liveData {
        emit(APIResource.loading())
        val response = ordersRepo.getOrders(skip)
        emit(response)
    }

}