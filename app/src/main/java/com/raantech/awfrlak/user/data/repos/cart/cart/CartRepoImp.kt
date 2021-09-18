package com.raantech.awfrlak.user.data.repos.cart.cart

import androidx.lifecycle.LiveData
import com.raantech.awfrlak.user.data.api.response.ResponseHandler
import com.raantech.awfrlak.user.data.daos.local.cart.CartLocalDao
import com.raantech.awfrlak.user.data.models.home.AccessoriesItem
import com.raantech.awfrlak.user.data.repos.base.BaseRepo
import javax.inject.Inject

class CartRepoImp @Inject constructor(
    responseHandler: ResponseHandler,
    private val cartLocalDao: CartLocalDao
) : BaseRepo(responseHandler), CartRepo {

    override suspend fun saveCart(accessory: AccessoriesItem) {
        cartLocalDao.saveCart(accessory)
    }

    override suspend fun saveCarts(accessories: List<AccessoriesItem>) {
        cartLocalDao.saveCarts(accessories)
    }

    override suspend fun loadCarts(): List<AccessoriesItem> {
        return cartLocalDao.getCarts()
    }

    override suspend fun getCart(id: Int): AccessoriesItem {
        return cartLocalDao.getCarts(id)
    }

    override fun getCartsCount(): LiveData<Int> {
        return cartLocalDao.getCartsCount()
    }

    override suspend fun getCartsCountInt(): Int? {
        return cartLocalDao.getCartsCountInt()
    }

    override suspend fun deleteCart(id: Int) {
        cartLocalDao.deleteCart(id)
    }

    override suspend fun clearCart() {
        cartLocalDao.clearCart()
    }


}