package com.technzone.phoneapp.data.daos.remote.accessories

import com.technzone.phoneapp.data.api.response.ResponseWrapper
import com.technzone.phoneapp.data.common.NetworkConstants
import com.technzone.phoneapp.data.models.accessories.Accessory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface AccessoriesRemoteDao {

    @Headers("${NetworkConstants.SKIP_AUTHORIZATION_HEADER}:false")
    @GET("accessories")
    suspend fun getAccessories(
        @Query("skip") skip: Int,
        @Query("category_id") serviceType: String?
    ): ResponseWrapper<List<Accessory>>

    @Headers("${NetworkConstants.SKIP_AUTHORIZATION_HEADER}:false")
    @GET("accessories/{id}/show")
    suspend fun getAccessory(
        @Path("id") id: Int
    ): ResponseWrapper<Accessory>

}