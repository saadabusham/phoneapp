package com.raantech.awfrlak.data.daos.remote.wishlist


import com.raantech.awfrlak.data.api.response.ResponseWrapper
import com.raantech.awfrlak.data.common.NetworkConstants
import com.raantech.awfrlak.data.models.wishlist.WishList
import retrofit2.http.*

interface WishListRemoteDao {

    @Headers("${NetworkConstants.SKIP_AUTHORIZATION_HEADER}:false")
    @GET("wishlist")
    suspend fun getWishList(
        @Query("skip") skip: Int
    ): ResponseWrapper<List<WishList>>

    @Headers("${NetworkConstants.SKIP_AUTHORIZATION_HEADER}:false")
    @FormUrlEncoded
    @POST("wishlist/store")
    suspend fun addToWishList(
        @Field("entity_type") entity_type: String,
        @Field("entity_id") entity_id: Int
    ): ResponseWrapper<Any>

    @Headers("${NetworkConstants.SKIP_AUTHORIZATION_HEADER}:false")
    @DELETE("wishlist/{product_id}/destroy")
    suspend fun removeFromWishList(
        @Path("product_id") productId: Int
    ): ResponseWrapper<Any>

}