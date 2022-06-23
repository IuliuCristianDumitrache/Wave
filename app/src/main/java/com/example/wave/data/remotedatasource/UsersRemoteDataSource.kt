package com.example.wave.data.remotedatasource

import com.example.wave.models.UserResponse
import com.example.wave.network.ApiService
import com.example.wave.network.RemoteServicesHandler
import com.example.wave.network.Resource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UsersRemoteDataSource @Inject constructor(
    private val apiService: ApiService,
    private val servicesHandler: RemoteServicesHandler
) {
    suspend fun fetchUsersData(page: Int, loadSize: Int): Resource<UserResponse> =
        servicesHandler.makeTheCallAndHandleResponse(
            serviceCall = { apiService.getUsers(page, loadSize) },
            mapSuccess = {
                Resource.Success(it.body())
            }
        )
}