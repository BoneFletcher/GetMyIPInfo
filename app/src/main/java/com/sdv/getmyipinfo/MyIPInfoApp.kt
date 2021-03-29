package com.sdv.getmyipinfo

import android.app.Application
import android.content.Context
import com.sdv.getmyipinfo.di.repository
import com.sdv.getmyipinfo.di.useCase
import com.sdv.getmyipinfo.di.viewModel
import com.sdv.getmyipinfo.di.dataSource
import com.sdv.getmyipinfo.di.network
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyIPInfoApp: Application() {
    private fun initKoin() {
        startKoin {
            androidContext(this@MyIPInfoApp)
            modules(
                listOf(
                    network,
                    viewModel,
                    useCase,
                    repository,
                    dataSource
                )
            )
        }
    }

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        initKoin()
    }

    companion object {
        lateinit var appContext: Context
    }
}