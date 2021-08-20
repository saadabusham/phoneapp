package com.technzone.phoneapp.ui.wishlist.viewmodels

import androidx.lifecycle.liveData
import com.technzone.phoneapp.data.api.response.APIResource
import com.technzone.phoneapp.data.repos.accessories.AccessoriesRepo
import com.technzone.phoneapp.data.repos.wishlist.WishListRepo
import com.technzone.phoneapp.ui.base.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WishListViewModel @Inject constructor(
    private val wishListRepo: WishListRepo,
    private val accessoriesRepo: AccessoriesRepo
) : BaseViewModel() {

    fun getWishList(
        skip: Int
    ) = liveData {
        emit(APIResource.loading())
        val response =
            wishListRepo.getWishList(skip)
        emit(response)
    }

    fun addToWishList(
        entityType: String,
        productId: Int
    ) = liveData {
        emit(APIResource.loading())
        val response =
            wishListRepo.addToWishList(entityType,productId)
        emit(response)
    }

    fun removeFromWishList(
        entityType: String,
        productId: Int
    ) = liveData {
        emit(APIResource.loading())
        val response =
            wishListRepo.removeFromWishList(productId,entityType)
        emit(response)
    }

    fun getAccessory(
        id: Int
    ) = liveData {
        emit(APIResource.loading())
        val response =
            accessoriesRepo.getAccessory(id)
        emit(response)
    }

}