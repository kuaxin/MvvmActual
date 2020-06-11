package com.youjiaoyule.mvvmactual.module.home

import androidx.lifecycle.MutableLiveData
import com.wjx.android.wanandroidmvvm.common.state.State
import com.youjiaoyule.mvvmactual.base.ApiRepository
import com.youjiaoyule.mvvmactual.net.dataConvert

/**
 *  @author RenGX on 2020/6/10
 *
 */
class HomeRepository(val loadState: MutableLiveData<State>): ApiRepository() {

    suspend fun loadBannerCo(): HomeBean{

        val hm =
            HashMap<String, Any>()
        hm["currentPage"] = 1
        hm["pageSize"] = 1

        return apiService.loadHomeBean("/api/v2/front/sentence",hm).dataConvert(loadState)
    }



}