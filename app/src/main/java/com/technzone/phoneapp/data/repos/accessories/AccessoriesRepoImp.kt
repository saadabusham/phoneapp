package com.technzone.phoneapp.data.repos.accessories

import com.technzone.phoneapp.data.api.response.APIResource
import com.technzone.phoneapp.data.api.response.ResponseHandler
import com.technzone.phoneapp.data.api.response.ResponseWrapper
import com.technzone.phoneapp.data.daos.remote.accessories.AccessoriesRemoteDao
import com.technzone.phoneapp.data.models.accessories.Accessory
import com.technzone.phoneapp.data.repos.base.BaseRepo
import javax.inject.Inject

class AccessoriesRepoImp @Inject constructor(
    responseHandler: ResponseHandler,
    private val accessoriesRemoteDao: AccessoriesRemoteDao
) : BaseRepo(responseHandler), AccessoriesRepo {

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