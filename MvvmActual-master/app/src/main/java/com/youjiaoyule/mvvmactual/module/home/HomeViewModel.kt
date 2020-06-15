package com.youjiaoyule.mvvmactual.module.home

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.youjiaoyule.mvvmactual.activity.home.adapter.bean.HDData
import com.youjiaoyule.mvvmactual.base.BaseViewModel
import com.youjiaoyule.mvvmactual.net.initiateRequest

/**
 *  @author RenGX on 2020/6/10
 *
 */
class HomeViewModel(application: Application): BaseViewModel<HomeRepository>(application) {
    val homeData: MutableLiveData<List<HDData>> = MutableLiveData()

    fun loadHomeData(adType: Int){
        initiateRequest(
            {
                homeData.value = mRepository.loadIconData(adType)
            },
            loadState
        )
    }
}