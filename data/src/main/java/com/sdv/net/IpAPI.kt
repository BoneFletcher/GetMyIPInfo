package com.sdv.net

import com.sdv.domain.model.response.IpAddressDetail
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface IpAPI {

    @GET("json/{query}")
    suspend fun getIpDetail(@Path("query") ip: String): Response<IpAddressDetail>

}