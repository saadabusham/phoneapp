package com.raantech.awfrlak.data.repos.accessories

import com.raantech.awfrlak.data.api.response.APIResource
import com.raantech.awfrlak.data.api.response.ResponseWrapper
import com.raantech.awfrlak.data.models.accessories.Accessory
import com.raantech.awfrlak.data.models.home.*
import retrofit2.http.Query

interface AccessoriesRepo {

    suspend fun getHome(
    ): APIResource<ResponseWrapper<HomeResponse>>

    suspend fun getMobiles(
        skip: Int,
        store_id: Int?= null,
        search_text: String?= null
    ): APIResource<ResponseWrapper<List<MobilesItem>>>


    suspend fun getAccessories(
        skip: Int,
        store_id: Int?= null,
        search_text: String?= null
    ): APIResource<ResponseWrapper<List<AccessoriesItem>>>

    suspend fun getStores(
        skip: Int,
        search_text: String? = null
    ): APIResource<ResponseWrapper<List<Store>>>

    suspend fun getServices(
        skip: Int,
        store_id: Int?= null,
        search_text: String?= null
    ): APIResource<ResponseWrapper<List<Service>>>

    suspend fun getAccessory(
        id: Int
    ): APIResource<ResponseWrapper<Accessory>>
}