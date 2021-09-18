package com.raantech.awfrlak.user.data.repos.wishlist

import com.raantech.awfrlak.user.data.api.response.APIResource
import com.raantech.awfrlak.user.data.api.response.ResponseWrapper
import com.raantech.awfrlak.user.data.models.wishlist.WishList

interface WishListRepo {

    suspend fun getWishList(
            skip: Int
    ): APIResource<ResponseWrapper<List<WishList>>>

    suspend fun addToWishList(
            entity_type: String,
            entity_id: Int
    ): APIResource<ResponseWrapper<Any>>

    suspend fun removeFromWishList(
            entity_type: String,
            productId: Int
    ): APIResource<ResponseWrapper<Any>>

}