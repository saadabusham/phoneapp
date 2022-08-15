package com.raantech.awfrlak.user.data.repos.auth

import com.raantech.awfrlak.user.data.api.response.APIResource
import com.raantech.awfrlak.user.data.api.response.ResponseWrapper
import com.raantech.awfrlak.user.data.enums.UserEnums
import com.raantech.awfrlak.user.data.models.auth.login.TokenModel
import com.raantech.awfrlak.user.data.models.auth.login.UserDetailsResponseModel
import com.raantech.awfrlak.user.data.models.auth.login.UserInfo
import com.raantech.awfrlak.user.data.models.notification.Notification
import retrofit2.http.Query


interface UserRepo {


    suspend fun login(
        phoneNumber: String
    ): APIResource<ResponseWrapper<TokenModel>>

    suspend fun logout(
    ): APIResource<ResponseWrapper<Any>>

    suspend fun resendCode(
        token: String
    ): APIResource<ResponseWrapper<TokenModel>>

    suspend fun verify(
        token: String,
        code: Int,
        device_token: String,
        platform: String
    ): APIResource<ResponseWrapper<UserDetailsResponseModel>>

    suspend fun register(
            token: String,
            name: String,
            address: String,
            email: String
    ): APIResource<ResponseWrapper<UserDetailsResponseModel>>

    suspend fun updateProfile(
            name: String,
            email: String
    ): APIResource<ResponseWrapper<UserInfo>>

    suspend fun updateAddress(
        name: String,
        phone: String,
        city: String,
        district: String,
        street: String,
        building_number: String,
        description: String,
        latitude: Double,
        longitude: Double,
    ): APIResource<ResponseWrapper<UserInfo>>

    suspend fun getNotifications(
        skip:Int
    ): APIResource<ResponseWrapper<List<Notification>>>

    fun saveNotificationStatus(flag: Boolean)
    fun getNotificationStatus(): Boolean

    fun saveTouchIdStatus(flag: Boolean)
    fun getTouchIdStatus(): Boolean

    fun saveAccessToken(accessToken: String)
    fun getAccessToken(): String

    fun saveUserPassword(password: String)
    fun getUserPassword(): String

    fun setUserStatus(userState: UserEnums.UserState)
    fun getUserStatus(): UserEnums.UserState

    fun setUser(user: UserDetailsResponseModel)
    fun getUser(): UserDetailsResponseModel?
}