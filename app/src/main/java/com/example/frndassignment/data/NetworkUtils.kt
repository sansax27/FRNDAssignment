package com.example.frndassignment.data

import retrofit2.Response
import timber.log.Timber


object NetworkUtils {

    suspend fun <T : Any> callApi(
        executeApi: suspend () ->  Response<T>
    ): NetworkResult<T> {
        return try {
            val response = executeApi()
            if (response.isSuccessful && response.body() != null) {
                Timber.i(response.body().toString())
                NetworkResult.Success(response.body()!!)
            } else {
                NetworkResult.Failure(response.code(), response.message())
            }
        } catch (e: Exception) {
            Timber.i("Exception Occurred $e")
            NetworkResult.Exception(e)
        }
    }
}