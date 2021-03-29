package com.sdv.repository

import com.sdv.datasource.remote.API
import com.sdv.domain.repository.IpAddressRepository
import com.sdv.domain.model.Error
import com.sdv.domain.model.response.IpAddressDetail

class IpAddressRepositoryImpl(private val api: API): IpAddressRepository {

    override suspend fun getIpAddress(
        ipAddress: String,
        onSuccess: (IpAddressDetail) -> Unit,
        onError: (Error) -> Unit
    ) = api.getIpDetail(ipAddress, onSuccess, onError)
}