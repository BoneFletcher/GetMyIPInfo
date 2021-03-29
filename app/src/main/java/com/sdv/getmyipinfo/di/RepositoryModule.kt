package com.sdv.getmyipinfo.di

import com.sdv.domain.repository.IpAddressRepository
import com.sdv.repository.IpAddressRepositoryImpl
import org.koin.core.module.Module
import org.koin.dsl.module

val repository: Module = module {
    single<IpAddressRepository> { IpAddressRepositoryImpl(get()) }
}