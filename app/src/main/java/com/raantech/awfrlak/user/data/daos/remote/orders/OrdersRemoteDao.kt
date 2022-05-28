package com.raantech.awfrlak.user.data.daos.remote.orders


import com.raantech.awfrlak.user.data.api.response.ResponseWrapper
import com.raantech.awfrlak.user.data.common.NetworkConstants
import com.raantech.awfrlak.user.data.models.orders.Order
import com.raantech.awfrlak.user.data.models.orders.OrderDetails
import com.raantech.awfrlak.user.data.models.orders.OrderRequest
import com.raantech.awfrlak.user.data.models.wishlist.WishList
import retrofit2.http.*

interface OrdersRemoteDao {

    @Headers("${NetworkConstants.SKIP_AUTHORIZATION_HEADER}:false")
    @POST("orders/create")
    suspend fun createOrder(
        @Body orderRequest: OrderRequest
    ): ResponseWrapper<String>

    @Headers("${NetworkConstants.SKIP_AUTHORIZATION_HEADER}:false")
    @GET("orders")
    suspend fun getOrders(
        @Query("skip") skip: Int
    ): ResponseWrapper<List<Order>>

    @Headers("${NetworkConstants.SKIP_AUTHORIZATION_HEADER}:false")
    @GET("orders/show/{orderId}")
    suspend fun getOrderDetails(
        @Path("orderId") orderId: Int
    ): ResponseWrapper<OrderDetails>

}