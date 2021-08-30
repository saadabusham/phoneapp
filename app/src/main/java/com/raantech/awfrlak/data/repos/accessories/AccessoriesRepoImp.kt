package com.raantech.awfrlak.data.repos.accessories

import com.raantech.awfrlak.data.api.response.APIResource
import com.raantech.awfrlak.data.api.response.ResponseHandler
import com.raantech.awfrlak.data.api.response.ResponseWrapper
import com.raantech.awfrlak.data.daos.remote.accessories.AccessoriesRemoteDao
import com.raantech.awfrlak.data.models.accessories.Accessory
import com.raantech.awfrlak.data.models.home.HomeResponse
import com.raantech.awfrlak.data.repos.base.BaseRepo
import javax.inject.Inject

class AccessoriesRepoImp @Inject constructor(
    responseHandler: ResponseHandler,
    private val accessoriesRemoteDao: AccessoriesRemoteDao
) : BaseRepo(responseHandler), AccessoriesRepo {
    override suspend fun getHome(): APIResource<ResponseWrapper<HomeResponse>> {
        return try {
            responseHandle.handleSuccess(accessoriesRemoteDao.getHome())
        } catch (e: Exception) {
            responseHandle.handleException(e)
        }
    }

    override suspend fun getAccessories(
        skip: Int,
        serviceType: String?
    ): APIResource<ResponseWrapper<List<Accessory>>> {
        return try {
            responseHandle.handleSuccess(accessoriesRemoteDao.getAccessories(skip, serviceType))
        } catch (e: Exception) {
            responseHandle.handleException(e)
        }
    }

    override suspend fun getAccessory(id: Int): APIResource<ResponseWrapper<Accessory>> {
        return try {
            responseHandle.handleSuccess(accessoriesRemoteDao.getAccessory(id))
        } catch (e: Exception) {
            responseHandle.handleException(e)
        }
    }

}