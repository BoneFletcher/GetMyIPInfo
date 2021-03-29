package com.sdv.getmyipinfo.di

import com.sdv.presentation.ui.ipaddressinfo.IpAddressDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val viewModel: Module = module {

    viewModel {
        IpAddressDetailViewModel(get())
    }

}