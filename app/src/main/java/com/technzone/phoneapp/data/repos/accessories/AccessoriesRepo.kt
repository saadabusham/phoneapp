package com.technzone.phoneapp.data.repos.accessories

import com.technzone.phoneapp.data.api.response.APIResource
import com.technzone.phoneapp.data.api.response.ResponseWrapper
import com.technzone.phoneapp.data.models.accessories.Accessory

interface AccessoriesRepo {

    suspend fun getAccessories(
        skip: Int,
        serviceType: String?
    ): APIResource<ResponseWrapper<List<Accessory>>>

    suspend fun getAccessory(
        id: Int
    ): APIResource<ResponseWrapper<Accessory>>
}