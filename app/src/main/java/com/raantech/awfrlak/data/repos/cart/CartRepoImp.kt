package com.raantech.awfrlak.data.repos.cart

import androidx.lifecycle.LiveData
import com.raantech.awfrlak.data.api.response.ResponseHandler
import com.raantech.awfrlak.data.daos.local.cart.CartLocalDao
import com.raantech.awfrlak.data.models.accessories.Accessory
import com.raantech.awfrlak.data.repos.base.BaseRepo
import javax.inject.Inject

class CartRepoImp @Inject constructor(
    responseHandler: ResponseHandler,
    private val cartLocalDao: CartLocalDao
) : BaseRepo(responseHandler), CartRepo {

    override suspend fun saveCart(accessory: Accessory) {
        cartLocalDao.saveCart(accessory)
    }

    override suspend fun saveCarts(accessories: List<Accessory>) {
        cartLocalDao.saveCarts(accessories)
    }

    override suspend fun loadCarts(): List<Accessory> {
        return cartLocalDao.getCarts()
    }

    override suspend fun getCart(id: Int): Accessory {
        return cartLocalDao.getCarts(id)
    }

    override fun getCartsCount(): LiveData<Int> {
        return cartLocalDao.getCartsCount()
    }

    override suspend fun deleteCart(id: Int) {
        cartLocalDao.deleteCart(id)
    }

    override suspend fun clearCart() {
        cartLocalDao.clearCart()
    }


}