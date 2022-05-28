package com.raantech.awfrlak.user.data.repos.orders

import com.raantech.awfrlak.user.data.api.response.APIResource
import com.raantech.awfrlak.user.data.api.response.ResponseWrapper
import com.raantech.awfrlak.user.data.models.orders.CreateOrderResponse
import com.raantech.awfrlak.user.data.models.orders.Order
import com.raantech.awfrlak.user.data.models.orders.OrderDetails
import com.raantech.awfrlak.user.data.models.orders.OrderRequest
import retrofit2.http.*

interface OrdersRepo {

    suspend fun createOrder(
        orderRequest: OrderRequest
    ): APIResource<ResponseWrapper<CreateOrderResponse>>

    suspend fun getOrders(
        skip: Int
    ): APIResource<ResponseWrapper<List<Order>>>

    suspend fun getOrderDetails(
        orderId: Int
    ): APIResource<ResponseWrapper<OrderDetails>>

}