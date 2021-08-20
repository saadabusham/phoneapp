package com.technzone.phoneapp.data.di


import com.technzone.phoneapp.data.repos.accessories.AccessoriesRepo
import com.technzone.phoneapp.data.repos.accessories.AccessoriesRepoImp
import com.technzone.phoneapp.data.repos.auth.UserRepo
import com.technzone.phoneapp.data.repos.auth.UserRepoImp
import com.technzone.phoneapp.data.repos.cart.CartRepo
import com.technzone.phoneapp.data.repos.cart.CartRepoImp
import com.technzone.phoneapp.data.repos.configuration.ConfigurationRepo
import com.technzone.phoneapp.data.repos.configuration.ConfigurationRepoImp
import com.technzone.phoneapp.data.repos.media.MediaRepo
import com.technzone.phoneapp.data.repos.media.MediaRepoImp
import com.technzone.phoneapp.data.repos.wishlist.WishListRepo
import com.technzone.phoneapp.data.repos.wishlist.WishListRepoImp
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
    abstract fun bindUserRepo(userRepoImp: UserRepoImp) : UserRepo

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
    abstract fun bindWishListRepo(wishListRepoImp: WishListRepoImp): WishListRepo

}