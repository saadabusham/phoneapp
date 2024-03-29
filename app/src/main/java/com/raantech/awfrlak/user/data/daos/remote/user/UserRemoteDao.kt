package com.raantech.awfrlak.user.data.daos.remote.user

import com.raantech.awfrlak.user.data.api.response.ResponseWrapper
import com.raantech.awfrlak.user.data.common.NetworkConstants
import com.raantech.awfrlak.user.data.models.auth.login.TokenModel
import com.raantech.awfrlak.user.data.models.auth.login.UserDetailsResponseModel
import com.raantech.awfrlak.user.data.models.auth.login.UserInfo
import com.raantech.awfrlak.user.data.models.notification.Notification
import retrofit2.http.*

interface UserRemoteDao {

    @Headers("${NetworkConstants.SKIP_AUTHORIZATION_HEADER}:true")
    @FormUrlEncoded
    @POST("auth/login")
    suspend fun login(
        @Field("phone_number") phoneNumber: String
    ): ResponseWrapper<TokenModel>

    @Headers("${NetworkConstants.SKIP_AUTHORIZATION_HEADER}:false")
    @POST("auth/logout")
    suspend fun logout(
    ): ResponseWrapper<Any>

    @Headers("${NetworkConstants.SKIP_AUTHORIZATION_HEADER}:true")
    @FormUrlEncoded
    @POST("auth/resend")
    suspend fun resendCode(
        @Field("token") token: String
    ): ResponseWrapper<TokenModel>

    @Headers("${NetworkConstants.SKIP_AUTHORIZATION_HEADER}:true")
    @FormUrlEncoded
    @POST("auth/verify")
    suspend fun verify(
        @Field("token") token: String,
        @Field("code") code: Int,
        @Field("device_token") device_token: String,
        @Field("platform") platform: String
    ): ResponseWrapper<UserDetailsResponseModel>

    @Headers("${NetworkConstants.SKIP_AUTHORIZATION_HEADER}:true")
    @FormUrlEncoded
    @POST("auth/register")
    suspend fun register(
        @Field("token") token: String,
        @Field("name") name: String,
        @Field("address") address: String,
        @Field("email") email: String
    ): ResponseWrapper<UserDetailsResponseModel>

    @Headers("${NetworkConstants.SKIP_AUTHORIZATION_HEADER}:false")
    @POST("profile/update")
    suspend fun updateProfile(
        @Query("name") name: String,
        @Query("email") email: String
    ): ResponseWrapper<UserInfo>

    @Headers("${NetworkConstants.SKIP_AUTHORIZATION_HEADER}:false")
    @POST("profile/update/address")
    suspend fun updateAddress(
        @Query("name") name: String,
        @Query("phone") phone: String,
        @Query("city") city: String,
        @Query("district") district: String,
        @Query("street") street: String,
        @Query("building_number") building_number: String,
        @Query("description") description: String,
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
    ): ResponseWrapper<UserInfo>

    @Headers("${NetworkConstants.SKIP_AUTHORIZATION_HEADER}:false")
    @GET("notifications")
    suspend fun getNotifications(
        @Query("skip") skip: Int
    ): ResponseWrapper<List<Notification>>

}