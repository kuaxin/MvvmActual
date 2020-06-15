package com.youjiaoyule.mvvmactual.module.home

import androidx.lifecycle.MutableLiveData
import com.wjx.android.wanandroidmvvm.common.state.State
import com.youjiaoyule.mvvmactual.activity.home.adapter.bean.HDData
import com.youjiaoyule.mvvmactual.base.ApiRepository
import com.youjiaoyule.mvvmactual.net.dataConvert

/**
 *  @author RenGX on 2020/6/10
 *
 */
class HomeRepository(private val loadState: MutableLiveData<State>): ApiRepository() {

    /**
     * @param adType 4 Banner 6 Icon
     */
    suspend fun loadIconData(adType: Int): List<HDData>{
        val hm =
            HashMap<String, Any>()
        hm["adType"] = adType

        return apiService.loadHomeData("/api/v2/front/adPic/getAdPicByAdType",hm).dataConvert(loadState)
    }

}