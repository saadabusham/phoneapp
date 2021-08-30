package com.raantech.awfrlak.data.repos.accessories

import com.raantech.awfrlak.data.api.response.APIResource
import com.raantech.awfrlak.data.api.response.ResponseWrapper
import com.raantech.awfrlak.data.models.accessories.Accessory
import com.raantech.awfrlak.data.models.home.HomeResponse

interface AccessoriesRepo {

    suspend fun getHome(
    ): APIResource<ResponseWrapper<HomeResponse>>

    suspend fun getAccessories(
        skip: Int,
        serviceType: String?
    ): APIResource<ResponseWrapper<List<Accessory>>>

    suspend fun getAccessory(
        id: Int
    ): APIResource<ResponseWrapper<Accessory>>
}