package com.raantech.awfrlak.data.repos.cart.cart

import androidx.lifecycle.LiveData
import com.raantech.awfrlak.data.models.accessories.Accessory

interface CartRepo {

    suspend fun saveCart(accessory: Accessory)
    suspend fun saveCarts(accessories: List<Accessory>)
    suspend fun loadCarts(): List<Accessory>
    suspend fun getCart(id: Int): Accessory
    fun getCartsCount(): LiveData<Int>
    suspend fun getCartsCountInt(): Int?
    suspend fun deleteCart(id:Int)
    suspend fun clearCart()
}