package com.technzone.phoneapp.data.repos.wishlist

import com.technzone.phoneapp.data.api.response.APIResource
import com.technzone.phoneapp.data.api.response.ResponseWrapper
import com.technzone.phoneapp.data.models.wishlist.WishList

interface WishListRepo {

    suspend fun getWishList(
        skip: Int
    ): APIResource<ResponseWrapper<List<WishList>>>

    suspend fun addToWishList(
        entity_type:String,
        entity_id:Int
    ): APIResource<ResponseWrapper<Any>>

    suspend fun removeFromWishList(
        productId: Int,
        entity_type: String
    ): APIResource<ResponseWrapper<Any>>

}