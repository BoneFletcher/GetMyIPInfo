package com.sdv.domain.usecase

import com.sdv.domain.model.response.IpAddressDetail
import com.sdv.domain.repository.IpAddressRepository

class GetIpAddressInfoUseCase(private val repository: IpAddressRepository) {
    suspend operator fun invoke(ipAddress: String,
                                onSuccess: (IpAddressDetail) -> Unit,
                                onError: (com.sdv.domain.model.Error) -> Unit
    ) = repository.getIpAddress(ipAddress, onSuccess, onError)
}