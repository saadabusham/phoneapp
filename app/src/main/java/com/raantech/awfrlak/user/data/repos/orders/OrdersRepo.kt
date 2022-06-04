package com.raantech.awfrlak.user.data.repos.orders

import com.raantech.awfrlak.user.data.api.response.APIResource
import com.raantech.awfrlak.user.data.api.response.ResponseWrapper
import com.raantech.awfrlak.user.data.models.orders.request.CreateOrderResponse
import com.raantech.awfrlak.user.data.models.orders.entity.Order
import com.raantech.awfrlak.user.data.models.orders.request.OrderRequest

interface OrdersRepo {

    suspend fun createOrder(
        orderRequest: OrderRequest
    ): APIResource<ResponseWrapper<CreateOrderResponse>>

    suspend fun getOrders(
        skip: Int
    ): APIResource<ResponseWrapper<List<Order>>>

    suspend fun getOrderDetails(
        orderId: String
    ): APIResource<ResponseWrapper<Order>>

}