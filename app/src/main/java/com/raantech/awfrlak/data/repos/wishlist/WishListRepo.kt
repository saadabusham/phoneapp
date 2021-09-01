package com.raantech.awfrlak.data.repos.wishlist

import com.raantech.awfrlak.data.api.response.APIResource
import com.raantech.awfrlak.data.api.response.ResponseWrapper
import com.raantech.awfrlak.data.models.wishlist.WishList

interface WishListRepo {

    suspend fun getWishList(
        skip: Int
    ): APIResource<ResponseWrapper<List<WishList>>>

    suspend fun addToWishList(
        entity_type:String,
        entity_id:Int
    ): APIResource<ResponseWrapper<Any>>

    suspend fun removeFromWishList(
        productId: Int
    ): APIResource<ResponseWrapper<Any>>

}