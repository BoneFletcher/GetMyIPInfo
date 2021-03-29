package com.sdv.datasource.remote

import com.sdv.net.IpAPI
import retrofit2.Response
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import com.sdv.domain.model.Result
import com.sdv.domain.model.Error
import com.sdv.domain.model.response.IpAddressDetail

class API(private val ipAPI: IpAPI) {

    private suspend fun <T : Any> safeApiCall(
        call: suspend () -> Response<T>,
        OnSuccess: (T) -> Unit,
        OnError: (Error) -> Unit
    ) {
        when (val result: Result<T> = safeApiResult(call)) {
            is Result.Success ->
                OnSuccess(result.data)
            is Result.Error -> {
                OnError(result.exception)
            }
        }
    }

    private suspend fun <T : Any> safeApiCallWithPagination(
        call: suspend () -> Response<T>,
        OnSuccess: (T) -> Unit,
        OnError: (Error) -> Unit
    ): T? {

        val result: Result<T> = safeApiResult(call)
        var data: T? = null

        when (result) {
            is Result.Success ->
                return result.data
            is Result.Error -> {
                OnError(result.exception)
            }
        }
        return null
    }

    private suspend fun <T : Any> safeApiResult(call: suspend () -> Response<T>): Result<T> {
        var response: Response<T>
        try {
            response = call.invoke()
        } catch (e: Exception) {
            return if (e is UnknownHostException || e is SocketTimeoutException)
                Result.Error(Error(
                        "No internet connection",
                        666
                    )
                )
            else
                Result.Error(
                    Error(e.message ?: "", -1))
        }
        if (response.isSuccessful) return Result.Success(response.body()!!)
        return Result.Error(
            Error(if (response.code() == 500) "Oops! Something went wrong" else response.errorBody()?.string() ?: "", response.code()
            )
        )
    }

    suspend fun getIpDetail(
        ipAddress: String,
        onSuccess: (IpAddressDetail) -> Unit,
        onError: (Error) -> Unit

    ) {
        safeApiCall(
            call = {
                ipAPI.getIpDetail(ipAddress)
            }
            , OnSuccess = {
                onSuccess(it)
            }
            , OnError = {
                onError(it)
            })
    }
}