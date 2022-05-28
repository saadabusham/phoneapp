package com.raantech.awfrlak.user.data.repos.orders


import com.raantech.awfrlak.user.data.api.response.APIResource
import com.raantech.awfrlak.user.data.api.response.ResponseHandler
import com.raantech.awfrlak.user.data.api.response.ResponseWrapper
import com.raantech.awfrlak.user.data.daos.remote.orders.OrdersRemoteDao
import com.raantech.awfrlak.user.data.models.orders.CreateOrderResponse
import com.raantech.awfrlak.user.data.models.orders.Order
import com.raantech.awfrlak.user.data.models.orders.OrderDetails
import com.raantech.awfrlak.user.data.models.orders.OrderRequest
import com.raantech.awfrlak.user.data.repos.base.BaseRepo
import javax.inject.Inject

class OrdersRepoImp @Inject constructor(
    responseHandler: ResponseHandler,
    private val ordersRemoteDao: OrdersRemoteDao
) : BaseRepo(responseHandler), OrdersRepo {

    override suspend fun createOrder(orderRequest: OrderRequest): APIResource<ResponseWrapper<CreateOrderResponse>> {
        return try {
            responseHandle.handleSuccess(ordersRemoteDao.createOrder(orderRequest))
        } catch (e: Exception) {
            responseHandle.handleException(e)
        }
    }

    override suspend fun getOrders(skip: Int): APIResource<ResponseWrapper<List<Order>>> {
        return try {
            responseHandle.handleSuccess(ordersRemoteDao.getOrders(skip))
        } catch (e: Exception) {
            responseHandle.handleException(e)
        }
    }

    override suspend fun getOrderDetails(orderId: Int): APIResource<ResponseWrapper<OrderDetails>> {
        return try {
            responseHandle.handleSuccess(ordersRemoteDao.getOrderDetails(orderId))
        } catch (e: Exception) {
            responseHandle.handleException(e)
        }
    }

}