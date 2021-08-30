package com.raantech.awfrlak.ui.main.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.raantech.awfrlak.common.CommonEnums
import com.raantech.awfrlak.data.api.response.APIResource
import com.raantech.awfrlak.data.enums.UserEnums
import com.raantech.awfrlak.data.enums.ServicesType
import com.raantech.awfrlak.data.models.accessories.Accessory
import com.raantech.awfrlak.data.pref.configuration.ConfigurationPref
import com.raantech.awfrlak.data.pref.user.UserPref
import com.raantech.awfrlak.data.repos.accessories.AccessoriesRepo
import com.raantech.awfrlak.data.repos.auth.UserRepo
import com.raantech.awfrlak.data.repos.cart.CartRepo
import com.raantech.awfrlak.data.repos.configuration.ConfigurationRepo
import com.raantech.awfrlak.data.repos.wishlist.WishListRepo
import com.raantech.awfrlak.ui.base.viewmodel.BaseViewModel
import com.raantech.awfrlak.utils.pref.SharedPreferencesUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val userRepo: UserRepo,
    private val sharedPreferencesUtil: SharedPreferencesUtil,
    private val userPref: UserPref,
    private val configurationPref: ConfigurationPref,
    private val configurationRepo: ConfigurationRepo,
    private val cartRepo: CartRepo,
    private val wishListRepo: WishListRepo,
    private val accessoriesRepo : AccessoriesRepo
) : BaseViewModel() {

    val cartCount: MutableLiveData<String> = MutableLiveData("0")

    fun getCartsCount() = viewModelScope.launch {
        cartRepo.getCartsCount().observeForever {
            if (it != null)
                cartCount.postValue(it.toString())
            else
                cartCount.postValue("0")
        }
    }

    fun logoutRemote() = liveData {
        emit(APIResource.loading())
        val response = userRepo.logout()
        emit(response)
    }

    fun logoutLocale() {
        if (userRepo.getTouchIdStatus())
            sharedPreferencesUtil.logout()
        else {
            sharedPreferencesUtil.clearPreference()
            userPref.setIsFirstOpen(false)
        }
    }

    fun getAccessories(
        skip: Int,
        serviceType: String?
    ) = liveData {
        emit(APIResource.loading())
        val response = accessoriesRepo.getAccessories(skip, serviceType)
        emit(response)
    }

    fun getHome() = liveData {
        emit(APIResource.loading())
        val response = accessoriesRepo.getHome()
        emit(response)
    }

    fun isUserLoggedIn(): Boolean {
        return userRepo.getUserStatus() == UserEnums.UserState.LoggedIn
    }

    fun saveLanguage() = liveData {
        configurationPref.setAppLanguageValue(if (configurationPref.getAppLanguageValue() == "ar") CommonEnums.Languages.English.value else CommonEnums.Languages.Arabic.value)
        emit(null)
    }

    fun getAppLanguage(): String {
        return configurationPref.getAppLanguageValue()
    }

    fun getCities() = liveData {
        emit(APIResource.loading())
        val response = configurationRepo.getCities()
        emit(response)
    }

    fun addToWishList(
        productId: Int
    ) = liveData {
        emit(APIResource.loading())
        val response =
            wishListRepo.addToWishList(ServicesType.PRODUCT.value, productId)
        emit(response)
    }

    fun removeFromWishList(
        productId: Int
    ) = liveData {
        emit(APIResource.loading())
        val response =
            wishListRepo.removeFromWishList(productId, ServicesType.PRODUCT.value)
        emit(response)
    }

    fun addToCart(accessory: Accessory) = viewModelScope.launch {
        cartRepo.saveCart(accessory)
    }

    fun getCarts() = liveData {
        val response = cartRepo.loadCarts()
        emit(response)
    }

    fun getCart(id: Int) = liveData {
        val response = cartRepo.getCart(id)
        emit(response)
    }

}