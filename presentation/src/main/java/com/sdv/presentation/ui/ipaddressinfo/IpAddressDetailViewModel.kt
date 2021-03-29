package com.sdv.presentation.ui.ipaddressinfo

import android.util.Log
import androidx.databinding.Bindable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sdv.domain.model.response.IpAddressDetail
import com.sdv.domain.usecase.GetIpAddressInfoUseCase
import com.sdv.presentation.base.BaseViewModel

class IpAddressDetailViewModel(private val getIpAddressInfoUseCase: GetIpAddressInfoUseCase) : BaseViewModel() {
    private val ipAddressDetailMutable: MutableLiveData<IpAddressDetail> = MutableLiveData()
    val ipAddressDetailLiveData: LiveData<IpAddressDetail> = ipAddressDetailMutable
    private val showErrorMutableLiveData: MutableLiveData<String> = MutableLiveData()
    val showErrorLiveData: MutableLiveData<String> = showErrorMutableLiveData
    val showProgress: MutableLiveData<Boolean> = MutableLiveData()

    fun getIpAddressInfo(ipAddress: String) {
        showProgress.postValue(true)
        launchAsync {
            getIpAddressInfoUseCase.invoke(ipAddress, {
                showProgress.postValue(false)
                ipAddressDetailMutable.postValue(it)
            }, {
                showProgress.postValue(false)
                showErrorMutableLiveData.postValue(it.message)
            })
        }
    }
}