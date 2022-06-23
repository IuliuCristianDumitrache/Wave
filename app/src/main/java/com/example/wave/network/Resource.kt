package com.example.wave.network

import java.io.Serializable

sealed class Resource<T>(
    val httpCode: Int? = null,
    val data: T? = null,
    val message: String? = null,
    val status: Status
) : Serializable {
    class Success<T>(data: T?) : Resource<T>(data = data, status = Status.SUCCESS)
    class Loading<T>(data: T? = null) : Resource<T>(data = data, status = Status.LOADING)
    class Error<T>(httpCode: Int? = null, message: String?) :
        Resource<T>(httpCode = httpCode, message = message, status = Status.ERROR)
}

fun <T> Resource<T>.isSuccessful(): Boolean {
    return status == Status.SUCCESS
}

