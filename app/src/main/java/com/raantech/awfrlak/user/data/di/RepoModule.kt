package com.raantech.awfrlak.user.data.di


import com.raantech.awfrlak.user.data.repos.accessories.AccessoriesRepo
import com.raantech.awfrlak.user.data.repos.accessories.AccessoriesRepoImp
import com.raantech.awfrlak.user.data.repos.auth.UserRepo
import com.raantech.awfrlak.user.data.repos.auth.UserRepoImp
import com.raantech.awfrlak.user.data.repos.cart.cart.CartRepo
import com.raantech.awfrlak.user.data.repos.cart.cart.CartRepoImp
import com.raantech.awfrlak.user.data.repos.cart.mobilecart.MobileCartRepo
import com.raantech.awfrlak.user.data.repos.cart.mobilecart.MobileCartRepoImp
import com.raantech.awfrlak.user.data.repos.configuration.ConfigurationRepo
import com.raantech.awfrlak.user.data.repos.configuration.ConfigurationRepoImp
import com.raantech.awfrlak.user.data.repos.media.MediaRepo
import com.raantech.awfrlak.user.data.repos.media.MediaRepoImp
import com.raantech.awfrlak.user.data.repos.orders.OrdersRepo
import com.raantech.awfrlak.user.data.repos.orders.OrdersRepoImp
import com.raantech.awfrlak.user.data.repos.wishlist.WishListRepo
import com.raantech.awfrlak.user.data.repos.wishlist.WishListRepoImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepoModule {

    @Singleton
    @Binds
    abstract fun bindConfigurationRepo(configurationRepoImp: ConfigurationRepoImp): ConfigurationRepo

    @Singleton
    @Binds
    abstract fun bindUserRepo(userRepoImp: UserRepoImp): UserRepo

    @Singleton
    @Binds
    abstract fun bindMediaRepo(mediaRepoImp: MediaRepoImp): MediaRepo

    @Singleton
    @Binds
    abstract fun bindAccessoryRepo(accessoriesRepoImp: AccessoriesRepoImp): AccessoriesRepo

    @Singleton
    @Binds
    abstract fun bindCartRepo(cartRepoImp: CartRepoImp): CartRepo

    @Singleton
    @Binds
    abstract fun bindMobileCartRepo(mobileRepoImp: MobileCartRepoImp): MobileCartRepo

    @Singleton
    @Binds
    abstract fun bindWishListRepo(wishListRepoImp: WishListRepoImp): WishListRepo

    @Singleton
    @Binds
    abstract fun bindOrdersRepo(ordersRepoImp: OrdersRepoImp): OrdersRepo

}