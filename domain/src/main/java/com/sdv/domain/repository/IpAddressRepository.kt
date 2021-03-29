package com.sdv.domain.repository

import com.sdv.domain.model.Error
import com.sdv.domain.model.response.IpAddressDetail


interface IpAddressRepository {
    suspend fun getIpAddress(ipAddress: String, onSuccess: (IpAddressDetail) -> Unit, onError: (Error) -> Unit)
}