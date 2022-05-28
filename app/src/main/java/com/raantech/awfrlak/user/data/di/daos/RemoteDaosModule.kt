package com.raantech.awfrlak.user.data.di.daos

import com.raantech.awfrlak.user.data.daos.remote.accessories.AccessoriesRemoteDao
import com.raantech.awfrlak.user.data.daos.remote.configuration.ConfigurationRemoteDao
import com.raantech.awfrlak.user.data.daos.remote.media.MediaRemoteDao
import com.raantech.awfrlak.user.data.daos.remote.orders.OrdersRemoteDao
import com.raantech.awfrlak.user.data.daos.remote.user.UserRemoteDao
import com.raantech.awfrlak.user.data.daos.remote.wishlist.WishListRemoteDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RemoteDaosModule {


    @Singleton
    @Provides
    fun provideUserRemoteDao(
        retrofit: Retrofit
    ): UserRemoteDao {
        return retrofit.create(UserRemoteDao::class.java)
    }

    @Singleton
    @Provides
    fun provideConfigurationRemoteDao(
        retrofit: Retrofit
    ): ConfigurationRemoteDao {
        return retrofit.create(ConfigurationRemoteDao::class.java)
    }

    @Singleton
    @Provides
    fun provideMediaRemoteDao(
        retrofit: Retrofit
    ): MediaRemoteDao {
        return retrofit.create(MediaRemoteDao::class.java)
    }

    @Singleton
    @Provides
    fun provideWishListRemoteDao(
        retrofit: Retrofit
    ): WishListRemoteDao {
        return retrofit.create(WishListRemoteDao::class.java)
    }

    @Singleton
    @Provides
    fun provideAccessoriesRemoteDao(
        retrofit: Retrofit
    ): AccessoriesRemoteDao {
        return retrofit.create(AccessoriesRemoteDao::class.java)
    }

    @Singleton
    @Provides
    fun provideOrdersRemoteDao(
        retrofit: Retrofit
    ): OrdersRemoteDao {
        return retrofit.create(OrdersRemoteDao::class.java)
    }

}