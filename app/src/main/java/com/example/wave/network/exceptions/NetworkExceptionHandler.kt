package com.example.wave.network.exceptions

import android.util.Log
import com.example.wave.R
import com.example.wave.network.Resource
import com.example.wave.utils.StringResource
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkExceptionHandler @Inject constructor(val resources: StringResource) {

    companion object {
        private val TAG = NetworkExceptionHandler::class.simpleName
    }

    fun <T> map(throwable: Throwable): Resource<T> {
        Log.e(TAG, "", throwable)
        return when (throwable) {
            is HttpException -> mapHttpException(throwable)
            is NetworkNotAvailableException -> {
                Resource.Error(message = resources.getString(R.string.no_internet_error))
            }
            else -> {
                Resource.Error(message = resources.getString(R.string.something_went_wrong))
            }
        }
    }

    private fun <T> mapHttpException(exception: HttpException): Resource<T> {
        return try {
            val errorBody = exception.response()?.errorBody()?.string()
            Resource.Error(
                message = errorBody,
                httpCode = exception.code()
            )
        } catch (parseException: Exception) {
            Resource.Error(
                message = resources.getString(R.string.something_went_wrong),
                httpCode = exception.code()
            )
        }
    }
}