package com.technzone.baseapp.data.di


import com.technzone.baseapp.data.repos.auth.UserRepo
import com.technzone.baseapp.data.repos.auth.UserRepoImp
import com.technzone.baseapp.data.repos.configuration.ConfigurationRepo
import com.technzone.baseapp.data.repos.configuration.ConfigurationRepoImp
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



}