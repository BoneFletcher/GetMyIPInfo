package com.sdv.getmyipinfo.di


import com.sdv.domain.usecase.GetIpAddressInfoUseCase
import org.koin.core.module.Module
import org.koin.dsl.module

val useCase: Module = module {
   single { GetIpAddressInfoUseCase(get()) }

}