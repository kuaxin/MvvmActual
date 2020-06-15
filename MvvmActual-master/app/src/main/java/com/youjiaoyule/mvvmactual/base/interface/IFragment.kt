package com.youjiaoyule.mvvmactual.base.`interface`

import android.app.Activity

/**
 *  @author RenGX on 2020/6/11
 *
 */
interface IFragment {

    /**
     * 获取获取当前Fragment的宿主Activity
     *
     * @return 所依赖的Activity
     */
    fun getHoldingActivity():Activity


}