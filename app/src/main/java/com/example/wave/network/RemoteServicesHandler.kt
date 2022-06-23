package com.example.wave.network

import com.example.wave.network.exceptions.NetworkExceptionHandler
import retrofit2.HttpException
import retrofit2.Response

class RemoteServicesHandler(private val errorHandler: NetworkExceptionHandler) {

    suspend fun <T : Any, R : Response<*>> makeTheCallAndHandleResponse(
        serviceCall: suspend () -> (R),
        mapSuccess: (R) -> Resource<T>
    ) = try {
        val response = serviceCall()
        if (response.isSuccessful) {
            mapSuccess(response)
        } else {
            val error = errorHandler.map<T>(HttpException(response))
            error
        }
    } catch (exception: Exception) {
        errorHandler.map(exception)
    }
}